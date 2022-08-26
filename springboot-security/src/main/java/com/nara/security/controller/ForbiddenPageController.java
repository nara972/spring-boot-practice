package com.nara.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForbiddenPageController {

    @GetMapping("/forbidden")
    public String forbiddenPage() {
        return "forbidden";
    }
}