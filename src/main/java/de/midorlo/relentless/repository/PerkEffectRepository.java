package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.PerkEffect;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerkEffectRepository extends CrudRepository<PerkEffect, Long> {

    List<PerkEffect> findAllByName(String name);
    List<PerkEffect> findAllByPerkName(String name);
    PerkEffect findByNameAndLevel(String name, Integer level);
    PerkEffect findById(long id);
}