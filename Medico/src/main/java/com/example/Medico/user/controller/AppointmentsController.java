package com.example.Medico.user.controller;

import com.example.Medico.user.model.Appointments;
import com.example.Medico.user.responses.AppointmentsResponse;
import com.example.Medico.user.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    @Autowired
    AppointmentsService appointmentsService;

    @PostMapping("/addAppointments/{id}")
    public Appointments addAppointments(@RequestBody AppointmentsResponse appointmentsResponse, @PathVariable UUID id) {
        return appointmentsService.addAppointments(appointmentsResponse, id);
    }

    @GetMapping("/getAppointments/{id}")
    public List<Appointments> getAppointments(@PathVariable UUID id) {
        return appointmentsService.getAppointments(id);
    }
}
