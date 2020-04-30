package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.AttackType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttackTypeRepository  extends CrudRepository<AttackType, String> {
    List<AttackType> findAll();
    AttackType getByName(String name);
}
