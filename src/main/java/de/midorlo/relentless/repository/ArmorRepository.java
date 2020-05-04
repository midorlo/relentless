package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Armor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArmorRepository  extends CrudRepository<Armor, String> {
    @Override
    List<Armor> findAll();
    Armor findByName(String name);
}
