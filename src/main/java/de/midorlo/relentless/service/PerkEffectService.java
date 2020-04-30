package de.midorlo.relentless.service;


import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.repository.PerkEffectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerkEffectService {

    private final PerkEffectRepository repository;

    public PerkEffectService(PerkEffectRepository repository) {
        this.repository = repository;
    }

    public List<PerkEffect> findAll() {
        return repository.findAll();
    }

    public Optional<PerkEffect> one(Long id) {
        return repository.findById(id);
    }
}
