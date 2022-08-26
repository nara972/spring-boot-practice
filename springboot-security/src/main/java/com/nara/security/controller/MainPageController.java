package com.nara.security.controller;

import com.nara.security.model.User;
import com.nara.security.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if (userDetails != null) {
            User user = userDetails.getUser();
            System.out.println(user.getRole().getDescription());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", "");
        }
        return "index";
    }
}