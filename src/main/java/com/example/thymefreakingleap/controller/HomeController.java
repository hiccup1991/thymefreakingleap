package com.example.thymefreakingleap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("greeter", "James Buttocker");
		model.addAttribute("option_path", "/foo/bar/options2.html");
		return "index";
	}
}
