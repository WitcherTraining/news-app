package com.epam.spring.services;

import com.epam.spring.dao.NewsDAO;
import com.epam.spring.model.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsDAO newsDAO;

    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<News> findAll() {
        return newsDAO.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsDAO.findById(id);
    }

    @Override
    public News save(News object) {
        return newsDAO.save(object);
    }

    @Override
    public News saveOrUpdateNews(News news) {
        return newsDAO.saveOrUpdateNews(news);
    }

    @Override
    public void update(News object) {
        newsDAO.update(object);
    }

    @Override
    public void delete(News object) {
        newsDAO.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        newsDAO.deleteById(id);
    }
}
