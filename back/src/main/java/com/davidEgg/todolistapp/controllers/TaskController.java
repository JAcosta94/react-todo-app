package com.davidEgg.todolistapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.exceptions.CustomException;
import com.davidEgg.todolistapp.services.TaskService;

@RestController
@RequestMapping("/to-do-list")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.listTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.readTask(id);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (CustomException ex) {
            return ResponseEntity.notFound().build(); 
        }
    }


    
}

//If i chose to use DTO

// @GetMapping("/tasks/{id}")                       
// public TaskDTO getTask(@PathVariable Long id){

//     try {
//         return taskService.readTask(id);
//     } catch (CustomException ex) {

//     }
// }
        



//If I chose to send the message separatly from the rest
// @GetMapping("/tasks/{id}"){                      
//     public String getTask(@PathVariable Long id){

//         try {
//             return taskService.dateInfoMessage(id);
//         } catch (CustomException ex) {

//         }
//     }
        

// }
