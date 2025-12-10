package com.example.hospital.managment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.managment.entity.Patient;
import com.example.hospital.managment.service.PatientService;
import com.example.hospital.managment.repository.PatientRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepo;

    public PatientController(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientRepo.findAll();
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return patientRepo.save(patient);
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient updated) {
        Patient p = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        p.setFirstName(updated.getFirstName());
        p.setLastName(updated.getLastName());
        p.setGender(updated.getGender());
        p.setDateOfBirth(updated.getDateOfBirth());
        p.setPhone(updated.getPhone());
        p.setEmail(updated.getEmail());
        p.setAddress(updated.getAddress());

        return patientRepo.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientRepo.deleteById(id);
    }
}