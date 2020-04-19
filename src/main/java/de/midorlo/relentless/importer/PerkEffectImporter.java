package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.items.PerkEffect;
import de.midorlo.relentless.repository.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
public class PerkEffectImporter extends AbstractImporter<PerkEffect> {

    public PerkEffectImporter(Repository<PerkEffect> repository) {
        super(repository);
    }

    @Override
    public PerkEffect parseGameObject(LinkedHashMap<Object,Object> leveledPerkEffectMap) {
        PerkEffect effect = new PerkEffect();
        Object description = leveledPerkEffectMap.get("description");
        Object value = leveledPerkEffectMap.get("value");
        parseMixedContents(description, effect.getDescriptions());
        parseMixedContents(value, effect.getValues());
        return effect;
    }

    /**
     * Weapon data is another special snowflake
     *
     * @param mapsList list of Datamaps with reduced details
     * @return list of Combat Effects
     */
    public List<PerkEffect> parseGameWeaponObjects(ArrayList<LinkedHashMap> mapsList) {
        List<PerkEffect> effects = new ArrayList<>();
        if (mapsList != null) {
            for (LinkedHashMap map : mapsList) {
                PerkEffect effect = parseGameWeaponObject(map);
                effects.add(effect);
            }
        }
        return effects;
    }

    public PerkEffect parseGameWeaponObject(LinkedHashMap map) {
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
        effect.getDescriptions().add(description);
        effect.setLevel(level);
        effect.getValues().add(""+value);
        return effect;
    }
}
