package com.example.Medico.responses;

import java.time.LocalDate;
import java.util.UUID;

public class PersonalInfoResponse {
    private UUID id;
    private String height;
    private String weight;
    private LocalDate dob;
    private String emergencyContactName;
    private String emergencyContactRelation;
    private String emergencyContactPhone;
    private String currentAddress;
    private String permanentAddress;
    private String knownHealthConditions;
    private String familyMedicalHistory;
    private String insuranceProvider;
    private String policyNumber;
    private String groupNumber;
    private String coverageDetails;
    private byte[] photo;

    public PersonalInfoResponse() {
    }

    public PersonalInfoResponse(String height, String weight, LocalDate dob, String emergencyContactName, String emergencyContactRelation, String emergencyContactPhone, String currentAddress, String permanentAddress, String knownHealthConditions, String familyMedicalHistory, String insuranceProvider, String policyNumber, String groupNumber, String coverageDetails) {
        this.height = height;
        this.weight = weight;
        this.dob = dob;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactRelation = emergencyContactRelation;
        this.emergencyContactPhone = emergencyContactPhone;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.knownHealthConditions = knownHealthConditions;
        this.familyMedicalHistory = familyMedicalHistory;
        this.insuranceProvider = insuranceProvider;
        this.policyNumber = policyNumber;
        this.groupNumber = groupNumber;
        this.coverageDetails = coverageDetails;
    }

    public PersonalInfoResponse(UUID id, String height, String weight, LocalDate dob, String emergencyContactName, String emergencyContactRelation, String emergencyContactPhone, String currentAddress, String permanentAddress, String knownHealthConditions, String familyMedicalHistory, String insuranceProvider, String policyNumber, String groupNumber, String coverageDetails) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.dob = dob;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactRelation = emergencyContactRelation;
        this.emergencyContactPhone = emergencyContactPhone;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.knownHealthConditions = knownHealthConditions;
        this.familyMedicalHistory = familyMedicalHistory;
        this.insuranceProvider = insuranceProvider;
        this.policyNumber = policyNumber;
        this.groupNumber = groupNumber;
        this.coverageDetails = coverageDetails;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }

    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getKnownHealthConditions() {
        return knownHealthConditions;
    }

    public void setKnownHealthConditions(String knownHealthConditions) {
        this.knownHealthConditions = knownHealthConditions;
    }

    public String getFamilyMedicalHistory() {
        return familyMedicalHistory;
    }

    public void setFamilyMedicalHistory(String familyMedicalHistory) {
        this.familyMedicalHistory = familyMedicalHistory;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
