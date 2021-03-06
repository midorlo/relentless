package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.repository.PerkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerkService {

    private PerkRepository repository;

    public PerkService(PerkRepository repository) {
        this.repository = repository;
    }

    public List<Perk> findAll() {
        return repository.findAll();
    }

    public Optional<Perk> one(Long id) {
        return repository.findById(id);
    }
}
