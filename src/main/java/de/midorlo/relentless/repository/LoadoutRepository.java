package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Loadout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoadoutRepository extends CrudRepository<Loadout, Long> {

    @Override
    List<Loadout> findAll();

    Optional<Loadout> getById(Long id);
}
