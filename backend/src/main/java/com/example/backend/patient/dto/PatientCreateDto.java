package com.example.backend.patient.dto;

import com.example.backend.user.entity.User;

public class PatientCreateDto {
    private String medicalNumber;
    private String dateOfBirth;
    private String phoneNumber;
    private String address;
    private User userId;

    public PatientCreateDto() {}

    public PatientCreateDto(String medicalNumber, String dateOfBirth, String phoneNumber, String address, User userId) {
        this.medicalNumber = medicalNumber;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userId = userId;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
