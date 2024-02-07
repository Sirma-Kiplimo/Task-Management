package com.example.taskManager.repositories;

import com.example.taskManager.model.Task;
import com.example.taskManager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);

}
