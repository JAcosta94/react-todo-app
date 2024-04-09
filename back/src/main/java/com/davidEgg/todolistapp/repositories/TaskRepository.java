package com.davidEgg.todolistapp.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidEgg.todolistapp.entities.Task;
import com.davidEgg.todolistapp.entities.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findByUser(User user, PageRequest pageRequest);
    
    //public List<Task> findByOrderByPriorityDescDeadlineAsc();

    //List<Task> findByOrderByPriorityAscDeadlineDesc();
}
