package io.myzticbean.wordle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordleUIController {

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
}
