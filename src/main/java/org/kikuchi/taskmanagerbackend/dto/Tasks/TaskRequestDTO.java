package org.kikuchi.taskmanagerbackend.dto.Tasks;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.kikuchi.taskmanagerbackend.enums.TaskStatus;

@Getter
@Setter
public class TaskRequestDTO {

    @NotBlank(message = "Titúlo não pode ser vazio")
    private String title;
    private String description;
    private TaskStatus status;
}
