package com.example.hospital.managment.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private String medicineName;
    private String dosageInstructions;
    private String frequency; // e.g., once a day, twice a day, etc.

    @Temporal(TemporalType.TIMESTAMP)
    private Date prescriptionDate;

    // Getters and Setters
}

