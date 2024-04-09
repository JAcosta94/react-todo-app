package com.davidEgg.todolistapp.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.davidEgg.todolistapp.dtos.TaskDTO;
import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.entities.User;
import com.davidEgg.todolistapp.enumerations.Priority;
import com.davidEgg.todolistapp.enumerations.Recurring;
import com.davidEgg.todolistapp.exceptions.TaskIdValidationException;
import com.davidEgg.todolistapp.exceptions.TaskNotFoundException;
import com.davidEgg.todolistapp.exceptions.UserNotFoundException;
import com.davidEgg.todolistapp.repositories.TaskRepository;
import com.davidEgg.todolistapp.repositories.UserRepository;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepo;
    @Autowired
    public UserRepository userRepo;

    public List<Task> listTasks() throws TaskNotFoundException {
        try {
            return taskRepo.findAll();
        } catch (Exception e) {
            System.err.println("An error occurred while fetching tasks: " + e.getMessage());
            throw new TaskNotFoundException("Failed to fetch tasks");
        }
    }

    public List<Task> getPaginatedTasks(Integer page, Integer size, Long userId) throws UserNotFoundException {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
            return taskRepo.findByUser(user, pageRequest);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid page or size: " + e.getMessage());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Invalid page or size: " + e.getMessage());
        }
    }

    // creates and persist a task entity, but returns a taskDTO (could convert it
    // into a void method)
    /**
     * @param dataTask
     * @param userId
     * @return
     * @throws TaskNotFoundException
     */
    public TaskDTO createTask(TaskDTO dataTask, Long userId) throws TaskNotFoundException { // todo
        validateTask(dataTask);
        Task targetTask = new Task();
        targetTask.setTitle(dataTask.getTitle());
        targetTask.setDates(dataTask.getDates());
        targetTask.setRecurring(dataTask.getRecurring());
        targetTask.setDescription(dataTask.getDescription());
        targetTask.setCompleted(dataTask.getCompleted());
        targetTask.setDeadline(dataTask.getDeadline());
        targetTask.setPriority(dataTask.getPriority());
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            targetTask.setUser(optionalUser.get());
        }

        dataTask.setDateMessage(dateInfoMessage(dataTask.getRecurring()) );

        try {
            taskRepo.save(targetTask);
            return dataTask;
        } catch (Exception e) {
            throw new TaskNotFoundException("Failed to create task: " + e.getMessage());
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

    public TaskDTO updateTask(Long taskId, TaskDTO updatedTask)
            throws TaskIdValidationException, TaskNotFoundException {

        if (taskId == null || taskId <= 0 || taskId > Long.MAX_VALUE) {
            throw new TaskIdValidationException("Invalid task ID: " + taskId);
        }
        validateTask(updatedTask);

        Optional<Task> optionalTask = taskRepo.findById(taskId);
        if (optionalTask.isPresent()) {

            Task existingTask = optionalTask.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDates(updatedTask.getDates());
            existingTask.setRecurring(updatedTask.getRecurring());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.getCompleted());
            existingTask.setDeadline(updatedTask.getDeadline());
            existingTask.setPriority(updatedTask.getPriority());

            updatedTask.setDateMessage(dateInfoMessage(updatedTask.getRecurring()) );

            taskRepo.save(existingTask);
            return updatedTask;
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

    private void validateTask(TaskDTO updatedTaskDto) {
        if (updatedTaskDto.getTitle() == null || updatedTaskDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        List<LocalDate> dates = updatedTaskDto.getDates();
        if (dates != null && !dates.isEmpty()) {
            for (LocalDate date : dates) {
                if (date == null || date.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("Invalid date provided");
                }
            }
        }

        Recurring recurring = updatedTaskDto.getRecurring();
        if (recurring == null) {
            updatedTaskDto.setRecurring(Recurring.NONE);
        }

        String description = updatedTaskDto.getDescription();
        if (description == null) {
            updatedTaskDto.setDescription("");
        }

        Boolean completed = updatedTaskDto.getCompleted();
        if (completed == null) {
            updatedTaskDto.setCompleted(false);
        }

        LocalDate deadline = updatedTaskDto.getDeadline();
        if (deadline == null || deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid deadline provided");
        }

        Priority priority = updatedTaskDto.getPriority();
        if (priority == null) {
            updatedTaskDto.setPriority(Priority.LOW);
        }
    }

    public Task markTaskAsCompleted(Long id) throws TaskIdValidationException, TaskNotFoundException {
        if (id == null || id <= 0 || id > Long.MAX_VALUE) {
            throw new TaskIdValidationException("Invalid task ID: " + id);
        }
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
        task.setCompleted(true);
        return task;
    }

    private String dateInfoMessage(Recurring recurring) {

        switch (recurring) {
            case DAILY:
                return "Everyday";
            case WEEKLY:
                
                return "Every (days of the week)";// + daysOfWeek.stream()
                        //.map(DayOfWeek::toString)
                       //.collect(Collectors.joining(", "));
            case MONTHLY:
                return "(days of the month) each month";
            case YEARLY:
                return "(days of the year) every year";
            case NONE:
            default:
                return "";
        }
    }

}
