package com.davidEgg.todolistapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.davidEgg.todolistapp.dtos.TaskDTO;
import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.exceptions.*;
import com.davidEgg.todolistapp.services.TaskService;

// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/{userId}")
// @Api(tags = "To-Do List API")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // @ApiOperation("Create a new task")
    @PostMapping("/tasks/create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO task, @PathVariable Long userId) {
        try {
            TaskDTO createdTask = taskService.createTask(task, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // @ApiOperation("Get all tasks, response = List.class")
    @GetMapping("/tasks/all")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long userId) {
        try {
            List<Task> tasks = taskService.listTasks();
            if (tasks.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // @ApiOperation("Get paginated tasks, response = Page.class")
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getPaginatedTasks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Long userId) {
        try {
            List<Task> tasksPage = taskService.getPaginatedTasks(page, size, userId);
            return ResponseEntity.ok(tasksPage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // @ApiOperation("Get a task by ID, response = Task.class")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve task: " + e.getMessage());
        }
    }

    // @ApiOperation("Update a task by ID, response = Task.class")
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO updatedTask) { 
        try {
            updatedTask = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(updatedTask);
        } catch (TaskIdValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // @ApiOperation("Delete a task by ID")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id, @PathVariable Long userId) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // @ApiOperation("Mark a task as completed, response = Task.class")
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