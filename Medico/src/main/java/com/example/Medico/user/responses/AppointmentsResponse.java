package com.example.Medico.user.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentsResponse {
    private UUID id;
    private String doctorName;
    private String type;
    private String specialist;
    private LocalDateTime time;

    private AppointmentsResponse() {

    }

    public AppointmentsResponse(UUID id, String doctorName, String type, String specialist, LocalDateTime time) {
        this.id = id;
        this.doctorName = doctorName;
        this.type = type;
        this.specialist = specialist;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
