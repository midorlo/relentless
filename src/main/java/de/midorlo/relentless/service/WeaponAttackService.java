package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Skill;
import de.midorlo.relentless.repository.WeaponAttackRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class WeaponAttackService implements Serializable {

    private final WeaponAttackRepository repository;

    public WeaponAttackService(WeaponAttackRepository repository) {
        this.repository = repository;
    }

    public List<Skill> all() {
        return repository.findAll();
    }
    public Optional<Skill> one(Long id    ) { return repository.findById(id);}
}

