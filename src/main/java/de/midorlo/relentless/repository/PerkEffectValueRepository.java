package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.PerkEffectValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerkEffectValueRepository extends CrudRepository<PerkEffectValue, Long> {

    @Override
    List<PerkEffectValue> findAll();

    PerkEffectValue findById(long id);
}