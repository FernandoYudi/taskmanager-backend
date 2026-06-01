package org.kikuchi.taskmanagerbackend.dto.Auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {

    private String email;
    private String password;
}
