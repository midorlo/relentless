package de.midorlo.relentless.repository.jpa;

import de.midorlo.relentless.domain.behemoth.Behemoth;
import org.springframework.data.repository.CrudRepository;

public interface BehemothRepository extends CrudRepository<Behemoth, Long> {
}
