package de.midorlo.relentless.controller;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.repository.CellRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CellController {

    private final CellRepository repository;

    public CellController(CellRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cells")
    List<Cell> all() {
        return repository.findAll();
    }

    @GetMapping("/cells/{name}")
    EntityModel<Cell> one(@PathVariable String name) {
        Cell cell = repository.findByName(name).orElseThrow(() -> new RuntimeException("unknown cell type"));
        return new EntityModel<>(cell,
                linkTo(methodOn(CellController.class).one(name)).withSelfRel(),
                linkTo(methodOn(CellController.class).all()).withRel("cells"));
    }
}
