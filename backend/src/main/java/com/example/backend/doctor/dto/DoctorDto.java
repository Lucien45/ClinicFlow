package com.example.backend.doctor.dto;

import com.example.backend.user.entity.User;

public class DoctorDto {
    private long id;
    private String speciality;
    private String description;
    private boolean available;
    private User userId;

    public DoctorDto() {}

    public DoctorDto(long id, String speciality, String description, boolean available, User userId) {
        this.id = id;
        this.speciality = speciality;
        this.description = description;
        this.available = available;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
