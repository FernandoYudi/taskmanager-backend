package org.kikuchi.taskmanagerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.Users.UserRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Users.UserResponseDTO;
import org.kikuchi.taskmanagerbackend.service.UserService;
import org.kikuchi.taskmanagerbackend.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@Valid @PathVariable Long id, @RequestBody UserRequestDTO dto){
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@Valid @PathVariable Long id){
        userService.deleteUser(id);
    }
}
