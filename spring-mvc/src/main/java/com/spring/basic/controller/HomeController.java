package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.basic.model.User;

@Controller
public class HomeController {
	
	@GetMapping("/form")
	private String readFrom(@ModelAttribute User user) {
		return "form";
	}
	@PostMapping("/form")
	private String confirm(@ModelAttribute User user) {
	return "confirm";
	}

}
