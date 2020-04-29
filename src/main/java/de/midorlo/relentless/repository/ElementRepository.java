package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Element;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementRepository extends CrudRepository<Element, String> {
    @Override
    List<Element> findAll();
}
