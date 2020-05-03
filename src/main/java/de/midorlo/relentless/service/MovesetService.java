package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.SkillSet;
import de.midorlo.relentless.repository.MovesetRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class MovesetService implements Serializable {

    private final MovesetRepository repository;

    public MovesetService(MovesetRepository repository) {
        this.repository = repository;
    }

    public List<SkillSet> all() {
        return repository.findAll();
    }

    public Optional<SkillSet> one(Long id) {
        return repository.findById(id);
    }
}

