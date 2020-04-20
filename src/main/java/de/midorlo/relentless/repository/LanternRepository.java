package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Lantern;
import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;

public class LanternRepository extends Repository<Lantern> {

    private Repository<Perk> perkRepository;
    private Repository<PerkEffect> perkEffectRepository;

    public LanternRepository(Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }
}
