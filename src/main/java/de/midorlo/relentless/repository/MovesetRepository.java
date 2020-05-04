package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.SkillSet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovesetRepository extends CrudRepository<SkillSet, Long> {

    @Override
    List<SkillSet> findAll();
    Optional<SkillSet> getById(Long id);
}
