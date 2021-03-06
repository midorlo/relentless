package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Armor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BehemothRepository extends CrudRepository<Armor, String> {

    List<Armor> findAll();
//    Armor findByName(String name);
//    Armor getById(Long id);
}
