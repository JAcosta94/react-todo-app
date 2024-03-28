package com.davidEgg.todolistapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.exceptions.TaskIdValidationException;
import com.davidEgg.todolistapp.exceptions.TaskNotFoundException;
import com.davidEgg.todolistapp.services.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/to-do-list")
@Api(tags = "To-Do List API")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation("Create a new task")
    @PostMapping("/tasks/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Get all tasks, response = List.class")
    @GetMapping("/tasks/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> tasks = taskService.listTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation("Get paginated tasks, response = Page.class")
    @GetMapping("/to-do-list/tasks")
    public ResponseEntity<Page<Task>> getPaginatedTasks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Page<Task> tasksPage = taskService.getPaginatedTasks(page, size);
            return ResponseEntity.ok(tasksPage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Get a task by ID, response = Task.class")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        try {
            Task task = taskService.readTask(id);
            if (task != null) {
                return ResponseEntity.ok(task);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (TaskNotFoundException e) {            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found: " + e.getMessage());    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve task: " + e.getMessage());
        }
    }

    @ApiOperation("Update a task by ID, response = Task.class")
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){ // throws TaskIdValidationException, TaskNotFoundException 
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (TaskIdValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ApiOperation("Delete a task by ID")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){ // throws TaskIdValidationException, TaskNotFoundException ?
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Mark a task as completed, response = Task.class")
    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long id) {
        try {
            Task completedTask = taskService.markTaskAsCompleted(id);
            return ResponseEntity.ok(completedTask);
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    

}

// If i chose to use DTO

// @GetMapping("/tasks/{id}")
// public TaskDTO getTask(@PathVariable Long id){

// try {
// return taskService.readTask(id);
// } catch (CustomException ex) {

// }
// }

// If I chose to send the message separatly from the rest
// @GetMapping("/tasks/{id}"){
// public String getTask(@PathVariable Long id){

// try {
// return taskService.dateInfoMessage(id);
// } catch (CustomException ex) {

// }
// }

// }
