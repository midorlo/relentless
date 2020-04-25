package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.Perk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PerkRepository extends CrudRepository<Perk, Long> {

    List<Perk> findAll();

    List<Perk> findAllByName(String name);

    Optional<Perk> findByNameAndLevel(String name, Integer level);
}
