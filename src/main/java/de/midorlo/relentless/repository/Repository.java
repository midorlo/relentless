package de.midorlo.relentless.repository;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Global Repository that is used like a Database (until I implement a real one //todo).
 */
@ToString
public class Repository<T> {

    private List<T> objects = new ArrayList<>();

    public List<T> findAll() {
        return objects;
    }

    public boolean contains(T obj) {
        return objects.contains(obj);
    }

    public List<T> findBy(Predicate<T> predicate) {
        return objects.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public void save(T obj) {
        if (!contains(obj)) {
            findAll().add(obj);
        }
    }

    public void save(List<T> objs) {
        objs.forEach(this::save);
    }
}
