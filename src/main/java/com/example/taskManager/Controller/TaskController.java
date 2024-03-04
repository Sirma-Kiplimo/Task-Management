package com.example.taskManager.Controller;

import com.example.taskManager.dto.TaskDTO;
import com.example.taskManager.mapper.TaskMapper;
import com.example.taskManager.model.TaskStatus;
import com.example.taskManager.services.TaskService;
import com.example.taskManager.wrappers.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    /**
     * Getting
     * all
     * tasks
     */
    @GetMapping
    public ResponseWrapper getAllTasks() {
        List<TaskDTO> tasks = taskService.findAllTasks();
        return new ResponseWrapper(true, "Tasks retrieved successfully", tasks);
    }



    /**
     * adding a task
     */
    @PostMapping
    public ResponseWrapper createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.saveTask(taskDTO);
        return new ResponseWrapper(true, "Task created successfully", createdTask);
    }




    /**
     * getting a task
     * By Id
     */
    @GetMapping("/{id}")
    public ResponseWrapper getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(taskDTO -> new ResponseWrapper(true, "Task retrieved successfully", taskDTO))
                .orElse(new ResponseWrapper(false, "Task not found", null));
    }


    /**
     * Update a task status
     */
    @PutMapping("/{id}/status")
    public ResponseWrapper updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        try {
            TaskDTO updatedTask = taskService.updateTaskStatus(id, status);
            return new ResponseWrapper(true, "Task status updated successfully", updatedTask);
        } catch (RuntimeException ex) {
            return new ResponseWrapper(false, "Task not found", null);
        }
    }

    /**
     * Getting a task by status
     */
    @GetMapping("/status/{status}")
    public ResponseWrapper getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskDTO> tasks = taskService.findTasksByStatus(status);
        return new ResponseWrapper(true, "Tasks retrieved successfully", tasks);
    }

    /**
     * Deleting a task by id
     */
    @DeleteMapping("/{id}")
    public ResponseWrapper deleteTaskById(@PathVariable Long id) {
        try {
            taskService.deleteTaskById(id);
            return new ResponseWrapper(true, "Task deleted successfully", null);
        } catch (RuntimeException ex) {
            return new ResponseWrapper(false, "Task not found", null);
        }
    }

    //    deleting all Tasks
    @DeleteMapping("/all")
    public ResponseWrapper deleteTasks() {
        taskService.deleteAllTasks();
        return new ResponseWrapper(true, "All tasks deleted successfully", null);
    }



}
