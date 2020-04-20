package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;
import de.midorlo.relentless.domain.items.Weapon;

public class WeaponRepository extends Repository<Weapon> {

    private Repository<Perk> perkRepository;
    private Repository<PerkEffect> perkEffectRepository;

    public WeaponRepository(Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        super();
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }
}
