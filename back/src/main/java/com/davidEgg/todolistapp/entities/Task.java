package com.davidEgg.todolistapp.entities;

import java.util.BitSet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Task {

    @Id
    private String title;

    private BitSet dates;
    private String comment;
    private Boolean recurring;



}
