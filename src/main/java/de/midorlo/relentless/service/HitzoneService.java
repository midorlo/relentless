package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Hitzone;
import de.midorlo.relentless.repository.HitzoneRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class HitzoneService implements Serializable {

    private final HitzoneRepository repository;

    public HitzoneService(HitzoneRepository repository) {
        this.repository = repository;
    }

    public List<Hitzone> all() {
        return repository.findAll();
    }
    public Optional<Hitzone> one(Long id    ) { return repository.findById(id);}
}

