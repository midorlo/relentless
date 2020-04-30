package de.midorlo.relentless.importer.yaml;

import de.midorlo.relentless.domain.Lantern;
import de.midorlo.relentless.domain.Perk;

public class LanternRepository extends YamlRepository<Lantern> {

    private final YamlRepository<Perk> perkRepository;

    public LanternRepository(YamlRepository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Lantern lantern) {
        lantern.getPerks().forEach(perkRepository::save);
        super.save(lantern);
    }
}
