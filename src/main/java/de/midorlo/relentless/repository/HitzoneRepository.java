package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Hitzone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HitzoneRepository extends CrudRepository<Hitzone, Long> {

    List<Hitzone> findAllByName(String name);

    @Override
    List<Hitzone> findAll();

    Optional<Hitzone> findById(long id);
}