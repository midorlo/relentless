package de.midorlo.relentless.repository.yaml;

import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.gear.Weapon;

public class WeaponYamlRepository extends YamlRepository<Weapon> {

    private final YamlRepository<Perk> perkRepository;

    public WeaponYamlRepository(YamlRepository<Perk> perkRepository) {
        super();
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Weapon obj) {
        obj.getPerks().forEach(perkRepository::save);
        super.save(obj);
    }
}
