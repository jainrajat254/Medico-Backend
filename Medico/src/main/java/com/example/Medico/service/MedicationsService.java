package com.example.Medico.service;

import com.example.Medico.model.Medications;
import com.example.Medico.responses.MedicationsResponse;
import com.example.Medico.model.Users;
import com.example.Medico.repository.MedicationsRepository;
import com.example.Medico.repository.UserRepository;
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
                medicationsResponse.getFrequency(),
                user
        );
        return medicationsRepository.save(medication);
    }

    public List<Medications> getMedications(UUID id) {
        return medicationsRepository.findByUsers_Id(id);
    }
}
