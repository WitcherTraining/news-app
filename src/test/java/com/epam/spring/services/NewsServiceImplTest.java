package com.epam.spring.services;

import com.epam.spring.model.News;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NewsServiceImplTest {

    @Mock
    private NewsService newsService;

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
        when(newsService.findAll()).thenReturn(newsList);
        List<News> news = newsService.findAll();
        Assert.assertEquals(news, newsList);
        verify(newsService, times(1)).findAll();
    }

    @Test
    public void findById() {
        when(newsService.findById(news1.getId())).thenReturn(news1);
        News foundedNews = newsService.findById(news1.getId());
        Assert.assertEquals(foundedNews, news1);
        verify(newsService, times(1)).findById(news1.getId());
    }

    @Test
    public void save() {
        when(newsService.save(news)).thenReturn(news);
        News savedNews = newsService.save(news);
        Assert.assertEquals(savedNews, news);
    }

    @Test
    public void saveOrUpdateNews() {
        when(newsService.saveOrUpdateNews(news)).thenReturn(news);
        News savedNews = newsService.saveOrUpdateNews(news);
        Assert.assertEquals(savedNews, news);
    }

    @Test
    public void update() {
        newsService.update(news);
        verify(newsService, times(1)).update(news);
    }

    @Test
    public void delete() {
        newsService.delete(news1);
        verify(newsService, times(1)).delete(news1);
    }

    @Test
    public void deleteById() {
        newsService.deleteById(news1.getId());
        verify(newsService, times(1)).deleteById(anyLong());
    }
}