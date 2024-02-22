package com.example.taskManager.Controller;

import com.example.taskManager.dto.TaskDTO;
import com.example.taskManager.mapper.TaskMapper;
import com.example.taskManager.model.TaskStatus;
import com.example.taskManager.services.TaskService;
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
    public List<TaskDTO> getAllTasks() {
        return taskService.findAllTasks();
    }

    /**
     * adding a task
     */
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.saveTask(taskDTO);
    }

    /**
     * getting a task
     * By Id
     */

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    /**
     * Update a task status
     */
//    @PutMapping("/{id}/status")
//    public TaskDTO updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
//        Task updatedTask = taskService.updateTaskStatus(id, status);
//        return TaskMapper.toDTO(updatedTask);
//    }

    @PutMapping("/{id}/status")
    public TaskDTO updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        return taskService.updateTaskStatus(id, status);
    }

}

//    private final TaskService taskService;
//
//    @Autowired
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
////    get all tasks
//    @GetMapping
//    public List<Task> getAllTasks (){
//        return taskService.findAllTasks();
//    }
//
////    Create a task
//    @PostMapping
//    public Task createTask (@RequestBody Task task){
//        return taskService.saveTask(task);
//    }
//
////    Get a task by Id
//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
//        return taskService.findTaskById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(()-> ResponseEntity.notFound().build());
//    }
//
//
////    updating the task status using put method
//    @PutMapping("/{id}/status")
//    public Task updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
//        return taskService.updateTaskStatus(id, status);
//    }
//
////    getting a task by  status
//    @GetMapping("/status/{status}")
//    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
//        return taskService.findTasksByStatus(status);
//    }
//
////    Delete a tsk by id
//    @DeleteMapping("{id}")
//    public void deletetask(@PathVariable Long id){
//        taskService.deleteTaskById(id);
//    }
//
//
////    deleting all Tasks
//    @DeleteMapping("/all")
//    public void deleteTasks(){
//        taskService.deleteAll();
//    }
//}


//    updating the task status using patch since it is one field
//    @PatchMapping("/{id}/status")
//    public Task updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
//        return taskService.updateTaskStatus(id, status);
//    }