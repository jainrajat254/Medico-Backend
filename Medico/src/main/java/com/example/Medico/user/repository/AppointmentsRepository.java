package com.example.Medico.user.repository;

import com.example.Medico.user.dto.AppointmentDTO;
import com.example.Medico.user.model.Appointments;
import com.example.Medico.user.responses.AppointmentsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, UUID> {
    List<Appointments> findByUsers_Id(UUID id);

    @Query("SELECT new com.example.Medico.user.dto.AppointmentDTO(u.users.id, u.patientName, u.date, u.time, u.appointmentBookingTime) FROM Appointments u WHERE u.doctorId = :doctorId")
    List<AppointmentDTO> findByDoctor_Id(@Param("doctorId") UUID doctorId);

}
