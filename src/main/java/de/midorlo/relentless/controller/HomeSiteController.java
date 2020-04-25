package de.midorlo.relentless.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeSiteController {


    @GetMapping("/")
    public String greeting() {
        return "index";
    }

    @GetMapping("/config.js")
    public String fileConfig() {
        return "config.js";
    }

}
