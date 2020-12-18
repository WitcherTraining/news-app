package com.epam.spring.services;

import com.epam.spring.model.News;

import java.util.List;

public interface NewsService {
    List<News> findAll();

    News findById(Long id);

    News save(News object);

    News saveOrUpdateNews(News news);

    void update(News object);

    void delete(News object);

    void deleteById(Long id);
}
