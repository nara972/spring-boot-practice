package com.spring.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class TestController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/index")
	public String index(Model model) {
		String sql="select * from test_tb";
		List<Map<String, Object>> list= jdbcTemplate.queryForList(sql);
		model.addAttribute("test",list.size());
		model.addAttribute("testList",list);
		return "index";
	}
}
