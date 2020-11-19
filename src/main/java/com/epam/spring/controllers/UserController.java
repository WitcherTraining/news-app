package com.epam.spring.controllers;

import com.epam.spring.model.User;
import com.epam.spring.services.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("{id}/newsListByAuthor")
    public String showNewsByUser(Model model, @PathVariable String id){
        model.addAttribute("user", userDAO.findById(Long.valueOf(id)));
        return "/users/newsListByAuthor";
    }

    @GetMapping({"", "/",  "/show"})
    public String listUsers(Model model){
        model.addAttribute("users", userDAO.findAll());
        return "users/show";
    }
}
