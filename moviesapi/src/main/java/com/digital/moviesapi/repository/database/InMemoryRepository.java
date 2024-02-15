package com.digital.moviesapi.repository.database;

import java.util.*;

import com.digital.moviesapi.model.EntityBase;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;

/**
 * A generic implementation of the Spring CrudRepository interface
 * that holds an in-memory map of any data type, and allows for basic CRUD operations on the data. In a typical
 * enterprise application we would use a database solution to store and query our data from.
 * <p>
 * Read more about the different types of Spring data repository abstractions here:
 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 * <p>
 *
 * @param <T>  the type of the entities handled by this repository
 * @param <ID> the type of the entities primary keys
 */
@SuppressWarnings("NullableProblems")
public class InMemoryRepository<T extends EntityBase, ID extends Integer> implements CrudRepository<T, ID> {

    private final Map<Integer, T> entities = new HashMap<>();

    public InMemoryRepository() {
    }

    @Override
    public <S extends T> S save(S entity) {
        entities.put(entity.id, entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.of(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        return entities.values();
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }

    @Override
    public long count() {
        return entities.size();
    }

    @Override
    public void deleteById(ID id) {
        T removedEntity = entities.remove(id);
        if (removedEntity == null) {
            throw new EmptyResultDataAccessException("Entity with id " + id + " not found", 1);
        }
    }

    @Override
    public void delete(T entity) {
        entities.remove(entity.id);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entitiesToDelete) {
        for (T entity : entitiesToDelete) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> iterable) {
        return null;
    }
}
