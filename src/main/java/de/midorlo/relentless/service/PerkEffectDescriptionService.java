package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.domain.PerkEffectDescription;
import de.midorlo.relentless.repository.PerkEffectDescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerkEffectDescriptionService {

    private final PerkEffectDescriptionRepository repository;

    public PerkEffectDescriptionService(PerkEffectDescriptionRepository repository) {
        this.repository = repository;
    }

    public List<PerkEffectDescription> findAll() {
        return repository.findAll();
    }
    public Optional<PerkEffectDescription> one(Long id    ) { return repository.findById(id);}
}

