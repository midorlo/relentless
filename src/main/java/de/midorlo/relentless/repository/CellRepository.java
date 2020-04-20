package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.items.Cell;
import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;

public class CellRepository extends Repository<Cell> {

    private Repository<Perk> perkRepository;
    private Repository<PerkEffect> perkEffectRepository;

    public CellRepository(Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }


}
