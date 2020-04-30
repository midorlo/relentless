package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.WeaponAttack;
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

    public List<WeaponAttack> all() {
        return repository.findAll();
    }
    public Optional<WeaponAttack> one(Long id    ) { return repository.findById(id);}
}

