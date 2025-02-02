package com.example.Medico.controllers;

import com.example.Medico.model.Medications;
import com.example.Medico.responses.MedicationsResponse;
import com.example.Medico.service.MedicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medications")
public class MedicationsController {

    @Autowired
    MedicationsService medicationsService;

    @PostMapping("/addMedication/{id}")
    public Medications addMedication(@RequestBody MedicationsResponse medicationsResponse, @PathVariable UUID id) {
        return medicationsService.addMedication(medicationsResponse, id);
    }

    @GetMapping("/getMedication/{id}")
    public List<Medications> getMedications(@PathVariable UUID id) {
        return medicationsService.getMedications(id);
    }
}
