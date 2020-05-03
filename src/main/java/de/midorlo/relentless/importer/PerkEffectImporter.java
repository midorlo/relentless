package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.domain.PerkEffectDescription;
import de.midorlo.relentless.domain.PerkEffectValue;
import de.midorlo.relentless.importer.yaml.YamlRepository;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class PerkEffectImporter extends YamlFileImporter<PerkEffect> {

    public PerkEffectImporter(YamlRepository<PerkEffect> repository) {
        super(repository);
    }

    /**
     * There are no source yaml files present for perk effects. Those are created indirectly
     * while parsing Perks.
     */
    @Override
    public void importGameObjects() {
    }

    /**
     * Dummy.
     * @return null.
     */
    @Override
    protected String getYamlsPath() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected PerkEffect parseGameObject(LinkedHashMap<Object,Object> leveledPerkEffectMap) {
        PerkEffect effect = new PerkEffect();

        Object descriptions = leveledPerkEffectMap.get("description");
        Object values       = leveledPerkEffectMap.get("value");

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
//        effect.getDescriptions().add(description);
        effect.setLevel(level);
//        effect.getValues().add(""+value);
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

}
