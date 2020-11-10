package com.epam.spring.controllers;

import com.epam.spring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final NewsService newsService;

    public IndexController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping({"", "/", "index"})
    public String getIndexPage(Model model){

        model.addAttribute("news", newsService.findAll());

        return "index";
    }
}
