package com.example.backend.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.user.dto.UserCreateDto;
import com.example.backend.user.entity.User;
import com.example.backend.user.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findall();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        return userService.findById(id)
            .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/users")
    // public ResponseEntity<User> createUser(@RequestBody User user) {
    //     User createdUser = userService.save(user);
    //     return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    // }
    public ResponseEntity<Void> createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User userRequest) {        
        return userService.update(id, userRequest)
            .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
