package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WeaponAttackRepository extends CrudRepository<Skill, Long> {

    Skill findByName(String name);

    @Override
    List<Skill> findAll();
    @Override
    Optional<Skill> findById(Long id);
}
