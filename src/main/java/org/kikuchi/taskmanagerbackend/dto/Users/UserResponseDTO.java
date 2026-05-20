package org.kikuchi.taskmanagerbackend.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.kikuchi.taskmanagerbackend.enums.UserRole;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
    private LocalDateTime createdAt;
}
