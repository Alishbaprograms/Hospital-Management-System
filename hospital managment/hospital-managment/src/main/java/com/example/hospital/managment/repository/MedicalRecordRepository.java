package com.example.hospital.managment.repository;
import com.example.hospital.managment.entity.MedicalRecord;
import com.example.hospital.managment.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findAllByPatient(Patient patient);
}

