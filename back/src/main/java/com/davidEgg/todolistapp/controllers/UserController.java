package com.davidEgg.todolistapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidEgg.todolistapp.services.UserService;

@RestController
@RequestMapping("/user/{userId}")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserById(Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
