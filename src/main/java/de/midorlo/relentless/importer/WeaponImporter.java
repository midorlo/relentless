package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.DamageType;
import de.midorlo.relentless.domain.items.*;
import de.midorlo.relentless.domain.mutators.Perk;
import de.midorlo.relentless.domain.mutators.PerkEffect;
import de.midorlo.relentless.repository.Assets;
import de.midorlo.relentless.repository.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponImporter extends AbstractImporter<Weapon> {

    Repository<Perk> perkRepository;
    Repository<PerkEffect> perkEffectRepository;

    public WeaponImporter(Repository<Weapon> weaponRepository, Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        super(weaponRepository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    public Weapon parseGameObject(LinkedHashMap map, Object extraData) {

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
        w.setDamageType(DamageType.valueOf((String) damage));

        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        List<Perk> perks = perkImporter.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap, null);
        w.setPerks(perks);
        w.setCellSockets(parseCellSockets((ArrayList<String>) cellsMap));


        Assets.assetsPathMap.put(w, (String) icon);
        return w;
    }

    private List<CellSocket> parseCellSockets(ArrayList<String> arrayList) {
        List<CellSocket> cellSockets = new ArrayList<>();
        if (arrayList != null) {
            cellSockets.addAll(arrayList.stream().map(e -> {
                CellSocket cellSocket = new CellSocket();
                cellSocket.setType(CellType.valueOf((String) e));
                return cellSocket;
            }).collect(Collectors.toList()));
        }
        return cellSockets;
    }
}
