package com.firstproject.springboot.myfirstproject.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	public TodoControllerJpa(TodoService todoService,TodoRepository todoRepository) {
		super();
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	private TodoService todoService;
	
	private TodoRepository todoRepository;
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String name = getLoggedinUsername(model);
		
		List<Todo> todos = todoRepository.findByUsername(name);
		model.addAttribute("todos",todos);
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo",method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String name = getLoggedinUsername(model);
		
		Todo todo = new Todo(0,name,"",LocalDate.now().plusYears(1),false);
		model.put("todo",todo);
		
		return "todo";
	}
	
	@RequestMapping(value="add-todo",method = RequestMethod.POST)
	public String addNewTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedinUsername(model);
		todo.setUsername(name);
		todoRepository.save(todo);
//		todoService.addTodo(name, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
//		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
//		Todo todo = todoService.findById(id);
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute(todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo",method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedinUsername(model); 
		todo.setUsername(name);
//		todoService.updateTodo(todo);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	private String getLoggedinUsername(ModelMap model) {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
