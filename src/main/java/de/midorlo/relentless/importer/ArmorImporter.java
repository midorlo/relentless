package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Armor;
import de.midorlo.relentless.domain.CellType;
import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.ItemType;
import de.midorlo.relentless.repository.*;
import de.midorlo.relentless.util.FileUtillities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.importer.CellTypeImporter.parseCellTypes;
import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_ARMOR;

@Configuration
@Slf4j
public class ArmorImporter {

    @Bean
    public CommandLineRunner importArmors(
            @Autowired ArmorRepository armorRepository,
            @Autowired ElementRepository elementRepository,
            @Autowired CellRepository cellRepository,
            @Autowired CellTypeRepository cellTypeRepository,
            @Autowired PerkRepository perkRepository,
            @Autowired PerkEffectRepository perkEffectRepository
    ) {
        return args -> {

            FileUtillities.readYamlFiles(DIR_DAUNTLESS_BUILDER_ARMOR)
                    .stream()
                    .map(this::parseGameObject)
                    .forEach(armorRepository::save);
            log.info(String.format("importArmors() -> imported %d Armors.", armorRepository.findAll().size()));
        };
    }

    @SuppressWarnings("rawtypes")
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
        a.setLevel(15);
        a.setDescription((String) description);
        a.setType(ItemType.valueOf(((String) type).trim().replace(" ", "")));
        a.setElement(((strength == null) ? new Element("Neutral") : new Element(strength.toString())));

//        Assets.assetsPathMap.put(a, (String) icon);

//        PerkImporter perkImporter = new PerkImporter();
//        List<Perk> perks = perkImporter.parseWeaponPerks((ArrayList<LinkedHashMap>) perksMap);
//        a.getPerks().addAll(perks);


        List<CellType> cellTypes = parseCellTypes((String) cellsMap);
        if (cellTypes.size() > 0) {
            a.setPrimaryCellSocket(cellTypes.get(0));
        }

        //todo uniques als Perks parsen
//        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
//        List<PerkEffect> perkEffects = perkEffectImporter.parseGameWeaponObjects((ArrayList<LinkedHashMap>) unique_effects);
//        a.getPerkEffects().addAll(perkEffects);

        return a;
    }
//    private List<CellType> parseCellType(String cellSocketString, Armor parent) {
//        List<CellType> cellSockets = new ArrayList<>();
//        if (cellSocketString != null) {
//            CellType cellSocket = new CellType();
//            CellType.par(cellSocketString);
//            cellSocket.setName();
//            cellSocket.setId(1L + parent.hashCode() + cellSocket.getType().hashCode());
//            cellSockets.add(cellSocket);
//        }
//        return cellSockets;
//    }


}
