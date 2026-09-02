package com.example.backend.doctor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.doctor.dto.DoctorAvailabilityCreateDto;
import com.example.backend.doctor.entity.DoctorAvailability;
import com.example.backend.doctor.service.DoctorAvailabilityService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api")
public class DoctorAvailabilityController {
    private final DoctorAvailabilityService doctorAvailabilityService;

    public DoctorAvailabilityController(DoctorAvailabilityService doctorAvailabilityService) {
        this.doctorAvailabilityService = doctorAvailabilityService;
    }

    @GetMapping("/availability")
    public ResponseEntity<List<DoctorAvailability>> getAllDoctorAvailabilitys() {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityService.findAll();
        return new ResponseEntity<>(doctorAvailabilities, HttpStatus.OK);
    }

    @GetMapping("/availability/{id}")
    public ResponseEntity<DoctorAvailability> getDoctorAvailabilityById(@PathVariable(value = "id") Long id) {
        return doctorAvailabilityService.findById(id)
            .map(doctorAvailability -> new ResponseEntity<>(doctorAvailability, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/availability")
    public ResponseEntity<Void> createDoctorAvailability(@RequestBody DoctorAvailabilityCreateDto availabilityCreateDto) {
        try {
            doctorAvailabilityService.createDoctorAvailability(availabilityCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PatchMapping("/availability/{id}")
    public ResponseEntity<DoctorAvailability> updateDoctorAvailability(@PathVariable("id") Long id, @RequestBody DoctorAvailability doctorAvailability) {
        try {
            return doctorAvailabilityService.update(id, doctorAvailability)
                .map(updatedAppointment -> new ResponseEntity<>(updatedAppointment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/availability/{id}")
    public ResponseEntity<HttpStatus> deleteAvailability(@PathVariable("id") Long id) {
        try {
            doctorAvailabilityService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/availability")
    public ResponseEntity<HttpStatus> deleteAllAvailabilitys() {
        try {
            doctorAvailabilityService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
