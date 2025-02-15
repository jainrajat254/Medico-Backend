package com.example.Medico.common.controllers;

import com.example.Medico.common.model.LoginCredentials;
import com.example.Medico.common.service.LoginService;
import com.example.Medico.doctor.responses.DoctorLoginResponse;
import com.example.Medico.user.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {
        Object response = loginService.login(credentials);

        if (response instanceof DoctorLoginResponse || response instanceof UserResponse) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}