package com.example.taskManager.services;

import com.example.taskManager.dto.TaskDTO;
import com.example.taskManager.mapper.TaskMapper;
import com.example.taskManager.model.Task;
import com.example.taskManager.model.TaskStatus;
import com.example.taskManager.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;


    public List<TaskDTO> findAllTasks() {
        return  taskRepo.findAll()
                .stream()
                .map(TaskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TaskDTO saveTask(TaskDTO taskDTO) {
        Task task = TaskMapper.mapToTask(taskDTO);
        Task savedTask = taskRepo.save(task);
        return TaskMapper.mapToDto(savedTask);
    }

    public Optional<TaskDTO> findTaskById(Long id) {
        return taskRepo.findById(id)
                .map(TaskMapper::mapToDto);
    }


    public TaskDTO updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        task.setStatus(status);
        return TaskMapper.mapToDto((taskRepo.save(task)));
    }

    public List<TaskDTO> findTasksByStatus(TaskStatus status) {
        List<Task> tasks = taskRepo.findByStatus(status);
        return tasks.stream()
                .map(TaskMapper::mapToDto) // Convert Task to TaskDTO
                .collect(Collectors.toList());
    }


    public void deleteTaskById(Long id) {
        taskRepo.deleteById(id);
    }
    public void deleteAllTasks() {
        taskRepo.deleteAll();
    }
}
