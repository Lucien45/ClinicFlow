package com.example.backend.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.doctor.dto.DoctorCreateDto;
import com.example.backend.doctor.entity.Doctor;
import com.example.backend.doctor.repository.DoctorRepository;

@Service
public class DoctorService {
    

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    public void createDoctor(DoctorCreateDto doctorCreateDto) {
        Doctor doctor = toEntity(doctorCreateDto);
        doctorRepository.save(doctor);
    }

    public Optional<Doctor> update(Long id, Doctor doctor) {
        return doctorRepository.findById(id)
            .map(existingDoctor -> {
                doctor.setId(id);
                return doctorRepository.save(doctor);
            });
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    public List<Doctor> findBySpeciality(String speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }

    public List<Doctor> findByUserId(Long userId) {
        return doctorRepository.findByUserId(userId);
    }

    private Doctor toEntity(DoctorCreateDto doctorCreateDto) {
        Doctor doctor = new Doctor();
        doctor.setSpeciality(doctorCreateDto.getSpeciality());
        doctor.setDescription(doctorCreateDto.getDescription());
        doctor.setAvailable(doctorCreateDto.isAvailable());
        doctor.setUser(doctorCreateDto.getUserId());
        return doctor;
    }
    
}
