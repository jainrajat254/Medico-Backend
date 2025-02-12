package com.example.Medico.controllers;

import com.example.Medico.DTO.DocAddressDetailsDTO;
import com.example.Medico.DTO.DocMedicalDetailsDTO;
import com.example.Medico.DTO.EditDocDTO;
import com.example.Medico.model.Doctor;
import com.example.Medico.model.LoginCredentials;
import com.example.Medico.responses.DoctorResponse;
import com.example.Medico.responses.UserResponse;
import com.example.Medico.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {
        Object response = doctorService.login(credentials);

        if (response instanceof DoctorResponse || response instanceof UserResponse) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    @PostMapping("/register")
    public Doctor register(@RequestBody Doctor doctor) {
        return doctorService.register(doctor);
    }

    @PutMapping("editDocPersonalDetails/{id}")
    public Doctor editDocPersonalDetails(@RequestBody @Valid EditDocDTO userDTO, @PathVariable UUID id) {
        return doctorService.editDocPersonalDetails(userDTO, id);
    }

    @PutMapping("editDocAddressDetails/{id}")
    public Doctor editDocAddressDetails(@RequestBody @Valid DocAddressDetailsDTO docDTO, @PathVariable UUID id) {
        return doctorService.editDocAddressDetails(docDTO, id);
    }

    @PutMapping("editDocMedicalDetails/{id}")
    public Doctor editDocMedicalDetails(@RequestBody @Valid DocMedicalDetailsDTO docDTO, @PathVariable UUID id) {
        return doctorService.editDocMedicalDetails(docDTO, id);
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
