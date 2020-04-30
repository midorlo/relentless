package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.CellType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CellTypeRepository extends CrudRepository<CellType, Long> {
    List<CellType> findAll();
    Optional<CellType> findByName(String name);
}
