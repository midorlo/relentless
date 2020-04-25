package de.midorlo.relentless.controller;

import de.midorlo.relentless.controller.exception.PerkNotFoundException;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.repository.PerkRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PerkController {

    private final PerkRepository repository;

    public PerkController(PerkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/perks")
    List<Perk> all() {
        return repository.findAll();
    }

    @GetMapping("/perks/{name}/{level}")
    Perk one(@PathVariable String name, @PathVariable Integer level) {
        return repository.findByNameAndLevel(name, level).orElseThrow(() -> new PerkNotFoundException(name));
    }
}
