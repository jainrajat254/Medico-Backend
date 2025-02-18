package com.example.Medico.user.repository;

import com.example.Medico.user.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportsRepository extends JpaRepository <Reports, UUID> {
    List<Reports> findByUsers_Id(UUID id);
}
