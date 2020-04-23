package de.midorlo.relentless.repository.dep;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.perk.Perk;

public class CellRepository extends YamlRepository<Cell> {

    private final YamlRepository<Perk> perkRepository;

    public CellRepository(YamlRepository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Cell cell) {
        cell.getPerks().forEach(perkRepository::save);
        super.save(cell);
    }
}
