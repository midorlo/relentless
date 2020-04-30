package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Moveset;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovesetRepository extends CrudRepository<Moveset, Long> {

    @Override
    List<Moveset> findAll();
    Optional<Moveset> getById(Long id);
}
