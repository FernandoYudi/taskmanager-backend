package org.kikuchi.taskmanagerbackend.dto.Tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.kikuchi.taskmanagerbackend.enums.TaskStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
}
