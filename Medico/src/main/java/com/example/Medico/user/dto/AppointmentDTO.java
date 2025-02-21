package com.example.Medico.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentDTO {
    private UUID userId;
    private String patientName;
    private String date;
    private String time;
    private LocalDateTime appointmentBookingTime;

    public AppointmentDTO(UUID userId, String patientName, String date, String time, LocalDateTime appointmentBookingTime) {
        this.userId = userId;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.appointmentBookingTime = appointmentBookingTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDateTime getAppointmentBookingTime() {
        return appointmentBookingTime;
    }

    public void setAppointmentBookingTime(LocalDateTime appointmentBookingTime) {
        this.appointmentBookingTime = appointmentBookingTime;
    }

    // Getters and setters
}

