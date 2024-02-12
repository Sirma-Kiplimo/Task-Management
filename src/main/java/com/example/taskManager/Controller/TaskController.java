package com.example.taskManager.Controller;

import com.example.taskManager.model.Task;
import com.example.taskManager.model.TaskStatus;
import com.example.taskManager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    get all tasks
    @GetMapping
    public List<Task> getAllTasks (){
        return taskService.findAllTasks();
    }

//    Create a task
    @PostMapping
    public Task createTask (@RequestBody Task task){
        return taskService.saveTask(task);
    }

//    Get a task by Id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        return taskService.updateTaskStatus(id, status);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.findTasksByStatus(status);
    }
    @DeleteMapping(("{id}"))
    public void deletetask(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }

}
