package com.example.hospital.managment.service;
import com.example.hospital.managment.entity.MedicalRecord;
import com.example.hospital.managment.entity.Patient;
import com.example.hospital.managment.entity.Doctor;
import com.example.hospital.managment.repository.MedicalRecordRepository;
import com.example.hospital.managment.repository.PatientRepository;
import com.example.hospital.managment.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDateTime;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepo,
                                PatientRepository patientRepo,
                                DoctorRepository doctorRepo) {
        this.medicalRecordRepo = medicalRecordRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    // Create a new medical record
    public MedicalRecord createMedicalRecord(Long patientId, Long doctorId, String diagnosis, String prescription) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        MedicalRecord record = new MedicalRecord();
        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setDiagnosis(diagnosis);
        record.setPrescription(prescription);
        record.setDate(LocalDateTime.now());

        return medicalRecordRepo.save(record);
    }

    public List<MedicalRecord> getMedicalRecords(Long patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return medicalRecordRepo.findAllByPatient(patient);
    }

    public MedicalRecord updateMedicalRecord(Long recordId, String diagnosis, String prescription) {
        MedicalRecord record = medicalRecordRepo.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Medical Record not found"));

        record.setDiagnosis(diagnosis);
        record.setPrescription(prescription);

        return medicalRecordRepo.save(record);
    }
}
