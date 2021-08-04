package com.nara.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nara.todo.model.Todo;
import com.nara.todo.repository.TodoRepository;

@Service
public class TodoService {
	@Autowired
	TodoRepository todoRepository;
	
	public List<Todo> searchAll(){
		return todoRepository.findAll();
	}
	
	public void addTodo(Todo todo) {
		todoRepository.save(todo);
	}
	
	public Todo findById(Integer id) {
		Optional<Todo> updateTodo=todoRepository.findById(id);
		return updateTodo.get();
	}
	
	public void deleteAllTodo() {
		List<Todo> allTodo=todoRepository.findAll();
		List<Todo> doneList=new ArrayList<>();
		for(Todo todo:allTodo) {
			if(todo.isDone()) {
				doneList.add(todo);
			}
		}
		todoRepository.deleteAll(doneList);
	}
}
