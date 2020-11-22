package com.epam.spring.services.dao;

import com.epam.spring.model.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> findAll();

    Category findById(Long id);

    void save(Category object);

    void update(Category object);

    void delete(Category object);

    void deleteById(Long id);
}
