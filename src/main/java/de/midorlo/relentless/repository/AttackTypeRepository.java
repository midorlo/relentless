package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.AttackType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AttackTypeRepository  extends CrudRepository<AttackType, String> {
    @Override
    List<AttackType> findAll();
    Optional<AttackType> findByName(String name);
}
