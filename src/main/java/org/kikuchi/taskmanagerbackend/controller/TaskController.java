package org.kikuchi.taskmanagerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.Tasks.TaskRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Tasks.TaskResponseDTO;
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
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO dto){
        return taskService.createTask(dto);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTask(@PathVariable Long id){
        return taskService.getTaskId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
         taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@Valid @PathVariable Long id, @RequestBody TaskRequestDTO dto){
        return taskService.updateTask(id, dto);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
}