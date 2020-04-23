package de.midorlo.relentless.repository.dep;

import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.gear.Weapon;

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
