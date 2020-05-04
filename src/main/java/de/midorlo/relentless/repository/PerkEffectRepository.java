package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.PerkEffect;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface PerkEffectRepository extends CrudRepository<PerkEffect, Long> {
    @Override
    List<PerkEffect> findAll();
    Optional<PerkEffect> findByName(String name);
}