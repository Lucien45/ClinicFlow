package com.example.backend.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.common.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.findall();

        ApiResponse<List<User>> response = ApiResponse.<List<User>>builder()
                .success(true)
                .message("Users retrieved successfully")
                .data(users)
                .build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);

        ApiResponse<User> response = ApiResponse.<User>builder()
                .success(true)
                .message("User retrieved successfully")
                .data(user)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/users")
    // public ResponseEntity<User> createUser(@RequestBody User user) {
    //     User createdUser = userService.save(user);
    //     return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    // }
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserCreateDto userCreateDto) {
        // userService.createUser(userCreateDto);
        // return ResponseEntity.status(HttpStatus.CREATED).build();
        User createdUser = userService.createUser(userCreateDto);

        ApiResponse<User> response = ApiResponse.<User>builder()
                .success(true)
                .message("User created successfully")
                .data(createdUser)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable("id") Long id, @RequestBody User userRequest) {        
        // return userService.update(id, userRequest)

        //     .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
        //     .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        User updatedUser = userService.update(id, userRequest);

        ApiResponse<User> response = ApiResponse.<User>builder()
                .success(true)
                .message("User updated successfully")
                .data(updatedUser)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") Long id) {
        // try {
        //     userService.deleteById(id);
        //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // } catch (Exception e) {
        //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // }

        userService.deleteById(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("User deleted successfully")
                .data(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }

    @DeleteMapping("/users")
    public ResponseEntity<ApiResponse<Void>> deleteAllUsers() {
        // try {
        //     userService.deleteAll();
        //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // } catch (Exception e) {
        //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // }
        userService.deleteAll();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("All users deleted successfully")
                .data(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }
}
