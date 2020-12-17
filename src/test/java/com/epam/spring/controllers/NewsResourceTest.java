package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.services.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NewsResourceTest {

    @Mock
    NewsService newsService;

    @InjectMocks
    NewsResource newsResource;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        newsResource = new NewsResource(newsService);
        mockMvc = MockMvcBuilders.standaloneSetup(newsResource).build();
    }

    @Test
    public void getAllNews() throws Exception {
        //given
        List<News> news = new ArrayList<>();
        when(newsService.findAll()).thenReturn(news);

        //when
        mockMvc.perform(get("/news-app/api/news"))
                .andExpect(status().is4xxClientError())
                .andExpect(model().attributeExists("news"));

        //then
        verify(newsService, times(1)).findAll();
    }

    @Test
    public void getTheNews() throws Exception {
        //given
        News news = new News();
        news.setId(1L);
        news.setTitle("Title");
        news.setBriefContent("Brief content");
        news.setContent("Content");
        news.setDate(new Date());

        //when
        when(newsService.findById(news.getId())).thenReturn(news);

        //then
        mockMvc.perform(get("/api/news/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(news.getTitle()));
    }

    @Test
    public void createNews() {
    }

    @Test
    public void updateNews() {
    }

    @Test
    public void deleteNews() {
    }
}