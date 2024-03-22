package com.davidEgg.todolistapp.entities;

import java.util.BitSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;    
    private BitSet dates;
    private Boolean recurring;
    private String description;
    private Boolean completed;

    public Task() {
        completed = false;
    }

    public Task(String title, BitSet dates, Boolean recurring, String description, Boolean completed) {
        this.title = title;
        this.dates = dates;
        this.recurring = recurring;
        this.description = description;
        this.completed = completed;
    }

    

}
