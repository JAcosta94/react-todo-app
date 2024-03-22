package com.davidEgg.todolistapp.services;

import java.util.BitSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.exceptions.CustomException;
import com.davidEgg.todolistapp.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepo;

    public List<Task> listTasks(){
        return null;
    }

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    public Task readTask(Long id){
        return taskRepo.findById(id).orElse(null);
    }

    Task updateTask(String title, BitSet dates, Boolean recurring, String description, Boolean completed){
        Task task = new Task();
        task.setTitle(title);
        task.setDates(dates);
        task.setRecurring(recurring);
        task.setDescription(description);
        return taskRepo.save(task);
    }

    public void deleteTask(Long id) throws CustomException{
        if (id == null) {
            throw new CustomException("ID can not be null");
        }

        Optional<Task> task = taskRepo.findById(id);

        if (task.isPresent()) {
            taskRepo.delete(task.get());
        }else{
            throw new CustomException("No task found with ID: " + id);
        }
    }

    private void validateTask(){}

    public String dateInfoMessage(Long id) {
        return null;
    }
    
}
