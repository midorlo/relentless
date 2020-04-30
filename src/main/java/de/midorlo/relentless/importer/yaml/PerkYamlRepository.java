package de.midorlo.relentless.importer.yaml;

import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.domain.PerkEffect;

public class PerkYamlRepository extends YamlRepository<Perk> {

    private final YamlRepository<PerkEffect> perkEffectRepository;

    public PerkYamlRepository(YamlRepository<PerkEffect> perkEffectRepository) {
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    public void save(Perk perk) {
        perk.getEffects(perk.getLevel()).forEach(perkEffectRepository::save);
        super.save(perk);
    }
}
