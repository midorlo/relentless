package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.domain.PerkEffectDescription;
import de.midorlo.relentless.domain.PerkEffectValue;
import de.midorlo.relentless.importer.yaml.YamlRepository;
import de.midorlo.relentless.repository.PerkEffectRepository;
import de.midorlo.relentless.util.FileUtillities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_PERKS;

@Configuration
@SuppressWarnings("rawtypes")
public class PerkEffectImporter {

//    public CommandLineRunner importPerkEffects(@Autowired PerkEffectRepository repository) {
//        return args -> {
//            List<PerkEffect> parsedItems = new ArrayList<>();
//            List<LinkedHashMap<Object, Object>> map = FileUtillities.readYamlFiles(DIR_DAUNTLESS_BUILDER_PERKS);
//            for (LinkedHashMap<Object, Object> oMap : map) {
//                parsedItems.add(parseGameObject(oMap));
//            }
//            return parsedItems;
//        };
//    }

    protected List<PerkEffect> parseGameObjects(List<LinkedHashMap<Object, Object>> map) {
        List<PerkEffect> parsedItems = new ArrayList<>();
        for (LinkedHashMap<Object, Object> oMap : map) {
            parsedItems.add(parseGameObject(oMap));
        }
        return parsedItems;
    }

    protected PerkEffect parseGameObject(LinkedHashMap<Object, Object> leveledPerkEffectMap) {
        PerkEffect effect = new PerkEffect();

        Object descriptions = leveledPerkEffectMap.get("description");
        Object values = leveledPerkEffectMap.get("value");

        List<String> descriptionsList = (descriptions instanceof String)
                ? Collections.singletonList(((String) descriptions))
                : (ArrayList<String>) descriptions;

        List<Object> valuesList = (values instanceof ArrayList)
                ? (List<Object>) values
                : Collections.singletonList(values);
        return effect;
    }

    /**
     * Weapon data is another special snowflake
     *
     * @param mapsList list of Datamaps with reduced details
     * @return list of Combat Effects
     */
    protected List<PerkEffect> parseGameWeaponObjects(ArrayList<LinkedHashMap> mapsList) {
        List<PerkEffect> effects = new ArrayList<>();
        if (mapsList != null) {
            for (LinkedHashMap map : mapsList) {
                PerkEffect effect = parseGameWeaponObject(map);
                effects.add(effect);
            }
        }
        return effects;
    }

    protected PerkEffect parseGameWeaponObject(LinkedHashMap map) {
        String name = (String) map.get("name");
        String description = (String) map.get("description");
        Double value = parseMixedNumerics(map.get("value"));
        Double from = parseMixedNumerics(map.get("from"));
        int level;
        if (from == 0) {
            level = 1;
        } else if (from == 6) {
            level = 2;
        } else if (from == 10) {
            level = 3;
        } else {
            level = 3;
            name = "FIXME_" + name;
        }

        PerkEffect effect = new PerkEffect();
        effect.setName(name);
        effect.setLevel(level);
        return effect;
    }

    protected static List<PerkEffectDescription> parsePerkEffectDescriptions(List<String> parsedList) {
        return parsedList.stream()
                .filter(Objects::nonNull)
                .map(PerkEffectDescription::new)
                .collect(Collectors.toList());
    }

    protected static List<PerkEffectValue> parsePerkEffectValues(List<Object> parsedList) {
        return parsedList.stream()
                .filter(Objects::nonNull)
                .map(e -> "" + e)
                .map(PerkEffectValue::new)
                .collect(Collectors.toList());
    }

    /**
     * Utillity Method. Asserts an Integer/Double/null as Double at compile time.
     *
     * @param o Numeric..
     * @return double
     */
    protected static Double parseMixedNumerics(Object o) {
        double d = 0d;
        if (o instanceof Integer) {
            d += ((Integer) o);
        } else if (o instanceof Double) {
            d += ((Double) o);
        } else {
            d = -1d; //todo warn
        }
        return d;
    }

}
