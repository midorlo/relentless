package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.PerkEffect;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerkEffectRepository extends CrudRepository<PerkEffect, Long> {

    List<PerkEffect> findAllByName(String name);
    List<PerkEffect> findAllByNameAndLevel(String name, Integer level);
    List<PerkEffect> findAllByPerkName(String name);
    PerkEffect findById(long id);
}