package org.kikuchi.taskmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.kikuchi.taskmanagerbackend.dto.Auth.AuthRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Auth.AuthResponseDTO;
import org.kikuchi.taskmanagerbackend.dto.Users.UserRequestDTO;
import org.kikuchi.taskmanagerbackend.dto.Users.UserResponseDTO;
import org.kikuchi.taskmanagerbackend.exception.InvalidCredentialsException;
import org.kikuchi.taskmanagerbackend.exception.UserNotFoundException;
import org.kikuchi.taskmanagerbackend.repository.UserRepository;
import org.kikuchi.taskmanagerbackend.enums.UserRole;
import org.kikuchi.taskmanagerbackend.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public AuthResponseDTO login(AuthRequestDTO dto){
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        boolean passwordMatches = passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
                );

        if(!passwordMatches){
            throw new InvalidCredentialsException("Senha Inválida");
        }

        return new AuthResponseDTO("Login realizado com sucesso!");
    }

    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(UserRole.ADMIN);
        user.setPassword(
                passwordEncoder.encode(dto.getPassword()));

        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
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
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getCreatedAt()
                ))
                .toList();
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
                savedUser.getRole(),
                savedUser.getCreatedAt()
        );
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
