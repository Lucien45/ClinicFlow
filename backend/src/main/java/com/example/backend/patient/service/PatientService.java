package com.example.backend.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.patient.dto.PatientCreateDto;
import com.example.backend.patient.entity.Patient;
import com.example.backend.patient.repository.PatientRepository;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public void createPatient(PatientCreateDto patientCreateDto) {
        try {
            Patient patient = toEntity(patientCreateDto);
            patientRepository.save(patient);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create patient", e);
        }
    }

    public Optional<Patient> update(Long id, Patient patient) {
        return patientRepository.findById(id)
            .map(existingPatient -> {
                patient.setId(id);
                return patientRepository.save(patient);
            });
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public void deleteAll() {
        patientRepository.deleteAll();
    }

    // public List<Patient> findByUserId(Long userId) {
    //     return patientRepository.findByUserId(userId);
    // }

    private Patient toEntity(PatientCreateDto patientCreateDto) {
        Patient patient = new Patient();
        patient.setMedicalNumber(patientCreateDto.getMedicalNumber());
        patient.setDateOfBirth(patientCreateDto.getDateOfBirth());
        patient.setPhoneNumber(patientCreateDto.getPhoneNumber());
        patient.setAddress(patientCreateDto.getAddress());
        patient.setUser(patientCreateDto.getUserId());
        return patient;
    }
}
