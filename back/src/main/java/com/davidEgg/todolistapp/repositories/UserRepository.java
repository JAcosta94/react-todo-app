package com.davidEgg.todolistapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidEgg.todolistapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
