package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.service.impl.EmailService;
import com.gleandro.metrocardapplication.service.impl.UserService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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
    public ApiResponse<UserEntity> updateUser(@RequestBody UserEntity userEntity) {
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

    @GetMapping("/reset-password")
    public Map<String, String> enviarCorreo(@RequestParam String dni) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", emailService.enviarCorreo(dni));
        return response;
    }

}
