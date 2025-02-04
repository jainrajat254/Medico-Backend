package com.example.Medico.controllers;

import com.example.Medico.model.Doctor;
import com.example.Medico.model.LoginCredentials;
import com.example.Medico.responses.DoctorResponse;
import com.example.Medico.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/login")
    public ResponseEntity<DoctorResponse> login(@RequestBody LoginCredentials credentials) {
        DoctorResponse doctorResponse = doctorService.login(credentials);
        return doctorResponse != null ? ResponseEntity.ok(doctorResponse)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/register")
    public Doctor register(@RequestBody Doctor doctor) {
        return doctorService.register(doctor);
    }

    @PutMapping("/uploadPhoto/{id}")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable UUID id) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty. Please upload a valid image.");
        }
        doctorService.addPhoto(file, id);
        return ResponseEntity.ok("Photo uploaded successfully");
    }

    @GetMapping("/getPhoto/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable UUID id) {
        Doctor doctor = doctorService.getPersonalInfo(id); // Use UID to fetch doctor

        if (doctor == null || doctor.getProfilePicture() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(doctor.getProfilePicture());
    }
}
