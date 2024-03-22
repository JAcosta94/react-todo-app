package com.davidEgg.todolistapp;

import java.util.BitSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.services.TaskService;

@SpringBootApplication
public class TodolistappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistappApplication.class, args);

		System.out.println("funciona");

		// Task task = new Task();

		// task.setTitle("title");
        // task.setDates(dates);
        // task.setRecurring(recurring);
        // task.setDescription(description);


	



		// TaskService taskService = new TaskService();

		// for (int i = 1; i <= 10; i++) {
		// 	String title = "Tarea " + i;
		// 	BitSet dates = new BitSet(7); // Por ejemplo, días de la semana
		// 	dates.set(i % 7); // Seleccionar un día de la semana aleatorio
		// 	Boolean recurring = i % 2 == 0; // Tarea recurrente alternando entre verdadero y falso
		// 	String description = "Descripción de la tarea " + i;
		// 	// taskService.createTask(title, dates, recurring, description);
		// 	System.out.println((new Task(title, dates, recurring, description, false)).toString());
		// }

		// Obtener la lista de tareas y imprimir sus detalles
		// List<Task> tasks = taskService.listTasks();
		// for (Task task : tasks) {
		// 	System.out.println(task.toString());
		// }
	}

}
