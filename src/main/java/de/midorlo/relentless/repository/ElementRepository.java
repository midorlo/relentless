package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Element;
import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, String> {
}
