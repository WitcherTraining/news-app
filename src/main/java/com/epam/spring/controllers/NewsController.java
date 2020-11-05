package com.epam.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @GetMapping({"", "/", "/index"})
    public String getIndexPage(){
        return "index";
    }
}
