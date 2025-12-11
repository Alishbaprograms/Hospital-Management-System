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

    public Bill createBill(Long patientId, Double amount) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setAmount(amount);
        bill.setCreatedDate(LocalDateTime.now());
        return billRepo.save(bill);
    }

    public List<Bill> getBills(Long patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return billRepo.findAllByPatient(patient);
    }

    public Bill markBillAsPaid(Long billId) {
        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setStatus(Bill.BillStatus.PAID);
        return billRepo.save(bill);
    }

    public Bill markBillAsCancelled(Long billId) {
        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setStatus(Bill.BillStatus.CANCELLED);
        return billRepo.save(bill);
    }
}
