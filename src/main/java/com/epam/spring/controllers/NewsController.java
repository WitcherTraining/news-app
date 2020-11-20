package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.model.User;
import com.epam.spring.services.dao.NewsDAO;
import com.epam.spring.services.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/users/{userId}/news")
@Controller
public class NewsController {

    private final NewsDAO newsDAO;
    private final UserDAO userDAO;

    public NewsController(NewsDAO newsDAO, UserDAO userDAO) {
        this.newsDAO = newsDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/{newsId}/show")
    public String showById(@PathVariable String newsId,
                           @PathVariable String userId, Model model) {
        User user = userDAO.findById(Long.valueOf(userId));
        News news = newsDAO.findById(Long.valueOf(newsId));
        user.getNews().add(news);
        news.setAuthor(user);
        model.addAttribute("news", news);
        return "news/show";
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateForm(@PathVariable String newsId,
                                 @PathVariable String userId, Model model) {
        User user = userDAO.findById(Long.valueOf(userId));
        News news = newsDAO.findById(Long.valueOf(newsId));
        user.getNews().add(news);
        news.setAuthor(user);
        model.addAttribute("news", news);
        return "news/createOrUpdateNewsForm";
    }

    @GetMapping("/new")
    public String addNews(User user, Model model) {
        News news = new News();
        user.getNews().add(news);
        news.setAuthor(user);
        model.addAttribute("news", news);
        return "news/createOrUpdateNewsForm";
    }

    @PostMapping("{newsId}/edit")
    public String processUpdateForm(@Valid News news, User user, BindingResult result, Model model) {
        if (result.hasErrors()){
            news.setAuthor(user);
            model.addAttribute("news", news);
            return "news/createOrUpdateNewsForm";
        } else {
            user.getNews().add(news);
            newsDAO.save(news);
            return "redirect:/users/" + news.getAuthor().getId() + "/newsListByAuthor";
        }
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return "news/createOrUpdateNewsForm";
        } else {
            String surname = news.getAuthor().getLastName();
            User user = userDAO.findByLastName(surname);
            System.out.println(user.getFirstName());
            news.setAuthor(user);
            newsDAO.save(news);
            user.getNews().add(news);
            return "redirect:/news/" + news.getId() + "/index";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting news with id: " + id);

        newsDAO.deleteById(Long.valueOf(id));

        return "index";
    }
}
