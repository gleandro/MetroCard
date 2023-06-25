package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.service.UserService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserEntity> getUserByDni(@PathVariable("id") String id) {
        return userService.getUserByCode(id);
    }

    @PostMapping
    public ApiResponse<UserEntity> add(@RequestBody UserEntity userEntity) {
        return userService.add(userEntity);
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
