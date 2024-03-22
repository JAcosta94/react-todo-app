package com.davidEgg.todolistapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidEgg.todolistapp.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
