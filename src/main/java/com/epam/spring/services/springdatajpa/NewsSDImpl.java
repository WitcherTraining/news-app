package com.epam.spring.services.springdatajpa;

import com.epam.spring.model.News;
import com.epam.spring.repositories.NewsRepository;
import com.epam.spring.repositories.UserRepository;
import com.epam.spring.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class NewsSDImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsSDImpl(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<News> findAll() {
        Set<News> news = new HashSet<>();
        newsRepository.findAll().forEach(news::add);
        return news;
    }

    @Override
    public News findById(Long aLong) {
        return newsRepository.findById(aLong).orElse(null);
    }

    @Override
    public News save(News object) {
        return newsRepository.save(object);
    }

    @Override
    public void delete(News object) {
        newsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        newsRepository.deleteById(aLong);
    }
}
