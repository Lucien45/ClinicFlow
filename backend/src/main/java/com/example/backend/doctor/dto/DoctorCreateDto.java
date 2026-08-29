package com.example.backend.doctor.dto;

import com.example.backend.user.entity.User;

public class DoctorCreateDto {
    private String speciality;
    private String description;
    private boolean available;
    private User userId;

    public DoctorCreateDto() {}

    public DoctorCreateDto(String speciality, String description, boolean available, User userId) {
        this.speciality = speciality;
        this.description = description;
        this.available = available;
        this.userId = userId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
