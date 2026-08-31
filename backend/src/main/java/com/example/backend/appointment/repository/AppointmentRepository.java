package com.example.backend.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.appointment.entity.Appointment;
import com.example.backend.appointment.entity.Status;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByStatus(Status status);
    List<Appointment> findByDate(String date);
}
