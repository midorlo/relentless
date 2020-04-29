package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.perk.PerkEffectValue;
import de.midorlo.relentless.repository.PerkEffectValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerkEffectValueService {

    private final PerkEffectValueRepository repository;

    public PerkEffectValueService(PerkEffectValueRepository repository) {
        this.repository = repository;
    }

    public List<PerkEffectValue> findAll() {
        return repository.findAll();
    }
}
