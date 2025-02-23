package com.example.Medico.user.controller;

import com.example.Medico.doctor.model.DoctorDetails;
import com.example.Medico.doctor.service.DoctorService;
import com.example.Medico.user.dto.AppointmentDTO;
import com.example.Medico.user.model.Appointments;
import com.example.Medico.user.responses.AppointmentsResponse;
import com.example.Medico.user.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    @Autowired
    AppointmentsService appointmentsService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/addAppointments/{id}")
    public Appointments addAppointments(@RequestBody AppointmentsResponse appointmentsResponse, @PathVariable UUID id) {
        return appointmentsService.addAppointments(appointmentsResponse,id);
    }

    @GetMapping("/getAppointments/{id}")
    public List<Appointments> getAppointments(@PathVariable UUID id) {
        return appointmentsService.getAppointments(id);
    }

    @GetMapping("getDoctorAppointments/{doctorId}")
    public List<AppointmentDTO> getDoctorAppointments(@PathVariable UUID doctorId) {
        return appointmentsService.getDoctorAppointments(doctorId);
    }
}
