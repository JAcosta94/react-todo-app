package com.davidEgg.todolistapp.exceptions;

public class TaskIdValidationException extends RuntimeException {
    public TaskIdValidationException(String msg) {
        super(msg);
    }
}
