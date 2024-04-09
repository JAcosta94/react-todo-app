package com.davidEgg.todolistapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidEgg.todolistapp.repositories.TaskRepository;
import com.davidEgg.todolistapp.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    public TaskRepository taskRepo;
    @Autowired
    public UserRepository userRepo;

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
