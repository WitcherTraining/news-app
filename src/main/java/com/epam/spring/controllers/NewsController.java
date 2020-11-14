package com.epam.spring.controllers;

import com.epam.spring.model.News;
import com.epam.spring.model.User;
import com.epam.spring.services.NewsService;
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

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("news", newsService.findById(Long.valueOf(id)));
        return "news/show";
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateForm(@PathVariable String newsId, Model model){
        model.addAttribute(newsService.findById(Long.valueOf(newsId)));
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
            News savedNews = newsService.save(news);
            return "redirect:/news/" + savedNews.getId() + "/show";
        }
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return "news/createOrUpdateNewsForm";
        } else {
            News savedNews = newsService.save(news);
            return "redirect:/news/" + savedNews.getId() + "/show";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting news with id: " + id);

        newsService.deleteById(Long.valueOf(id));

        return "redirect:/index";
    }
}
