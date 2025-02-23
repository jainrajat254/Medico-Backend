package com.example.Medico.user.service;

import com.example.Medico.doctor.repository.DoctorRepository;
import com.example.Medico.user.dto.AppointmentDTO;
import com.example.Medico.user.model.Appointments;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.repository.AppointmentsRepository;
import com.example.Medico.user.repository.UserRepository;
import com.example.Medico.user.responses.AppointmentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @Autowired
    DoctorRepository doctorRepository;


    public Appointments addAppointments(AppointmentsResponse appointmentsResponse, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Appointments appointment = new Appointments();
        appointment.setUsers(user);
        appointment.setDoctorId(appointmentsResponse.getDoctorId());
        appointment.setDoctorName(appointmentsResponse.getDoctorName());
        appointment.setPatientName(appointmentsResponse.getPatientName());
        appointment.setSpecialization(appointmentsResponse.getSpecialization());
        appointment.setWorkspaceName(appointmentsResponse.getWorkspaceName());
        appointment.setDate(appointmentsResponse.getDate());
        appointment.setTime(appointmentsResponse.getTime());
        return appointmentsRepository.save(appointment);
    }

    public List<Appointments> getAppointments(UUID id) {
        return appointmentsRepository.findByUsers_Id(id);
    }

    public List<AppointmentDTO> getDoctorAppointments(UUID doctorId) {
        return appointmentsRepository.findByDoctor_Id(doctorId);
    }
}
