package de.midorlo.relentless.repository.dep;

import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;

public class PerkRepository extends Repository<Perk> {

    private final Repository<PerkEffect> perkEffectRepository;

    public PerkRepository(Repository<PerkEffect> perkEffectRepository) {
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    public void save(Perk perk) {
        perk.getEffects(perk.getLevel()).forEach(perkEffectRepository::save);
        super.save(perk);
    }
}
