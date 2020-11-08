package com.epam.spring.repositories;

import java.util.List;

public interface Repository<T> {

    void delete(Long id);

    List getAll();

    T getById(Long id);

    void update(T object);

    void add(T object);

}
