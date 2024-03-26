package com.davidEgg.todolistapp.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.exceptions.CustomException;
import com.davidEgg.todolistapp.repositories.TaskRepository;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskRepository taskRepo;

    public List<Task> listTasks() {
        return taskRepo.findAll();
    }

    public Page<Task> getPaginatedTasks(int page, int size) {
        // Crear objeto PageRequest para solicitar la página deseada y el tamaño de la página
        PageRequest pageRequest = PageRequest.of(page, size);
        
        // Obtener la página de tareas del repositorio
        return taskRepo.findAll(pageRequest);
    }

    public Task createTask(Task task) {
        logger.info("Creando tarea: {}", task);
        return taskRepo.save(task);
    }

    public Task readTask(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task updateTask(Long taskId, Task updatedTask) throws CustomException {

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

            throw new CustomException("No se encontró la tarea con el ID: " + taskId);
        }

    }

    public void deleteTask(Long id) throws CustomException {
        if (id == null) {
            throw new CustomException("ID can not be null");
        }

        Optional<Task> task = taskRepo.findById(id);

        if (task.isPresent()) {
            taskRepo.delete(task.get());
        } else {
            throw new CustomException("No task found with ID: " + id);
        }
    }

    private void validateTask() {
    }

    public String dateInfoMessage(Long id) {
        return null;
    }

    public Task markTaskAsCompleted(Long id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(true);
            task = taskRepo.save(task);
        }
        return task;
    }



}
