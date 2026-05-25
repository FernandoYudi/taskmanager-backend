package org.kikuchi.taskmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.Users.UserRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Users.UserResponseDTO;
import org.kikuchi.taskmanagerbackend.enums.UserRole;
import org.kikuchi.taskmanagerbackend.exception.UserNotFoundException;
import org.kikuchi.taskmanagerbackend.model.User;
import org.kikuchi.taskmanagerbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(UserRole.ADMIN);
        user.setPassword(dto.getPassword());

        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getRole(),
                savedUser.getCreatedAt()
        );
    }

    public UserResponseDTO getUserId(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException("Usuário não encontrado"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto){
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Usuário não encontrado"));

        existingUser.setName(dto.getName());
        existingUser.setEmail(dto.getEmail());
        existingUser.setPassword(dto.getPassword());

        User savedUser = userRepository.save(existingUser);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getRole(),
                savedUser.getCreatedAt()
        );
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
