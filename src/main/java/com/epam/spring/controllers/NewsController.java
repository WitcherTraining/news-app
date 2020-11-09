package com.epam.spring.controllers;

import com.epam.spring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/news")
@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        model.addAttribute("news", newsService.findAll());

        return "news/index";
    }
}
