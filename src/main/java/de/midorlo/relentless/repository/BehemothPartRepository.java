package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.BehemothPart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BehemothPartRepository extends CrudRepository<BehemothPart, Long> {

    List<BehemothPart> findAllByHitzoneName(String name);
    @Override
    List<BehemothPart> findAll();

    Optional<BehemothPart> findById(long id);

}