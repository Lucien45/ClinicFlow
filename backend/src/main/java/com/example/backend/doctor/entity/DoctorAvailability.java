package com.example.backend.doctor.entity;

import java.time.DayOfWeek;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_availability_generator")
    private long id;

    @Column(name = "dayOfWeek")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time")
    private String start_time;

    @Column(name = "end_time")
    private String end_time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    public DoctorAvailability() {}

    public DoctorAvailability(long id, DayOfWeek dayOfWeek, String start_time, String end_time, Doctor doctor) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.start_time = start_time;
        this.end_time = end_time;
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
