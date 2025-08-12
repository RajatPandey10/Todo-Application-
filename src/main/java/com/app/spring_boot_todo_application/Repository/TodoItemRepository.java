package com.app.spring_boot_todo_application.Repository;

import com.app.spring_boot_todo_application.models.TodoItems;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItems,Long> {
}
