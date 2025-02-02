package com.example.Medico.repository;

import com.example.Medico.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, UUID> {
    List<Appointments> findByUsers_Id(UUID id);
}
