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
}

//    private final TaskRepo taskRepo;
//
//    @Autowired
//    public TaskService(TaskRepo taskRepo) {
//        this.taskRepo = taskRepo;
//    }
//
//    public List<Task> findAllTasks() {
//        return taskRepo.findAll();
//    }
//
//    public Task saveTask(Task task) {
//        return taskRepo.save(task);
//    }
//
//    public Optional<Task> findTaskById(Long id) {
//        return taskRepo.findById(id);
//    }
//
//
////    This means that Spring takes care of starting a transaction before the method execution and committing the transaction
////    if the method completes successfully, or rolling it back if there is an exception.
//
//    @Transactional
//    public Task updateTaskStatus(Long id, TaskStatus status) {
//        Task task = taskRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
//        task.setStatus(status);
//        return taskRepo.save(task);
//    }
//
//
//
//    public List<Task> findTasksByStatus(TaskStatus status) {
//        return taskRepo.findByStatus(status);
//    }
//
//
//    public void deleteTaskById(Long id) {
//        taskRepo.deleteById(id);
//    }
//
//    public void deleteAll() {
//        taskRepo.deleteAll();
//    }
//}




//    @Transactional
//    public ResponseMessage updateTaskStatus(Long id, TaskStatus status) {
//        Task task = taskRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
//        task.setStatus(status);
//        Task updatedTask = taskRepo.save(task);
//        return new ResponseMessage(updatedTask, "Task status updated successfully");
//    }


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
