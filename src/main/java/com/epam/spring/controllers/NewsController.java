package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.model.User;
import com.epam.spring.dao.UserDAO;
import com.epam.spring.services.NewsService;
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

    private final NewsService newsService;
    private final UserDAO userDAO;

    public NewsController(NewsService newsService, UserDAO userDAO) {
        this.newsService = newsService;
        this.userDAO = userDAO;
    }

    @GetMapping("/{newsId}/show")
    public String showById(@PathVariable String newsId,
                           @PathVariable String userId, Model model) {
        User user = userDAO.findById(Long.valueOf(userId));
        News news = newsService.findById(Long.valueOf(newsId));
        user.getNews().add(news);
        news.setAuthor(user);
        model.addAttribute("news", news);
        return "news/show";
    }

    @GetMapping("/new")
    public String initCreationNews(User user, Model model) {
        News news = new News();
        user.getNews().add(news);
        news.setAuthor(user);

        model.addAttribute("news", news);
        return "news/createOrUpdateNewsForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return "news/createOrUpdateNewsForm";
        } else {
            String surname = news.getAuthor().getLastName();
            User user = userDAO.findByLastName(surname);
            news.setAuthor(user);
            user.getNews().add(news);

            newsService.save(news);
            return "redirect:/users/" + news.getAuthor().getId() + "/newsListByAuthor";
        }
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateForm(@PathVariable String newsId,
                                 @PathVariable String userId, Model model) {
        User user = userDAO.findById(Long.valueOf(userId));
        News news = newsService.findById(Long.valueOf(newsId));
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

            newsService.update(news);
            return "redirect:/users/" + news.getAuthor().getId() + "/newsListByAuthor";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting news with id: " + id);

        newsService.deleteById(Long.valueOf(id));

        return "index";
    }
}
