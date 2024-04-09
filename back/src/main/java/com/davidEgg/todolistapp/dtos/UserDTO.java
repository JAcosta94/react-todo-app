package com.davidEgg.todolistapp.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UserDTO {

    private List<TaskDTO> tasks;
}
