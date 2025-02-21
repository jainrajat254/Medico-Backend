package com.example.Medico.user.controller;

import com.example.Medico.user.dto.MedicationsDTO;
import com.example.Medico.user.model.Medications;
import com.example.Medico.user.responses.MedicationsResponse;
import com.example.Medico.user.service.MedicationsService;
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

    @GetMapping("/doctorMedication/{doctorId}/{userId}")
    public List<MedicationsDTO> doctorMedication(@PathVariable UUID doctorId, @PathVariable UUID userId) {
        return medicationsService.doctorMedication(doctorId,userId);
    }

    @PutMapping("/updateMedication/{medId}")
    public Medications updateMedication(@PathVariable UUID medId, @RequestBody Medications updatedMedication) {
        return medicationsService.updateMedication(medId, updatedMedication);
    }
}
