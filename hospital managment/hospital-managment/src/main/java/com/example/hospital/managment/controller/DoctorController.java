package com.example.hospital.managment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.managment.entity.Doctor;
import com.example.hospital.managment.repository.DoctorRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepo;

    public DoctorController(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return doctorRepo.findAll();
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorRepo.deleteById(id);
    }
}