package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.Cell;
import de.midorlo.relentless.repository.CellRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class CellService implements Serializable {

    private final CellRepository repository;

    public CellService(CellRepository repository) {
        this.repository = repository;
    }

    public List<Cell> all() {
        return repository.findAll();
    }

    public Optional<Cell> one(Long id) {
        return repository.findById(id);
    }

    public Optional<Cell> one(String name) {
        return repository.findByName(name);
    }
}

