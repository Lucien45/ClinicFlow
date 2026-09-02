package com.example.backend.appointment.dto;

import com.example.backend.appointment.entity.Status;
import com.example.backend.doctor.entity.Doctor;
import com.example.backend.patient.entity.Patient;

public class AppointmentCreateDto {
    private String date;
    private String start_time;
    private String end_time;
    private String reason;
    private Status status;
    private Patient patientId;
    private Doctor doctorId;

    public AppointmentCreateDto() {}

    public AppointmentCreateDto(String date, String start_time, String end_time, String reason, Status status, Patient patientId, Doctor doctorId) {
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.reason = reason;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }
}
