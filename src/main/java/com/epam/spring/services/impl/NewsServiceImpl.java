package com.epam.spring.services.impl;

import com.epam.spring.model.News;
import com.epam.spring.services.NewsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class NewsServiceImpl extends AbstractService<News, Long> implements NewsService {

    @Override
    public Set<News> findAll() {
        return super.findAll();
    }

    @Override
    public News findById(Long id) {
        return super.findById(id);
    }

    @Override
    public News save(News object) {
        return super.save(object);
    }

    @Override
    public void delete(News object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
