package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;

public class PerkRepository extends Repository<Perk> {

    private final Repository<PerkEffect> perkEffectRepository;

    public PerkRepository(Repository<PerkEffect> perkEffectRepository) {
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    public void save(Perk perk) {
        perk.getEffects().forEach(perkEffectRepository::save);
        super.save(perk);
    }
}
