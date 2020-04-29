package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.behemoth.Behemoth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BehemothRepository extends CrudRepository<Behemoth, Long> {
    List<Behemoth> findAll();
}
