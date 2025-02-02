package com.example.Medico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Users users;

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @Column(name = "type", nullable = false)
    private String type;

    public Appointments(Users users, String doctorName, String type, String specialist, LocalDateTime time) {
        this.users = users;
        this.doctorName = doctorName;
        this.type = type;
        this.specialist = specialist;
        this.time = time;
    }

    @Column(name = "specialist", nullable = false)
    private String specialist;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Appointments() {

    }

    public Appointments(UUID id, Users users, String doctorName, String type, String specialist, LocalDateTime time) {
        this.id = id;
        this.users = users;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
