package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.cell.CellSocket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CellSocketRepository extends CrudRepository<CellSocket, Long> {
    List<CellSocket> findAll();
}
