package com.example.Medico.controllers;

import com.example.Medico.model.*;
import com.example.Medico.responses.UserResponse;
import com.example.Medico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/u")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<UserResponse> login(@RequestBody LoginCredentials credentials) {
//        UserResponse userResponse = userService.login(credentials);
//        return userResponse != null ? ResponseEntity.ok(userResponse)
//                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

}
