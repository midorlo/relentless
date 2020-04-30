package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.CellType;
import de.midorlo.relentless.repository.CellTypeRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class CellTypeService implements Serializable {

    private final CellTypeRepository repository;

    public CellTypeService(CellTypeRepository repository) {
        this.repository = repository;
    }

    public List<CellType> all() {
        return repository.findAll();
    }

    public Optional<CellType> one(String name) {
        return repository.findByName(name);
    }

}

