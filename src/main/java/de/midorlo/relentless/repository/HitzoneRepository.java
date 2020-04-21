package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.behemoth.Hitzone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HitzoneRepository extends CrudRepository<Hitzone, Long> {

    List<Hitzone> findAllByName(String name);

    Hitzone findById(long id);
}