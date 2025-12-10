package com.example.hospital.managment.entity;
import jakarta.persistence.*;
@Entity
public class LaboratoryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private String testName;
    private String testResult; // e.g., positive, negative, normal, abnormal


    // Getters and Setters
}
