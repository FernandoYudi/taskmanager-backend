package org.kikuchi.taskmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.model.Task;
import org.kikuchi.taskmanagerbackend.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task){
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task getTaskId(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not Found"));
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id,Task newTask){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(newTask.getTitle());
        existingTask.setDescription(newTask.getDescription());
        existingTask.setStatus(newTask.getStatus());

        return taskRepository.save(existingTask);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
