package com.example.Medico.controllers;

import com.example.Medico.model.PersonalInfo;
import com.example.Medico.responses.PersonalInfoResponse;
import com.example.Medico.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/personalInfo")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService personalInfoService;

    @PostMapping("/addPersonalInfo/{id}")
    public PersonalInfo addPersonalInfo(@RequestBody PersonalInfoResponse personalInfoResponse, UUID id) {
        return personalInfoService.addPersonalInfo(personalInfoResponse,id);
    }

    @GetMapping("/getPersonalInfo/{id}")
    public PersonalInfo getPersonalInfo(UUID id) {
        return personalInfoService.getPersonalInfo(id);
    }

    @PutMapping("/uploadPhoto/{id}")
    public PersonalInfo uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable UUID id) throws IOException {
        return personalInfoService.addPhoto(file, id);
    }

    @GetMapping("/getPhoto/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable UUID id) {
        PersonalInfo personalInfo = personalInfoService.getPersonalInfo(id);
        byte[] photo = personalInfo.getPhoto();
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(photo);
    }
}
