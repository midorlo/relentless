package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.PerkEffectValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PerkEffectValueRepository extends CrudRepository<PerkEffectValue, Long> {

    @Override
    List<PerkEffectValue> findAll();

    Optional<PerkEffectValue> findById(long id);
}