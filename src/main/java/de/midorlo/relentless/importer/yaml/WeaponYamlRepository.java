package de.midorlo.relentless.importer.yaml;

import de.midorlo.relentless.domain.Weapon;
import de.midorlo.relentless.domain.Perk;

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
