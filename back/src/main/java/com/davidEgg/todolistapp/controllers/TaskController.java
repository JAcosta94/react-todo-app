package com.davidEgg.todolistapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidEgg.todolistapp.services.TaskService;

@RestController
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskService;

    
    
}
