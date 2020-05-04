package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.PerkEffectValue;
import de.midorlo.relentless.repository.PerkEffectValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerkEffectValueService {

    private final PerkEffectValueRepository repository;

    public PerkEffectValueService(PerkEffectValueRepository repository) {
        this.repository = repository;
    }

    public List<PerkEffectValue> findAll() {
        return repository.findAll();
    }

    public Optional<PerkEffectValue> one(Long id) {
        return repository.findById(id);
    }
}
