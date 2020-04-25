//package de.midorlo.relentless.controller;
//
//import de.midorlo.relentless.domain.NamedObject;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.EntityModel;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//public abstract class AbstractController<T extends NamedObject> {
//
//    private final NamedObjectRepository<T> repository;
//
//    public AbstractController(NamedObjectRepository<T> repository) {
//        this.repository = repository;
//    }
//
//    public abstract CollectionModel<EntityModel<T>> all();
//
//    public abstract EntityModel<T> one(String name);
//
//    protected final CollectionModel<EntityModel<T>> all(String mapping) {
//        List<EntityModel<T>> entities = ((List<T>) repository.findAll()).stream()
//                .map(entity -> new EntityModel<>(
//                        entity,
//                        linkTo(methodOn(getClass()).one(entity.getName())).withSelfRel(),
//                        linkTo(methodOn(AbstractController.class).all()).withRel(mapping))
//                ).collect(Collectors.toList());
//        return new CollectionModel<>(entities,
//                linkTo(methodOn(AbstractController.class).all()).withSelfRel());
//    }
//
//    protected final EntityModel<T> one(String name, String mapping) {
//        T cellType = repository.findByName(name).orElseThrow(() -> new RuntimeException("unknown cell type"));
//        return new EntityModel<>(cellType,
//                linkTo(methodOn(AbstractController.class).one(name)).withSelfRel(),
//                linkTo(methodOn(AbstractController.class).all()).withRel(mapping));
//    }
//
//}
