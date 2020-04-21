package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Armor;
import de.midorlo.relentless.domain.items.Perk;

public class ArmorRepository extends Repository<Armor>{

    private final Repository<Perk> perkRepository;

    public ArmorRepository(Repository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Armor armor) {
        armor.getPerks().forEach(perkRepository::save);
        super.save(armor);
    }
}
