package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.gear.ItemType;
import de.midorlo.relentless.domain.gear.Weapon;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.repository.yaml.Assets;
import de.midorlo.relentless.repository.yaml.YamlRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_WEAPONS;

@SuppressWarnings({"unchecked", "rawtypes"})
public class WeaponImporter extends YamlFileImporter<Weapon> {

    YamlRepository<Perk> perkRepository;
    YamlRepository<PerkEffect> perkEffectRepository;

    public WeaponImporter(YamlRepository<Weapon> weaponRepository, YamlRepository<Perk> perkRepository, YamlRepository<PerkEffect> perkEffectRepository) {
        super(weaponRepository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected Weapon parseGameObject(LinkedHashMap map) {

        Object name = map.get("name");
        Object description = map.get("description");
        Object icon = map.get("icon");
        Object type = map.get("type");
        Object damage = map.get("damage");
        Object elemental = map.get("elemental");
        Object cellsMap = map.get("cells");
        Object perksMap = map.get("perks");
        Object unique_effects = map.get("unique_effects");

        Weapon w = new Weapon();
        w.setName((String) name);
        w.setDescription((String) description);
        w.setType(ItemType.valueOf(((String) type).trim().replace(" ", "")));
        w.setElement(((elemental == null) ? new Element("Neutral") : new Element("elemental")));
        Assets.assetsPathMap.put(w, (String) icon);

        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        List<Perk> perks = perkImporter.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap);
        w.getPerks().addAll(perks);

        List<CellSocket> cellSockets = parseCellSockets((ArrayList<String>) cellsMap);
        w.getCellSockets().addAll(cellSockets);

        //todo uniques als perks parsen
//        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
//        List<PerkEffect> perkEffects = perkEffectImporter.parseGameWeaponObjects((ArrayList<LinkedHashMap>) unique_effects);
//        w.getPerkEffects().addAll(perkEffects);

        return w;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_WEAPONS;
    }

    @Override
    protected void importGameObjects(List<LinkedHashMap<Object,Object>> map) {
        super.importGameObjects(map);
        repository.findAll().forEach(weapon -> {
            perkRepository.save(weapon.getPerks());
        });
    }

    private List<CellSocket> parseCellSockets(ArrayList<String> stringList) {
        List<CellSocket> cellSockets = new ArrayList<>();
        if (stringList != null) {
            cellSockets.addAll(stringList.stream().map(e -> {
                CellSocket cellSocket = new CellSocket();
                cellSocket.setType(CellImporter.parseCellType(e));
                return cellSocket;
            }).collect(Collectors.toList()));
        }
        return cellSockets;
    }
}
