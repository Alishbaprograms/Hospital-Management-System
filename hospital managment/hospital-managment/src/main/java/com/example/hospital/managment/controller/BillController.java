package com.example.hospital.managment.controller;
import com.example.hospital.managment.entity.Bill;
import com.example.hospital.managment.service.BillService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // Create a new bill
    @PostMapping("/create")
    public Bill createBill(@RequestParam Long patientId, @RequestParam Double amount) {
        return billService.createBill(patientId, amount);
    }

    // Get all bills for a patient
    @GetMapping("/{patientId}")
    public List<Bill> getBills(@PathVariable Long patientId) {
        return billService.getBills(patientId);
    }

    // Mark bill as paid
    @PostMapping("/pay")
    public Bill markBillAsPaid(@RequestParam Long billId) {
        return billService.markBillAsPaid(billId);
    }

    // Mark bill as cancelled
    @PostMapping("/cancel")
    public Bill markBillAsCancelled(@RequestParam Long billId) {
        return billService.markBillAsCancelled(billId);
    }
}
