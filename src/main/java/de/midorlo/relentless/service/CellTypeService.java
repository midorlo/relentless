package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.cell.CellType;
import de.midorlo.relentless.repository.CellTypeRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class CellTypeService implements Serializable {

    private final CellTypeRepository repository;

    public CellTypeService(CellTypeRepository repository) {
        this.repository = repository;
    }

    public List<CellType> all() {
        return repository.findAll();
    }
}

