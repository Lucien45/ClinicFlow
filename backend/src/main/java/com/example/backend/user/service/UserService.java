package com.example.backend.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.user.dto.UserCreateDto;
import com.example.backend.user.entity.User;
import com.example.backend.user.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findall() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException(
                    "User not found with id: " + id
                )
            );
    }
 
    public User createUser(UserCreateDto userCreateDto) {
        User user = toEntity(userCreateDto);

        return userRepository.save(user);
    }

    public User update(Long id, User userRequest) {
        
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException(
                    "User not found with id: " + id
                )
            );

        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setRole(userRequest.getRole());

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "User not found with id: " + id
            );
        }

        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    private User toEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(userCreateDto.getRole());
        return user;
    }
    
}
