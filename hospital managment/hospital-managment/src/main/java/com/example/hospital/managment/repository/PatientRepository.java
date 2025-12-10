package com.example.hospital.managment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.hospital.managment.entity.Patient;
// PatientRepository.java
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Custom queries can be added here
}


