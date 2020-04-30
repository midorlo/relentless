package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.AttackType;
import de.midorlo.relentless.repository.AttackTypeRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class AttackTypeService implements Serializable {

    private final AttackTypeRepository repository;

    public AttackTypeService(AttackTypeRepository repository) {
        this.repository = repository;
    }

    public List<AttackType> all() {
        return repository.findAll();
    }

    public Optional<AttackType> one(String name) {
        return repository.findByName(name);
    }
}

