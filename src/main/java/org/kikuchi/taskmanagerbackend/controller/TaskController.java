package org.kikuchi.taskmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.service.TaskService;
import org.kikuchi.taskmanagerbackend.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.getTaskId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
         taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
}