package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.Weapon;

public class WeaponRepository extends Repository<Weapon> {

    private final Repository<Perk> perkRepository;

    public WeaponRepository(Repository<Perk> perkRepository) {
        super();
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Weapon obj) {
        obj.getPerks().forEach(perkRepository::save);
        super.save(obj);
    }
}
