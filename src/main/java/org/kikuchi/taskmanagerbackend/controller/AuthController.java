package org.kikuchi.taskmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.Auth.AuthRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Auth.AuthResponseDTO;
import org.kikuchi.taskmanagerbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO dto){
        return userService.login(dto);
    }
}
