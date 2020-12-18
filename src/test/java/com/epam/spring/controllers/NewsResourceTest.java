package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.services.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NewsResourceTest {

    @Mock
    NewsService newsService;

    @InjectMocks
    NewsResource newsResource;

    MockMvc mockMvc;

    List<News> newsList;
    News news1;
    News news2;

    @Before
    public void setUp() throws Exception {
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

        newsResource = new NewsResource(newsService);
        mockMvc = MockMvcBuilders.standaloneSetup(newsResource).build();
    }

    @Test
    public void getAllNews() throws Exception {
        //given
        List<News> news = newsList;
        when(newsService.findAll()).thenReturn(news);

        //when
        mockMvc.perform(get("/api/news")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(news1.getId()));

        //then
        verify(newsService, times(1)).findAll();
    }

    @Test
    public void getTheNews() throws Exception {
        //given
        News news = news1;

        //when
        when(newsService.findById(news.getId())).thenReturn(news);

        //then
        mockMvc.perform(get("/api/news/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(news.getTitle()));
    }

    @Test
    public void createNews() throws Exception {
        //given
        News news = news1;

        //when
        when(newsService.save(news1)).thenReturn(news);

        //then
        mockMvc.perform(post("/api/news")
                .content("{\n" +
                        "    \"id\": \"3\",\n" +
                        "    \"title\": \"Title\",\n" +
                        "    \"briefContent\": \"brief content\",\n" +
                        "    \"content\": \"Content\",\n" +
                        "    \"newsDate\": \"2020-11-01\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNews() {
    }

    @Test
    public void deleteNews() {
    }
}