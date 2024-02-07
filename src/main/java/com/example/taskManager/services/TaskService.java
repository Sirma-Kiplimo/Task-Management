package com.example.taskManager.services;

import com.example.taskManager.model.Task;
import com.example.taskManager.model.TaskStatus;
import com.example.taskManager.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> findAllTasks() {
        return taskRepo.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepo.findById(id);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> taskOptional = taskRepo.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setStatus(status);
            return taskRepo.save(task);
        } else {
            throw new RuntimeException("Task not found with id " + id);
        }
    }

    public List<Task> findTasksByStatus(TaskStatus status) {
        return taskRepo.findByStatus(status);
    }
}
