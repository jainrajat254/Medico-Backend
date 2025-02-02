package com.example.Medico.repository;

import com.example.Medico.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordsRepository extends JpaRepository<Records, UUID> {
    List<Records> findByUsers_Id(UUID id);
}
