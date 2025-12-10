package com.app.spring_boot_todo_application.Controllers;

import com.app.spring_boot_todo_application.Repository.TodoItemRepository;
import com.app.spring_boot_todo_application.models.TodoItems;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;

@Controller
public class TodoItemController {;
    private final Logger log = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItems todoItems, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-todo-item";
        }
        todoItems.setCreatedDate(Instant.now());
        todoItems.setModifyDate(Instant.now());
        todoItemRepository.save(todoItems);
        return "redirect:/";
    }
    @GetMapping("/")
    public ModelAndView index(){
        log.debug("request to Get index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems",todoItemRepository.findAll());
        modelAndView.addObject("today",Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItems todoItems, BindingResult result, Model model){
        if(result.hasErrors()){
            todoItems.setId(id);
            return "update-todo-item";
        }
        todoItems.setModifyDate(Instant.now());
        todoItemRepository.save(todoItems);
        return "redirect:/";
    }
}
