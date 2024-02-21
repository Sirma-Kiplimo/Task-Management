package com.example.taskManager.services;

import com.example.taskManager.model.ResponseMessage;
import com.example.taskManager.model.Task;
import com.example.taskManager.model.TaskStatus;
import com.example.taskManager.repositories.TaskRepo;
import jakarta.transaction.Transactional;
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


//    This means that Spring takes care of starting a transaction before the method execution and committing the transaction
//    if the method completes successfully, or rolling it back if there is an exception.

    @Transactional
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        task.setStatus(status);
        return taskRepo.save(task);
    }


//    @Transactional
//    public ResponseMessage updateTaskStatus(Long id, TaskStatus status) {
//        Task task = taskRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
//        task.setStatus(status);
//        Task updatedTask = taskRepo.save(task);
//        return new ResponseMessage(updatedTask, "Task status updated successfully");
//    }

    public List<Task> findTasksByStatus(TaskStatus status) {
        return taskRepo.findByStatus(status);
    }


    public void deleteTaskById(Long id) {
        taskRepo.deleteById(id);
    }

    public void deleteAll() {
        taskRepo.deleteAll();
    }
}

//    @Transactional
//    public Task updateTaskStatus(Long id, TaskStatus status) {
//        Optional<Task> taskOptional = taskRepo.findById(id);
//        if (taskOptional.isPresent()) {
//            Task task = taskOptional.get();
//            task.setStatus(status);
//            return taskRepo.save(task);
//        } else {
//            throw new RuntimeException("Task not found with id " + id);
//        }
//
//    }
