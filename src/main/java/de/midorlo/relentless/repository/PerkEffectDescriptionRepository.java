package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.PerkEffectDescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PerkEffectDescriptionRepository extends CrudRepository<PerkEffectDescription, Long> {

    @Override
    List<PerkEffectDescription> findAll();
    Optional<PerkEffectDescription> findById(long id);
}