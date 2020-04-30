package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.WeaponAttack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WeaponAttackRepository extends CrudRepository<WeaponAttack, Long> {

    WeaponAttack findByName(String name);

    @Override
    List<WeaponAttack> findAll();
    @Override
    Optional<WeaponAttack> findById(Long id);
}
