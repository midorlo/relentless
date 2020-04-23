package de.midorlo.relentless.repository.dep;

import de.midorlo.relentless.domain.item.Lantern;
import de.midorlo.relentless.domain.perk.Perk;

public class LanternRepository extends Repository<Lantern> {

    private final Repository<Perk> perkRepository;

    public LanternRepository(Repository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Lantern lantern) {
        lantern.getPerks().forEach(perkRepository::save);
        super.save(lantern);
    }
}
