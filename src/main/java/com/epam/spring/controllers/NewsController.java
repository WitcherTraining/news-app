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
@RequestMapping("/news")
@Controller
public class NewsController {

    private final NewsDAO newsDAO;
    private final UserDAO userDAO;

    public NewsController(NewsDAO newsDAO, UserDAO userDAO) {
        this.newsDAO = newsDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("news", newsDAO.findById(Long.valueOf(id)));
        return "news/show";
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateForm(@PathVariable String newsId, Model model){
        model.addAttribute(newsDAO.findById(Long.valueOf(newsId)));
        return "news/createOrUpdateNewsForm";
    }

    @GetMapping("/new")
    public String addNews(Model model){
        model.addAttribute("news", new News());
        return "news/createOrUpdateNewsForm";
    }

    @PostMapping("{newsId}/show")
    public String processUpdateForm(@Valid News news, User user, Model model, BindingResult result){
        if (result.hasErrors()) {
            news.setAuthor(user);
            model.addAttribute("news", news);
            return "news/createOrUpdateNewsForm";
        } else {
            user.getNews().add(news);
            News savedNews = newsDAO.save(news);
            return "redirect:/news/" + savedNews.getId() + "index";
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
            News savedNews = newsDAO.save(news);
            user.getNews().add(savedNews);
            return "redirect:/news/" + savedNews.getId() + "index";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting news with id: " + id);

        newsDAO.deleteById(Long.valueOf(id));

        return "index";
    }
}
