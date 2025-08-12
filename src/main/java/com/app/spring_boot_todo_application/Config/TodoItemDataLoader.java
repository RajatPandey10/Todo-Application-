package com.app.spring_boot_todo_application.Config;

import com.app.spring_boot_todo_application.Repository.TodoItemRepository;
import com.app.spring_boot_todo_application.models.TodoItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoItemDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);
    @Autowired
    TodoItemRepository todoItemRepository ;

    @Override
    public void  run(String... args) throws Exception{
        loadData();
    }
    private void loadData(){
        if(todoItemRepository.count() ==0){
            TodoItems todoItems = new TodoItems("Get the Milk");
            TodoItems todoItems1 = new TodoItems("Bring eggs");

            todoItemRepository.save(todoItems1);
            todoItemRepository.save(todoItems);
        }
        logger.info("Number of TodoItems: {}",todoItemRepository.count());
    }

}
