package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.CellSocket;
import de.midorlo.relentless.repository.CellSocketRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class CellSocketService implements Serializable {

    private final CellSocketRepository repository;

    public CellSocketService(CellSocketRepository repository) {
        this.repository = repository;
    }

    public List<CellSocket> all() {
        return repository.findAll();
    }
    public Optional<CellSocket> one(Long id    ) { return repository.findById(id);}
}

