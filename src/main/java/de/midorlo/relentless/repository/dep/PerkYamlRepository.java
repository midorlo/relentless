package de.midorlo.relentless.repository.dep;

import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;

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
