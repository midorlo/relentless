package de.midorlo.relentless.controller;

import de.midorlo.relentless.domain.cell.CellType;
import de.midorlo.relentless.repository.CellTypeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CellTypeController {

    private final CellTypeRepository repository;

    public CellTypeController(CellTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cellTypes")
    public CollectionModel<EntityModel<CellType>> all() {
        List<EntityModel<CellType>> entities = repository.findAll().stream()
                .map(entity -> new EntityModel<>(
                        entity,
                        linkTo(methodOn(CellTypeController.class).one(entity.getName())).withSelfRel(),
                        linkTo(methodOn(CellTypeController.class).all()).withRel("entities"))
                ).collect(Collectors.toList());
        return new CollectionModel<>(entities,
                linkTo(methodOn(CellTypeController.class).all()).withSelfRel());
    }

    @GetMapping("/cellTypes/{name}")
    EntityModel<CellType> one(@PathVariable String name) {
        CellType cellType = repository.findByName(name).orElseThrow(() -> new RuntimeException("unknown cell type"));
        return new EntityModel<>(cellType,
                linkTo(methodOn(CellTypeController.class).one(name)).withSelfRel(),
                linkTo(methodOn(CellTypeController.class).all()).withRel("cellTypes"));
    }

}
