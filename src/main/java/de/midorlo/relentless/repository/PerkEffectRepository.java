package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.PerkEffect;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerkEffectRepository extends CrudRepository<PerkEffect, Long> {
    @Override
    List<PerkEffect> findAll();
}