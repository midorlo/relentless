package de.midorlo.relentless.repository;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
        return findAll().contains(obj);
    }

    public void save(T obj) {
        findAll().add(obj);
    }
}
