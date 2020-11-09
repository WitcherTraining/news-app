package com.epam.spring.controllers;

import com.epam.spring.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/",  "/index"})
    public String listUsers(Model model){

        model.addAttribute("users", userService.findAll());

        return "users/index";
    }
}
