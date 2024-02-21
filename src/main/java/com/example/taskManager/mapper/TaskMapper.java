package com.example.taskManager.mapper;

import com.example.taskManager.dto.TaskDTO;
import com.example.taskManager.model.Task;

public class TaskMapper {
    public static TaskDTO mapToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());

        return taskDTO;
    }
    public static Task mapToTask(TaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }

}
