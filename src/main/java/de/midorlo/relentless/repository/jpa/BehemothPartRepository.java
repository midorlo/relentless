package de.midorlo.relentless.repository.jpa;

import de.midorlo.relentless.domain.behemoth.BehemothPart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BehemothPartRepository extends CrudRepository<BehemothPart, Long> {

    List<BehemothPart> findAllByHitzoneName(String name);

    BehemothPart findById(long id);

}