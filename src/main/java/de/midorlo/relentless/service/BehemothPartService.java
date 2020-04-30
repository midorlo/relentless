package de.midorlo.relentless.service;

import de.midorlo.relentless.domain.BehemothPart;
import de.midorlo.relentless.repository.BehemothPartRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class BehemothPartService implements Serializable {

    private final BehemothPartRepository repository;

    public BehemothPartService(BehemothPartRepository repository) {
        this.repository = repository;
    }

    public List<BehemothPart> all() {
        return repository.findAll();
    }

    public Optional<BehemothPart> one(Long id) {
        return repository.findById(id);
    }
}

