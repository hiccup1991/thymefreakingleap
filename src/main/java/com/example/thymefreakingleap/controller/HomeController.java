package com.example.thymefreakingleap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.thymefreakingleap.model.Person;

@Controller()
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("greeter", "James Buttocker");
		model.addAttribute("option_path", "/foo/bar/options2.html");
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Homer Simpson", 56));
		persons.add(new Person("Marge Simpson", 50));
		
		model.addAttribute("persons", persons);
		
		return "index";
	}
}
