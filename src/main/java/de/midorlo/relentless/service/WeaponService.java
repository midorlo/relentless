package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Weapon;
import de.midorlo.relentless.repository.WeaponRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class WeaponService implements Serializable {

    private final WeaponRepository repository;

    public WeaponService(WeaponRepository repository) {
        this.repository = repository;
    }

    public List<Weapon> all() {
        return repository.findAll();
    }

    public Optional<Weapon> one(String name) {
        return repository.findByName(name);
    }

}

