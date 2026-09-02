package com.example.backend.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUserId(Long userId);
}
