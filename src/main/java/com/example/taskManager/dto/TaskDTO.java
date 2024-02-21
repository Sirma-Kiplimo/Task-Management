package com.example.taskManager.dto;

import com.example.taskManager.model.TaskStatus;
import lombok.Data;

@Data
public class TaskDTO {

    private String name;
    private String description;
    private TaskStatus status;

}
