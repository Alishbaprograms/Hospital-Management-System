package com.example.hospital.managment.controller;

import com.example.hospital.managment.entity.MedicalRecord;
import com.example.hospital.managment.repository.MedicalRecordRepository;
import com.example.hospital.managment.service.PrescriptionPdfService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final MedicalRecordRepository recordRepo;
    private final PrescriptionPdfService pdfService;

    public PrescriptionController(MedicalRecordRepository recordRepo,
                                  PrescriptionPdfService pdfService) {
        this.recordRepo = recordRepo;
        this.pdfService = pdfService;
    }

    @GetMapping("/{recordId}/pdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long recordId) {

        MedicalRecord record = recordRepo.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        byte[] pdfBytes = pdfService.generatePrescriptionPDF(record);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "prescription_" + recordId + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
