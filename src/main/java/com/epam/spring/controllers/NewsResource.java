package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.services.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class NewsResource {

    private final NewsService newsService;

    public NewsResource(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsService.findAll();
    }

    @GetMapping("/news/{newsId}")
    @ResponseStatus(HttpStatus.OK)
    public News getTheNews(@PathVariable Long newsId) {
        return newsService.findById(newsId);
    }

//    @PostMapping("/news")
//    public ResponseEntity<Void> createNews(@RequestBody News news) {
//
////        news.setId(6L);
//        News createdNews = newsService.save(news);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(createdNews.getId()).toUri();
//
//        return ResponseEntity.created(uri).build();
//    }

    @PostMapping(path = "/news", consumes = APPLICATION_JSON_VALUE)
//    @PostMapping("/news")
    public ResponseEntity<Void> createNews(@RequestBody News news) {

        News createdNews = newsService.saveOrUpdateNews(news);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdNews.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("news/{newsId}")
    public ResponseEntity<News> updateNews(@PathVariable Long newsId, @RequestBody News news) {
        newsService.update(news);
        return new ResponseEntity<>(news,HttpStatus.OK);
    }

    @DeleteMapping("news/{newsId}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long newsId) {
        News news = newsService.findById(newsId);

        if (news == null) {
            throw new RuntimeException("News id " + newsId + " not found");
        }

        newsService.deleteById(newsId);

        log.debug("Deleting news with id: " + newsId);
        return ResponseEntity.notFound().build();

    }
}
