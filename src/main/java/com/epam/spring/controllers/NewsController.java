package com.epam.spring.controllers;

import com.epam.spring.model.Category;
import com.epam.spring.model.News;
import com.epam.spring.model.User;
import com.epam.spring.services.dao.CategoryDAO;
import com.epam.spring.services.dao.NewsDAO;
import com.epam.spring.services.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/users/{userId}/news")
@Controller
public class NewsController {

    private final NewsDAO newsDAO;
    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;

    public NewsController(NewsDAO newsDAO, UserDAO userDAO, CategoryDAO categoryDAO) {
        this.newsDAO = newsDAO;
        this.userDAO = userDAO;
        this.categoryDAO = categoryDAO;
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

    @GetMapping("/new")
    public String initCreationNews(User user, Model model) {
        News news = new News();
        user.getNews().add(news);
        news.setAuthor(user);

        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);

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

            newsDAO.save(news);
            return "redirect:/users/" + news.getAuthor().getId() + "/newsListByAuthor";
        }
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateForm(@PathVariable String newsId,
                                 @PathVariable String userId, Model model) {
        User user = userDAO.findById(Long.valueOf(userId));
        News news = newsDAO.findById(Long.valueOf(newsId));
        user.getNews().add(news);
        news.setAuthor(user);
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
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

            newsDAO.update(news);
            return "redirect:/users/" + news.getAuthor().getId() + "/newsListByAuthor";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting news with id: " + id);

        newsDAO.deleteById(Long.valueOf(id));

        return "index";
    }
}
