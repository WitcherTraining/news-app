package com.epam.spring.services.impl;

import com.epam.spring.model.Category;
import com.epam.spring.services.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {

    @Override
    public Set<Category> findAll() {
        return super.findAll();
    }

    @Override
    public Category findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Category save(Category object) {
        return super.save(object);
    }

    @Override
    public void delete(Category object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
