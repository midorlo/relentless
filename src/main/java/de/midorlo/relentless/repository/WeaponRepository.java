package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.gear.Weapon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponRepository extends CrudRepository<Weapon, String> {
    Weapon findByName(String name);
    List<Weapon> findAll();
}
