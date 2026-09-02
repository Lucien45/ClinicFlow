package com.example.backend.appointment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.appointment.dto.AppointmentCreateDto;
import com.example.backend.appointment.entity.Appointment;
import com.example.backend.appointment.entity.Status;
import com.example.backend.appointment.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    public void createAppointment(AppointmentCreateDto appointmentCreateDto) {
        Appointment appointment = toEntity(appointmentCreateDto);
        appointmentRepository.save(appointment);
    }

    public Optional<Appointment> update(Long id, Appointment appointment) {
        return appointmentRepository.findById(id)
            .map(existingAppointment -> {
                appointment.setId(id);
                return appointmentRepository.save(appointment);
            });
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void deleteAll() {
        appointmentRepository.deleteAll();
    }

    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> findByStatus(Status status) {
        return appointmentRepository.findByStatus(status);
    }

    private Appointment toEntity(AppointmentCreateDto appointmentCreateDto) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentCreateDto.getDate());
        appointment.setStart_time(appointmentCreateDto.getStart_time());
        appointment.setEnd_time(appointmentCreateDto.getEnd_time());
        appointment.setReason(appointmentCreateDto.getReason());
        appointment.setStatus(appointmentCreateDto.getStatus());
        appointment.setPatient(appointmentCreateDto.getPatientId());
        appointment.setDoctor(appointmentCreateDto.getDoctorId());
        return appointment;
    }
    
}
