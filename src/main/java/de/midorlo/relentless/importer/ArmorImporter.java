package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.item.Element;
import de.midorlo.relentless.domain.item.Armor;
import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.cell.CellType;
import de.midorlo.relentless.domain.item.ItemType;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.repository.dep.Assets;
import de.midorlo.relentless.repository.dep.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_ARMOR;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ArmorImporter extends YamlFileImporter<Armor> {

    Repository<Perk> perkRepository;
    Repository<PerkEffect> perkEffectRepository;

    public ArmorImporter(Repository repository, Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        super(repository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_ARMOR;
    }

    @Override
    protected void importGameObjects(List<LinkedHashMap<Object,Object>> map) {
        super.importGameObjects(map);
        repository.findAll().forEach(armor -> {
            perkRepository.save(armor.getPerks());
            perkEffectRepository.save(armor.getPerkEffects());
        });
    }

    @Override
    protected Armor parseGameObject(LinkedHashMap map) {
        Object name = map.get("name");
        Object description = map.get("description");
        Object icon = map.get("icon");
        Object type = map.get("type");
        Object strength = map.get("strength");

        Object cellsMap = map.get("cells");
        Object perksMap = map.get("perks");
        Object unique_effects = map.get("unique_effects");

        Armor a = new Armor();
        a.setName((String) name);
        a.setDescription((String) description);
        a.setType(ItemType.valueOf(((String) type).trim().replace(" ", "")));
        a.setElement(((strength == null) ? Element.Neutral : Element.valueOf((String) strength)));

        Assets.assetsPathMap.put(a, (String) icon);

        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        List<Perk> perks = perkImporter.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap);
        a.getPerks().addAll(perks);

        List<CellSocket> cellSockets = parseCellSockets((String) cellsMap);
        a.getCellSockets().addAll(cellSockets);

        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
        List<PerkEffect> perkEffects = perkEffectImporter.parseGameWeaponObjects((ArrayList<LinkedHashMap>) unique_effects);
        a.getPerkEffects().addAll(perkEffects);

        return a;
    }

    private List<CellSocket> parseCellSockets(String cellSocketString) {
        List<CellSocket> cellSockets = new ArrayList<>();
        if (cellSocketString != null) {
            CellSocket cellSocket = new CellSocket();
            cellSocket.setType(CellType.valueOf(cellSocketString));
            cellSockets.add(cellSocket);
        }
        return cellSockets;
    }
}
