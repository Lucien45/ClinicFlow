package com.example.backend.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.user.dto.UserCreateDto;
import com.example.backend.user.dto.UserDto;
import com.example.backend.user.entity.User;
import com.example.backend.user.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findall() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
 
    // public User save(User user) {
    //     return userRepository.save(user);
    // }
    public void createUser(UserCreateDto userCreateDto) {
        User user = toEntity(userCreateDto);
        userRepository.save(user);
    }

    public Optional<User> update(Long id, User user) {
        return userRepository.findById(id)
            .map(existingUser -> {
                user.setId(id);
                return userRepository.save(user);
            });
    }

    public void deleteById(Long id) {
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
        user.setPassword(userCreateDto.getPassword());
        return user;
    }
    
}
