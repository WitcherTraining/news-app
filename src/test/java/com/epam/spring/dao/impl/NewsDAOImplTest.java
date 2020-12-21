package com.epam.spring.dao.impl;

import com.epam.spring.dao.NewsDAO;
import com.epam.spring.model.News;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NewsDAOImplTest {

    @Mock
    private NewsDAO newsDAO;

    public News news;

    List<News> newsList;
    News news1;
    News news2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        news1 = new News();
        news1.setId(1L);
        news1.setTitle("Title");
        news1.setBriefContent("Brief content");
        news1.setContent("Content");
        news1.setDate(new Date());

        news2 = new News();
        news2.setId(2L);
        news2.setTitle("Title");
        news2.setBriefContent("Brief content");
        news2.setContent("Content");
        news2.setDate(new Date());

        newsList = new ArrayList<>();
        newsList.add(news1);
        newsList.add(news2);
    }

    @Test
    public void findAll() {
        when(newsDAO.findAll()).thenReturn(newsList);
        List<News> news = newsDAO.findAll();
        Assert.assertEquals(news, newsList);
        verify(newsDAO, times(1)).findAll();
    }

    @Test
    public void findById() {
        when(newsDAO.findById(news1.getId())).thenReturn(news1);
        News foundedNews = newsDAO.findById(news1.getId());
        Assert.assertEquals(foundedNews, news1);
        verify(newsDAO, times(1)).findById(news1.getId());
    }

    @Test
    public void save() {
        when(newsDAO.save(news)).thenReturn(news);
        News savedNews = newsDAO.save(news);
        Assert.assertEquals(savedNews, news);
    }

    @Test
    public void saveOrUpdateNews() {
        when(newsDAO.saveOrUpdateNews(news)).thenReturn(news);
        News savedNews = newsDAO.saveOrUpdateNews(news);
        Assert.assertEquals(savedNews, news);
    }

    @Test
    public void update() {
        newsDAO.update(news);
        verify(newsDAO, times(1)).update(news);
    }

    @Test
    public void delete() {
        newsDAO.delete(news1);
        verify(newsDAO, times(1)).delete(news1);
    }

    @Test
    public void deleteById() {
        newsDAO.deleteById(news1.getId());
        verify(newsDAO, times(1)).deleteById(anyLong());
    }
}