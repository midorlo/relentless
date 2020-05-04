package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Weapon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WeaponRepository extends CrudRepository<Weapon, String> {
    @Override
    List<Weapon> findAll();
    Optional<Weapon> findByName(String name);
}
