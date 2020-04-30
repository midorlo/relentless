package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.CellSocket;
import de.midorlo.relentless.domain.Armor;
import de.midorlo.relentless.domain.ItemType;
import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.importer.yaml.Assets;
import de.midorlo.relentless.importer.yaml.YamlRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_ARMOR;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ArmorImporter extends YamlFileImporter<Armor> {

    YamlRepository<Perk> perkRepository;
    YamlRepository<PerkEffect> perkEffectRepository;

    public ArmorImporter(YamlRepository repository, YamlRepository<Perk> perkRepository, YamlRepository<PerkEffect> perkEffectRepository) {
        super(repository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_ARMOR;
    }

    @Override
    protected void importGameObjects(List<LinkedHashMap<Object, Object>> map) {
        super.importGameObjects(map);
        repository.findAll().forEach(armor -> perkRepository.save(armor.getPerks()));
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
        a.setElement(((strength == null) ? new Element("Neutral") : new Element(strength.toString())));

        Assets.assetsPathMap.put(a, (String) icon);

        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        List<Perk> perks = perkImporter.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap);
        a.getPerks().addAll(perks);

        List<CellSocket> cellSockets = parseCellSocket((String) cellsMap, a);
        a.getCellSockets().addAll(cellSockets);

        //todo uniques als Perks parsen
//        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
//        List<PerkEffect> perkEffects = perkEffectImporter.parseGameWeaponObjects((ArrayList<LinkedHashMap>) unique_effects);
//        a.getPerkEffects().addAll(perkEffects);

        return a;
    }

    private List<CellSocket> parseCellSocket(String cellSocketString, Armor parent) {
        List<CellSocket> cellSockets = new ArrayList<>();
        if (cellSocketString != null) {
            CellSocket cellSocket = new CellSocket();
            cellSocket.setType(CellImporter.parseCellType(cellSocketString));
            cellSocket.setId(1L + parent.hashCode() + cellSocket.getType().hashCode());
            cellSockets.add(cellSocket);
        }
        return cellSockets;
    }
}
