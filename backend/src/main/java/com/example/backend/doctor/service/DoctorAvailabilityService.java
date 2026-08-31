package com.example.backend.doctor.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.doctor.dto.DoctorAvailabilityCreateDto;
import com.example.backend.doctor.entity.DoctorAvailability;
import com.example.backend.doctor.repository.DoctorAvailabilityRepository;

@Service
public class DoctorAvailabilityService {
    
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    public DoctorAvailabilityService(DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public List<DoctorAvailability> findAll() {
        return doctorAvailabilityRepository.findAll();
    }

    public Optional<DoctorAvailability> findById(Long id) {
        return doctorAvailabilityRepository.findById(id);
    }

    public void createDoctorAvailability(DoctorAvailabilityCreateDto doctorAvailabilityCreateDto) {
        DoctorAvailability doctorAvailability = toEntity(doctorAvailabilityCreateDto);
        doctorAvailabilityRepository.save(doctorAvailability);
    }

    public Optional<DoctorAvailability> update(Long id, DoctorAvailability doctorAvailability) {
        return doctorAvailabilityRepository.findById(id)
            .map(existingDoctorAvailability -> {
                doctorAvailability.setId(id);
                return doctorAvailabilityRepository.save(doctorAvailability);
            });
    }

    public void deleteById(Long id) {
        doctorAvailabilityRepository.deleteById(id);
    }

    public void deleteAll() {
        doctorAvailabilityRepository.deleteAll();
    }

    public List<DoctorAvailability> findByDoctorId(Long doctorId) {
        return doctorAvailabilityRepository.findByDoctorId(doctorId);
    }

    public List<DoctorAvailability> findByDayOfWeek(DayOfWeek dayOfWeek) {
        return doctorAvailabilityRepository.findByDayOfWeek(dayOfWeek);
    }

    private DoctorAvailability toEntity(DoctorAvailabilityCreateDto doctorAvailabilityCreateDto) {
        DoctorAvailability doctorAvailability = new DoctorAvailability();
        doctorAvailability.setDayOfWeek(doctorAvailability.getDayOfWeek());
        doctorAvailability.setStart_time(doctorAvailability.getStart_time());
        doctorAvailability.setEnd_time(doctorAvailability.getEnd_time());
        doctorAvailability.setDoctor(doctorAvailability.getDoctor());
        return doctorAvailability;
    }
}
