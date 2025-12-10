package com.example.hospital.managment.service;
import java.util.List;
import com.example.hospital.managment.entity.Bill;
import com.example.hospital.managment.entity.Patient;
import com.example.hospital.managment.repository.BillRepository;
import com.example.hospital.managment.repository.PatientRepository;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepo;
    private final PatientRepository patientRepo;

    public BillService(BillRepository billRepo, PatientRepository patientRepo) {
        this.billRepo = billRepo;
        this.patientRepo = patientRepo;
    }

    // Create a new bill
    public Bill createBill(Long patientId, Double amount) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setAmount(amount);
        bill.setCreatedDate(LocalDateTime.now());
        return billRepo.save(bill);
    }

    // Get all bills for a patient
    public List<Bill> getBills(Long patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return billRepo.findAllByPatient(patient);
    }

    // Mark a bill as paid
    public Bill markBillAsPaid(Long billId) {
        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setStatus(Bill.BillStatus.PAID);
        return billRepo.save(bill);
    }

    // Mark a bill as cancelled
    public Bill markBillAsCancelled(Long billId) {
        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setStatus(Bill.BillStatus.CANCELLED);
        return billRepo.save(bill);
    }
}
