package com.davidEgg.todolistapp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.davidEgg.todolistapp.enumerations.Priority;
import com.davidEgg.todolistapp.enumerations.Recurring;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Recurring recurring;
    private String description;
    private Boolean completed;
    private LocalDate deadline;
    private Priority priority;

    @ManyToOne
    private User user;

    public Task() {
        this.dates = new ArrayList<>();
    }

    public Task(String title, List<LocalDate> dates, Recurring recurring, String description, Boolean completed,
            LocalDate deadline, String dateMessage, Priority priority, User user) {
        this.title = title;
        this.dates = dates;
        this.recurring = recurring;
        this.description = description;
        this.completed = completed;
        this.deadline = deadline;
        this.priority = priority;
        this.user = user;
    }

}