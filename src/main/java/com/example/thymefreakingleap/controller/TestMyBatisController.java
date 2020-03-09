package com.example.thymefreakingleap.controller;
import java.util.List;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import com.example.thymefreakingleap.repo.TestRepo;
import com.example.thymefreakingleap.model.*;

@Controller()
public class TestMyBatisController {
	@Autowired
	private TestRepo repo;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@GetMapping("/testmybatis")
	public String home(Model model) {	
		Person2.Address address = new Person2.Address();
		address.city = "city2";

		List<HashMap> oj = this.repo.testSelect2(address);
		
		System.out.println(oj);	

		StringBuilder tableContent = new StringBuilder();

		for (HashMap o : oj) {
			Context ctx = new Context();
			ctx.setVariables(o);
			tableContent.append(this.templateEngine.process("foo/bar/row2", ctx));
		}
		
		model.addAttribute("tableContent", tableContent);
		return "testmybatis";
	}
}
