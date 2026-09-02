package com.example.backend.doctor.dto;

import java.time.DayOfWeek;

import com.example.backend.doctor.entity.Doctor;

public class DoctorAvailabilityCreateDto {
    private DayOfWeek dayOfWeek;
    private String start_time;
    private String end_time;
    private Doctor doctorId;

    public DoctorAvailabilityCreateDto() {}

    public DoctorAvailabilityCreateDto(DayOfWeek dayOfWeek, String start_time, String end_time, Doctor doctorId) {
        this.dayOfWeek = dayOfWeek;
        this.start_time = start_time;
        this.end_time = end_time;
        this.doctorId = doctorId;
    }

    // Getters and setters

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }
}
