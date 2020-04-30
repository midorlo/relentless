package de.midorlo.relentless.service;


import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.repository.PerkEffectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerkEffectService {

    private final PerkEffectRepository repository;

    public PerkEffectService(PerkEffectRepository repository) {
        this.repository = repository;
    }

    public List<PerkEffect> findAll() {
        return repository.findAll();
    }
}
