package de.midorlo.relentless.beans;

import de.midorlo.relentless.beans.PerkBean;
import de.midorlo.relentless.domain.*;
import de.midorlo.relentless.repository.*;
import de.midorlo.relentless.util.FileUtillities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.beans.CellTypeBean.parseCellTypes;
import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_ARMOR;

@SuppressWarnings({"unchecked", "rawtypes"})
@Slf4j
@Configuration
public class WeaponBean {

//    @Bean
    public CommandLineRunner importWeapons(
            @Autowired WeaponRepository weaponRepository,
            @Autowired ElementRepository elementRepository,
            @Autowired CellRepository cellRepository,
            @Autowired CellTypeRepository cellTypeRepository,
            @Autowired PerkRepository perkRepository,
            @Autowired PerkEffectRepository perkEffectRepository
    ) {
        return args -> {

            FileUtillities.readYamlFiles(DIR_DAUNTLESS_BUILDER_ARMOR)
                    .stream()
                    .map(objectObjectLinkedHashMap -> parseGameObject(objectObjectLinkedHashMap, perkRepository))
                    .forEach(weaponRepository::save);
            log.info(String.format("importWeapons() -> imported %d Weapons.", weaponRepository.findAll().size()));
        };
    }

    protected Weapon parseGameObject(LinkedHashMap map, PerkRepository perkRepository) {

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
        w.setElement(((elemental == null) ? new Element("Neutral") : new Element(elemental.toString())));

        //todo parse assets
        //Assets.assetsPathMap.put(w, (String) icon);

//        List<Perk> perks = PerkBean.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap, perkRepository);
//        w.getPerks().addAll(perks);
//
//        List<CellType> cellTypes = parseCellTypes((String) cellsMap);
//        if (cellTypes.size() > 0) {
//            w.setPrimaryCellSocket(cellTypes.get(0));
//        }
//        if (cellTypes.size() > 1) {
//            w.setSecondaryCellSocket(cellTypes.get(1));
//        }

        //todo uniques als perks parsen
        //PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
        //List<PerkEffect> perkEffects = perkEffectImporter.parseGameWeaponObjects((ArrayList<LinkedHashMap>) unique_effects);
        //w.getPerkEffects().addAll(perkEffects);

        return w;
    }


//    @Override
//        protected void importGameObjects(List<LinkedHashMap<Object,Object>> map) {
//            super.importGameObjects(map);
//            repository.findAll().forEach(weapon -> perkRepository.save(weapon.getPerks()));
//    }

//    protected Element parseWeaponElement() {
//        return new Element();
//    }

//    private List<CellSocket> parseCellSockets(ArrayList<String> stringList) {
//        List<CellSocket> cellSockets = new ArrayList<>();
//        if (stringList != null) {
//            cellSockets.addAll(stringList.stream().map(e -> {
//                CellSocket cellSocket = new CellSocket();
//                cellSocket.setType(CellImporter.parseCellType(e));
//                return cellSocket;
//            }).collect(Collectors.toList()));
//        }
//        return cellSockets;
//    }
}
