package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.AttackType;
import de.midorlo.relentless.domain.items.*;
import de.midorlo.relentless.repository.Assets;
import de.midorlo.relentless.repository.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_WEAPONS;

@SuppressWarnings({"unchecked", "rawtypes"})
public class WeaponImporter extends YamlFileImporter<Weapon> {

    Repository<Perk> perkRepository;
    Repository<PerkEffect> perkEffectRepository;

    public WeaponImporter(Repository<Weapon> weaponRepository, Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
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
        w.setElement(((elemental == null) ? Element.Neutral : Element.valueOf((String) elemental)));
        String attackTypeString = (String) damage;
        //Bad source data again.
        if ("Cutting".contentEquals(attackTypeString)) {
            attackTypeString = "Slashing";
        }
        w.setAttackType(AttackType.valueOf(attackTypeString));
        Assets.assetsPathMap.put(w, (String) icon);

        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        List<Perk> perks = perkImporter.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap);
        w.getPerks().addAll(perks);

        List<CellSocket> cellSockets = parseCellSockets((ArrayList<String>) cellsMap);
        w.getCellSockets().addAll(cellSockets);

        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
        List<PerkEffect> perkEffects = perkEffectImporter.parseGameWeaponObjects((ArrayList<LinkedHashMap>) unique_effects);
        w.getPerkEffects().addAll(perkEffects);

        return w;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_WEAPONS;
    }

    @Override
    protected Repository<Weapon> importGameObjects(List<LinkedHashMap<Object,Object>> map) {
        super.importGameObjects(map);
        repository.findAll().forEach(weapon -> {
            perkRepository.save(weapon.getPerks());
            perkEffectRepository.save(weapon.getPerkEffects());
        });
        return repository;
    }

    private List<CellSocket> parseCellSockets(ArrayList<String> stringList) {
        List<CellSocket> cellSockets = new ArrayList<>();
        if (stringList != null) {
            cellSockets.addAll(stringList.stream().map(e -> {
                CellSocket cellSocket = new CellSocket();
                cellSocket.setType(CellType.valueOf(e));
                return cellSocket;
            }).collect(Collectors.toList()));
        }
        return cellSockets;
    }
}
