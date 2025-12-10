package com.example.hospital.managment.repository;

import com.example.hospital.managment.entity.Bill;
import com.example.hospital.managment.entity.Patient;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByPatient(Patient patient);
}

