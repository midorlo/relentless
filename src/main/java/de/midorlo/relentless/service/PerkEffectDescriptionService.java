package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.PerkEffectDescription;
import de.midorlo.relentless.repository.PerkEffectDescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerkEffectDescriptionService {

    private final PerkEffectDescriptionRepository repository;

    public PerkEffectDescriptionService(PerkEffectDescriptionRepository repository) {
        this.repository = repository;
    }

    public List<PerkEffectDescription> findAll() {
        return repository.findAll();
    }
}
