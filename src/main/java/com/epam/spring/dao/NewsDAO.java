package com.epam.spring.dao;

import com.epam.spring.model.News;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsDAO {

    List<News> findAll();

    News findById(Long id);

    News save(News object);

    void update(News object);

    void delete(News object);

    void deleteById(Long id);
}
