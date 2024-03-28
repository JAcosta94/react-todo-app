package com.davidEgg.todolistapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.exceptions.TaskIdValidationException;
import com.davidEgg.todolistapp.exceptions.TaskNotFoundException;
import com.davidEgg.todolistapp.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepo;

    public List<Task> listTasks() throws TaskNotFoundException {
        try {
            return taskRepo.findAll();
        } catch (Exception e) {
            System.err.println("An error occurred while fetching tasks: " + e.getMessage());
            throw new TaskNotFoundException("Failed to fetch tasks");
        }
    }

    public Page<Task> getPaginatedTasks(Integer page, Integer size) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            return taskRepo.findAll(pageRequest);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid page or size: " + e.getMessage());
        }
    }

    public Task createTask(Task task) throws TaskNotFoundException {
        try {
            return taskRepo.save(task);
        } catch (Exception e) {
            throw new TaskNotFoundException ("Failed to create task: " + e.getMessage());
        }
    }

    public Task readTask(Long id) throws TaskIdValidationException, TaskNotFoundException {
        if (id == null || id <= 0 || id > Long.MAX_VALUE) {
            throw new TaskIdValidationException("Invalid task ID: " + id);
        }
        Task task = taskRepo.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        return task;
    }

    public Task updateTask(Long taskId, Task updatedTask) throws TaskIdValidationException, TaskNotFoundException {
        if (taskId == null || taskId <= 0 || taskId > Long.MAX_VALUE) {
            throw new TaskIdValidationException("Invalid task ID: " + taskId);
        }
        Optional<Task> optionalTask = taskRepo.findById(taskId);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDates(updatedTask.getDates());
            existingTask.setRecurring(updatedTask.getRecurring());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.getCompleted());

            return taskRepo.save(existingTask);
        } else {

            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }

    }

    public void deleteTask(Long id) throws TaskIdValidationException, TaskNotFoundException {
        if (id == null || id <= 0 || id > Long.MAX_VALUE) {
            throw new TaskIdValidationException("Invalid task ID: " + id);
        }

        Optional<Task> task = taskRepo.findById(id);

        if (task.isPresent()) {
            taskRepo.delete(task.get());
        } else {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
    }

    private void validateTask() {
    }

    public String dateInfoMessage(Long id) {
        return null;
    }

    public Task markTaskAsCompleted(Long id) throws TaskIdValidationException, TaskNotFoundException {
        if (id == null || id <= 0 || id > Long.MAX_VALUE) {
            throw new TaskIdValidationException("Invalid task ID: " + id);
        }
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
        task.setCompleted(true);
        return task;
    }

}
