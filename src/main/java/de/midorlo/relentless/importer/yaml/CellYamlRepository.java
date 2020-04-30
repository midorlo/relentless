package de.midorlo.relentless.importer.yaml;

import de.midorlo.relentless.domain.Cell;
import de.midorlo.relentless.domain.Perk;

public class CellYamlRepository extends YamlRepository<Cell> {

    private final YamlRepository<Perk> perkRepository;

    public CellYamlRepository(YamlRepository<Perk> perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Override
    public void save(Cell cell) {
        cell.getPerks().forEach(perkRepository::save);
        super.save(cell);
    }
}
