package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.service.UserService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> userEntities = userService.getAllUsers();
        return ResponseEntity.ok(userEntities);
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ApiResponse<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @PutMapping()
    public UserEntity updateUser(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ApiResponse<UserEntity> login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

}
