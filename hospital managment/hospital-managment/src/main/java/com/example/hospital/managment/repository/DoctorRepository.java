package com.example.hospital.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospital.managment.entity.Doctor;
import java.util.Optional;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findBySpecialization(String specialization);
}
