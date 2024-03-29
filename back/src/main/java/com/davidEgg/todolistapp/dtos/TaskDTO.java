package com.davidEgg.todolistapp.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    
    private String title;
    private List<LocalDate> dates;
    private Boolean recurring;
    private String description;
    private Boolean completed;
    private String dateMessage;

    public TaskDTO() {
        this.dates = new ArrayList<>();
    }

    public TaskDTO(String title, ArrayList<LocalDate> dates, Boolean recurring, String description, Boolean completed, String dateMessage) {
        this.title = title;
        this.dates = dates;
        this.recurring = recurring;
        this.description = description;
        this.completed = completed;
        this.dateMessage = dateMessage;
    }
}
