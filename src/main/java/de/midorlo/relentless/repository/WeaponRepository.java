package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.gear.Weapon;
import org.springframework.data.repository.CrudRepository;

public interface WeaponRepository extends CrudRepository<Weapon, String> {
    Weapon findByName(String name);
}
