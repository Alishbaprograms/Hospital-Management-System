package com.example.hospital.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospital.managment.entity.Appointment;
import com.example.hospital.managment.entity.Doctor;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    Optional<Appointment> findByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime dateTime);

}