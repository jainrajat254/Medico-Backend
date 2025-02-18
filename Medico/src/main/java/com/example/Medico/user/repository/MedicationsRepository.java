package com.example.Medico.user.repository;

import com.example.Medico.user.model.Medications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicationsRepository extends JpaRepository<Medications, UUID> {

    List<Medications> findByUsers_Id(UUID id);
}
