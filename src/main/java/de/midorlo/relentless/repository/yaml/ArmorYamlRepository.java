package de.midorlo.relentless.repository.yaml;

import de.midorlo.relentless.domain.gear.Armor;
import de.midorlo.relentless.domain.perk.Perk;

public class ArmorYamlRepository extends YamlRepository<Armor> {

    private final YamlRepository<Perk> perkRepository;

    public ArmorYamlRepository(YamlRepository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Armor armor) {
        armor.getPerks().forEach(perkRepository::save);
        super.save(armor);
    }
}
