package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Armor;
import de.midorlo.relentless.repository.ArmorRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ArmorService implements Serializable {

    private final ArmorRepository repository;

    public ArmorService(ArmorRepository repository) {
        this.repository = repository;
    }

    public List<Armor> all() {
        return repository.findAll();
    }

    public Optional<Armor> one(String name) {
        return repository.findById(name);
    }
}

