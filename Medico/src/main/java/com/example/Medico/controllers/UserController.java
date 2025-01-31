package com.example.Medico.controllers;

import com.example.Medico.model.LoginCredentials;
import com.example.Medico.model.LoginResponse;
import com.example.Medico.model.Users;
import com.example.Medico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCredentials credentials) {
        LoginResponse loginResponse = userService.login(credentials);
        return loginResponse != null ? ResponseEntity.ok(loginResponse)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

}
