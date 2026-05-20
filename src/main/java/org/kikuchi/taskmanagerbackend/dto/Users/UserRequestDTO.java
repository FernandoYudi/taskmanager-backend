package org.kikuchi.taskmanagerbackend.dto.Users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.kikuchi.taskmanagerbackend.enums.UserRole;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "Usuário não encontrado")
    private String name;
    private String email;
    private UserRole role;
    private String password;
}
