package de.midorlo.relentless.importer;

import de.midorlo.relentless.model.PerkEffect;
import de.midorlo.relentless.repository.Repository;

import java.util.LinkedHashMap;

@SuppressWarnings("rawtypes")
public class PerkEffectImporter extends AbstractImporter<PerkEffect> {

    public PerkEffectImporter(Repository<PerkEffect> repository) {
        super(repository);
    }

    @Override
    public PerkEffect parseGameObject(LinkedHashMap leveledPerkEffectMap, Object extraData) {
        PerkEffect effect = new PerkEffect();
        Object description = leveledPerkEffectMap.get("description");
        Object value = leveledPerkEffectMap.get("value");
        parseMixedContents(description, effect.getDescriptions());
        parseMixedContents(value, effect.getValues());
        return effect;
    }
}
