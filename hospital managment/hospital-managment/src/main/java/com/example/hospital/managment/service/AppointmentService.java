package com.example.hospital.managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospital.managment.entity.Appointment;
import com.example.hospital.managment.repository.AppointmentRepository;
import java.util.List;
import com.example.hospital.managment.entity.Patient;
import com.example.hospital.managment.entity.Doctor;
import com.example.hospital.managment.repository.PatientRepository;
import com.example.hospital.managment.repository.DoctorRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import jakarta.transaction.Transactional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepo,
                              PatientRepository patientRepo,
                              DoctorRepository doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @Transactional
    public Appointment bookAppointment(Long patientId,
                                       Long doctorId,
                                       LocalDateTime dateTime) {

        // Check if the patient exists
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Check if the doctor exists
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Check if the doctor is already booked at the given time
        Optional<Appointment> existingAppointment = appointmentRepo.findByDoctorAndAppointmentDateTime(doctor, dateTime);
        if (existingAppointment.isPresent() && existingAppointment.get().getStatus() != Appointment.Status.COMPLETED) {
            throw new RuntimeException("The doctor already has an appointment at this time");
        }

        // Create new appointment if no conflict
        Appointment appt = new Appointment();
        appt.setPatient(patient);
        appt.setDoctor(doctor);
        appt.setAppointmentDateTime(dateTime);

        return appointmentRepo.save(appt);
    }

    @Transactional
    public Appointment markAsCompleted(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Mark appointment as completed
        appointment.setStatus(Appointment.Status.COMPLETED);
        return appointmentRepo.save(appointment);
    }
}
