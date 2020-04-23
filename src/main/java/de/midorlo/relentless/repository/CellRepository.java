package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.cell.Cell;
import org.springframework.data.repository.CrudRepository;

public interface CellRepository extends CrudRepository<Cell, Long> {
}
