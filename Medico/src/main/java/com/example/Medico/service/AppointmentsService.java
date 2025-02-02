package com.example.Medico.service;

import com.example.Medico.model.Appointments;
import com.example.Medico.model.Users;
import com.example.Medico.repository.AppointmentsRepository;
import com.example.Medico.repository.UserRepository;
import com.example.Medico.responses.AppointmentsResponse;
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


    public Appointments addAppointments(AppointmentsResponse appointmentsResponse, UUID id) {
        Users user = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));
        Appointments appointments = new Appointments(
                user,
                appointmentsResponse.getDoctorName(),
                appointmentsResponse.getType(),
                appointmentsResponse.getSpecialist(),
                appointmentsResponse.getTime()
                );
        return appointmentsRepository.save(appointments);
    }

    public List<Appointments> getAppointments(UUID id) {
        return appointmentsRepository.findByUsers_Id(id);
    }
}
