package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.Perk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerkRepository extends CrudRepository<Perk, Long> {
    List<Perk> findAllByName(String name);
}
