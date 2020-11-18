package com.epam.spring.controllers;

import com.epam.spring.services.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping({"", "/",  "/show"})
    public String listUsers(Model model){

        model.addAttribute("users", userDAO.findAll());

        return "users/show";
    }
}
