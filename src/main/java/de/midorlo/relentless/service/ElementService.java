package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.repository.ElementRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ElementService implements Serializable {

    private final ElementRepository repository;

    public ElementService(ElementRepository repository) {
        this.repository = repository;
    }

    public List<Element> all() {
        return repository.findAll();
    }
    public Optional<Element> one(String name) { return repository.findByName(name);}

//    public Optional<WeaponAttack> one(Long id    ) { return repository.findById(id);}
//    public Optional<WeaponAttack> one(String name) { return repository.findByName(name);}
}

