package com.davidEgg.todolistapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.davidEgg.todolistapp.exceptions.CustomException;
import com.davidEgg.todolistapp.services.TaskService;

@RestController
@RequestMapping("/to-do-list")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks/create")
    public Task createTask(@RequestBody Task task) {
        logger.info("Solicitud POST recibida para crear una tarea: {}", task);
        return taskService.createTask(task);
    }

    @GetMapping("/tasks/all")
    public List<Task> getAllTasks() {
        return taskService.listTasks();
    }

    @GetMapping("/tasks")
    public ResponseEntity<Page<Task>> getPaginatedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Task> tasksPage = taskService.getPaginatedTasks(page, size);
        return ResponseEntity.ok(tasksPage);
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.readTask(id);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) throws CustomException {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (CustomException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long id) {
        Task completedTask = taskService.markTaskAsCompleted(id);
        return ResponseEntity.ok(completedTask);
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
