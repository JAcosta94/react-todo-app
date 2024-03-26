package com.davidEgg.todolistapp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
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
    
    @ElementCollection
    @CollectionTable(name = "task_dates", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "date")
    private List<LocalDate> dates;
    private Boolean recurring;
    private String description;
    private Boolean completed;

    public Task() {
        this.dates = new ArrayList<>();
    }

    public Task(String title, ArrayList<LocalDate> dates, Boolean recurring, String description, Boolean completed) {
        this.title = title;
        this.dates = dates;
        this.recurring = recurring;
        this.description = description;
        this.completed = completed;
    }

    

}