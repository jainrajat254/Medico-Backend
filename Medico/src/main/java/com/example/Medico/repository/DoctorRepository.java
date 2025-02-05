package com.example.Medico.repository;

import com.example.Medico.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Doctor findByEmail(String email);

    Optional<Doctor> findById(UUID id); // Find doctor by UID
}
