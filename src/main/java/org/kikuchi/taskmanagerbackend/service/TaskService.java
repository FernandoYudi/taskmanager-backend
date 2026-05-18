package org.kikuchi.taskmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.TaskRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.TaskResponseDTO;
import org.kikuchi.taskmanagerbackend.model.Task;
import org.kikuchi.taskmanagerbackend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponseDTO createTask(TaskRequestDTO dto){
        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());

        task.setCreatedAt(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);

        return new TaskResponseDTO(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCreatedAt()
        );
    }

    public TaskResponseDTO getTaskId(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not Found"));
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(dto.getTitle());
        existingTask.setDescription(dto.getDescription());
        existingTask.setStatus(dto.getStatus());

        Task savedTask = taskRepository.save(existingTask);

        return new TaskResponseDTO(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCreatedAt()
        );
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
