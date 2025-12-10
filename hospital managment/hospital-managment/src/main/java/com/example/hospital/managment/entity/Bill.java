package com.example.hospital.managment.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @ManyToOne
    private Patient patient;

    private Double amount;
    
    @Enumerated(EnumType.STRING)
    private BillStatus status = BillStatus.PENDING;

    private LocalDateTime createdDate;

    public enum BillStatus {
        PENDING, PAID, CANCELLED
    }

    // Getters & Setters

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
