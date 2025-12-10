package com.example.hospital.managment.controller;
import com.example.hospital.managment.entity.MedicalRecord;
import com.example.hospital.managment.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/medicalRecords")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    // Create a new medical record
    @PostMapping("/create")
    public MedicalRecord createMedicalRecord(@RequestParam Long patientId,
                                             @RequestParam Long doctorId,
                                             @RequestParam String diagnosis,
                                             @RequestParam String prescription) {
        return medicalRecordService.createMedicalRecord(patientId, doctorId, diagnosis, prescription);
    }

    // Get all medical records for a patient
    @GetMapping("/{patientId}")
    public List<MedicalRecord> getMedicalRecords(@PathVariable Long patientId) {
        return medicalRecordService.getMedicalRecords(patientId);
    }

    // Update a medical record
    @PutMapping("/update")
    public MedicalRecord updateMedicalRecord(@RequestParam Long recordId,
                                             @RequestParam String diagnosis,
                                             @RequestParam String prescription) {
        return medicalRecordService.updateMedicalRecord(recordId, diagnosis, prescription);
    }
}
