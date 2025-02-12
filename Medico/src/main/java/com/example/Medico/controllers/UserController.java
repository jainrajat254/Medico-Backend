package com.example.Medico.controllers;

import com.example.Medico.DTO.EditUserDTO;
import com.example.Medico.DTO.PasswordUpdateRequest;
import com.example.Medico.model.*;
import com.example.Medico.responses.UserResponse;
import com.example.Medico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @PutMapping("editPassword/{id}")
    public ResponseEntity<?> editPassword(@RequestBody PasswordUpdateRequest request, @PathVariable UUID id) {
        try {
            userService.editPassword(request, id);
            return ResponseEntity.ok().body("Password updated successfully.");
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect current password.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

}
