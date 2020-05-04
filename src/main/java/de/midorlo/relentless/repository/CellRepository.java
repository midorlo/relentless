package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Cell;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CellRepository extends CrudRepository<Cell, Long> {

    List<Cell> findAll();

    Optional<Cell> findByName(String name);

    Optional<Cell> readByNameAndLevel(String name, Integer level);
}
