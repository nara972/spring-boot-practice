package com.nara.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nara.todo.model.Todo;
import com.nara.todo.service.TodoService;

@Controller
public class TodoController {
	@Autowired
	TodoService todoService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Todo> allTodo=todoService.searchAll();
		model.addAttribute("allTodo",allTodo);
		model.addAttribute("todo",new Todo());
		return "home";
	}
	
	@PostMapping("/")
	public String createTodo(@Valid Todo todo,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			List<Todo> allTodo=todoService.searchAll();
			model.addAttribute("allTodo",allTodo);
			model.addAttribute("todo",todo);
			return "home";
		}
		//오류가 없으면 Todo를 신규등록
		todoService.addTodo(todo);
		return "redirect:/";
	}
	
	@PostMapping("/done")
	public String doneTodo(@RequestParam(name="id") Integer todoId) {
		Todo updateTodo=todoService.findById(todoId);
		updateTodo.setDone(true);
		todoService.addTodo(updateTodo);
		return "redirect:/";
	}
	
	@PostMapping("/deleteAll")
	public String deleteAll() {
		todoService.deleteAllTodo();
		return "redirect:/";
	}
	
}
