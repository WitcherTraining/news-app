package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/news")
@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("oneNews", newsService.findById(Long.valueOf(id)));
        return "news/show";
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateNewsForm(@PathVariable String newsId, Model model){
        model.addAttribute(newsService.findById(Long.valueOf(newsId)));
        return "news/createOrUpdateNewsForm";
    }

    @GetMapping("/new")
    public String addNews(Model model){
        model.addAttribute("news", new News());
        return "news/createOrUpdateNewsForm";
    }


}
