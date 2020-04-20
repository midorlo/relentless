package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Armor;
import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;

public class ArmorRepository extends Repository<Armor>{

    private Repository<Perk> perkRepository;
    private Repository<PerkEffect> perkEffectRepository;

    public ArmorRepository(Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }
}
