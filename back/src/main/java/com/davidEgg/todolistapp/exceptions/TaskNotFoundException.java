package com.davidEgg.todolistapp.exceptions;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String msg){
        super(msg);
    }
}
