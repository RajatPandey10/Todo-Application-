package com.app.spring_boot_todo_application.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.time.Instant;

@Entity
@Table(name = "todoItems")
@Data
public class TodoItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotBlank(message = "Description is required")
    private String description;
    private boolean complete;

    private Instant createdDate;
    private Instant modifyDate;

    public TodoItems(){}
    public TodoItems(String description){
        this.description  = description;
        this.complete = false;
        this.createdDate = Instant.now();
        this.modifyDate=Instant.now();
    }

    @Override
    public String toString(){
        return String.format("TodoItems{id=%d, description= '%s', complete='%s',createdDate='%s', modifiedDate='%s'}",
                id,description,createdDate,modifyDate);

    }
}
