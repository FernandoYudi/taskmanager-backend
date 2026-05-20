package org.kikuchi.taskmanagerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.Users.UserRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Users.UserResponseDTO;
import org.kikuchi.taskmanagerbackend.service.UserService;
import org.kikuchi.taskmanagerbackend.model.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto){
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserId(@PathVariable Long id){
        return userService.getUserId(id);
    }
}
