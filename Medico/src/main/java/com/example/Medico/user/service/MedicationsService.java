package com.example.Medico.user.service;

import com.example.Medico.user.model.Medications;
import com.example.Medico.user.responses.MedicationsResponse;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.repository.MedicationsRepository;
import com.example.Medico.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicationsService {

    @Autowired
    private MedicationsRepository medicationsRepository;

    @Autowired
    private UserRepository userRepository;

    public Medications addMedication(MedicationsResponse medicationsResponse, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Medications medication = new Medications(
                medicationsResponse.getMedicationName(),
                medicationsResponse.getDosage(),
                medicationsResponse.getDosageType(),   // ✅ Added new field
                medicationsResponse.getMedicationType(), // ✅ Added new field
                medicationsResponse.getFrequency(),
                medicationsResponse.getDuration(),    // ✅ Added new field
                medicationsResponse.getIntakeMethod(), // ✅ Added new field
                medicationsResponse.getTime(),        // ✅ Added new field
                user
        );

        return medicationsRepository.save(medication);
    }

    public List<Medications> getMedications(UUID id) {
        return medicationsRepository.findByUsers_Id(id);
    }
}
