package com.davidEgg.todolistapp.services;

import java.util.BitSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepo;

    public List<Task> listTasks(){
        return null;
    }

    public Task createTask(String title, BitSet dates, Boolean recurring, String description){
        Task task = new Task();
        task.setTitle(title);
        task.setDates(dates);
        task.setRecurring(recurring);
        task.setDescription(description);
        // task.setCompleted(false);   uncomment if the assignment doesn't work
        return task;
        // return taskRepo.save(task);
    }

    Task readTask(Long id){
        return taskRepo.findById(id).orElse(null);
    }

    Task updateTask(){
        return null;
    }

    void deleteTask(){}

    private void validateTask(){}
    
}
