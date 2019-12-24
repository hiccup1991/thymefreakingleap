package com.example.thymefreakingleap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.example.thymefreakingleap.model.Person;
import com.example.thymefreakingleap.repo.TestRepo;

@Controller()
public class HomeController {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private TestRepo repo;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("greeter", "James Buttocker");
		model.addAttribute("option_path", "/foo/bar/options2.html");
		
		List<Map<String, Object>> persons = new ArrayList<Map<String, Object>>();
		persons.add(this.personToMap(new Person("Homer Simpson", 56)));
		persons.add(this.personToMap(new Person("Marge Simpson", 50)));
		persons.add(this.personToMap(new Person("Bart Simpson", 11)));
		
		model.addAttribute("persons", persons);
		model.addAttribute("name", "Jay");
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("chunk", "first_name = 'phong'");
//		
		this.repo.testSelect("first_name = 'phong'");
//		Object o = this.sqlSession.selectOne("com.example.thymefreakingleap.repo.TestRepo.testSelect", map);
//		System.out.println(o);
	
		Configuration configuration = this.sqlSessionFactory.getConfiguration();
		MappedStatement m1 = configuration.getMappedStatement("com.example.thymefreakingleap.repo.TestRepo.first_name_chunk");
		
//		Map<String, Object> bound = new HashMap<String, Object>();
//		bound.put("first_name", "phong");
//		BoundSql bs = m1.getBoundSql(bound);
		
		MappedStatement select = configuration.getMappedStatement("com.example.thymefreakingleap.repo.TestRepo.testSelect");

		Map<String, Object> bound2 = new HashMap<String, Object>();
		bound2.put("chunk", "1=1");
		bound2.put("first_name", "phong");
		BoundSql bs2 = select.getBoundSql(bound2);
		//String someQuery ="select * from b where c = ${c} and d = ${d}";
		
		final Map<String, String> t =  new HashMap<>();
		t.put("c", "112313");
		t.put("d", "4444442");
		
//		Matcher matcher = Pattern.compile("\\$\\{(.+?)\\}", Pattern.DOTALL).matcher(someQuery);
//		String s2 = matcher.replaceAll(x->{
//			return t.get(x.group(1));
//		});
//		
//		System.out.println(bs2.getSql());
		//List<HashMap> rs = this.repo.testSelect(bs.getSql());
		List<Object> oj = this.sqlSession.selectList("testSelect", bound2);
		
		System.out.println(oj);
		
		StringBuilder tableContent = new StringBuilder();

		for (Object o : oj) {
			Context ctx = new Context();
			ctx.setVariables((Map)o);
			//ctx.setVariable("FIRST_NAME", ((Map) o).get("first_name"));
			tableContent.append(this.templateEngine.process("foo/bar/row", ctx));
		}
		
		model.addAttribute("someInlineHtml", "<div style=\"font-weight: bold\">hello world</div>");
		model.addAttribute("tableContent", tableContent);
		
		return "index";
	}
	
	private Map<String, Object> personToMap(Person person) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", person.getName());
		m.put("age", person.getAge());
		
		return m;
	}
}
