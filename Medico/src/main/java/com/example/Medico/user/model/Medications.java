package com.example.Medico.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "medications")
public class Medications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Users users;

    @Column(name = "medication_name", nullable = false)
    private String medicationName;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "dosage_type", nullable = false)
    private String dosageType; // New Field ✅

    @Column(name = "medication_type", nullable = false)
    private String medicationType; // New Field ✅

    @Column(name = "frequency", nullable = false)
    private String frequency;

    @Column(name = "duration", nullable = false)
    private String duration; // New Field ✅

    @Column(name = "intake_method", nullable = false)
    private String intakeMethod; // New Field ✅

    @Column(name = "time")
    private String time; // New Field (Optional) ⏰

    // ✅ Constructor (with ID for custom assignment)
    public Medications(UUID id, String medicationName, String dosage, String dosageType,
                       String medicationType, String frequency, String duration,
                       String intakeMethod, String time, Users users) {
        this.id = id;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.dosageType = dosageType;
        this.medicationType = medicationType;
        this.frequency = frequency;
        this.duration = duration;
        this.intakeMethod = intakeMethod;
        this.time = time;
        this.users = users;
    }

    // ✅ Constructor (without ID for auto-generated)
    public Medications(String medicationName, String dosage, String dosageType,
                       String medicationType, String frequency, String duration,
                       String intakeMethod, String time, Users users) {
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.dosageType = dosageType;
        this.medicationType = medicationType;
        this.frequency = frequency;
        this.duration = duration;
        this.intakeMethod = intakeMethod;
        this.time = time;
        this.users = users;
    }

    // ✅ Default constructor
    public Medications() {
    }

    // ✅ Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosageType() {
        return dosageType;
    }

    public void setDosageType(String dosageType) {
        this.dosageType = dosageType;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntakeMethod() {
        return intakeMethod;
    }

    public void setIntakeMethod(String intakeMethod) {
        this.intakeMethod = intakeMethod;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
