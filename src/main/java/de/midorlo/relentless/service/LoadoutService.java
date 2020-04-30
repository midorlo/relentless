package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Loadout;
import de.midorlo.relentless.repository.LoadoutRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class LoadoutService implements Serializable {

    private final LoadoutRepository repository;

    public LoadoutService(LoadoutRepository repository) {
        this.repository = repository;
    }

    public List<Loadout> all() {
        return repository.findAll();
    }
    public Optional<Loadout> one(Long id    ) { return repository.findById(id);}
}

