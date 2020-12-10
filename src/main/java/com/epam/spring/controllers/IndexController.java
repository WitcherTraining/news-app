package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.model.User;
import com.epam.spring.dao.UserDAO;
import com.epam.spring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api")
public class IndexController {

    private final NewsService newsService;
    private final UserDAO userDAO;

    public IndexController(NewsService newsService, UserDAO userDAO) {
        this.newsService = newsService;
        this.userDAO = userDAO;
    }

    @GetMapping({"", "/", "index"})
    public String getIndexPage(Model model){

        model.addAttribute("news", newsService.findAll());

        return "index";
    }

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsService.findAll();
    }

    @GetMapping("/showusers")
    public List<User> listUsers(){
        return userDAO.findAll();
    }
}
