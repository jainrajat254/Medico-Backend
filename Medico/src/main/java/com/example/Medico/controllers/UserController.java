package com.example.Medico.controllers;

import com.example.Medico.DTO.EditUserDTO;
import com.example.Medico.model.*;
import com.example.Medico.responses.UserResponse;
import com.example.Medico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/u")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("editDetails/{id}")
    public Users editDetails(@RequestBody @Valid EditUserDTO userDTO, @PathVariable UUID id) {
        return userService.editDetails(userDTO, id);
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

}
