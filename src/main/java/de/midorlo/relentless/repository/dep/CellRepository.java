package de.midorlo.relentless.repository.dep;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.perk.Perk;

public class CellRepository extends Repository<Cell> {

    private final Repository<Perk> perkRepository;

    public CellRepository(Repository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Cell cell) {
        cell.getPerks().forEach(perkRepository::save);
        super.save(cell);
    }
}
