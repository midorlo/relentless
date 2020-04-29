package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.perk.PerkEffectDescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerkEffectDescriptionRepository extends CrudRepository<PerkEffectDescription, Long> {

    @Override
    List<PerkEffectDescription> findAll();
    PerkEffectDescription findById(long id);
}