package com.epam.spring.services;

import java.util.Set;

// This interface is mimic to CrudRepository<T, ID>
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
