package com.example.hospital.managment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.managment.entity.Appointment;
import com.example.hospital.managment.service.AppointmentService;
import java.util.List;
import com.example.hospital.managment.repository.AppointmentRepository;
import java.time.LocalDateTime;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepo;

    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentRepository appointmentRepo) {
        this.appointmentService = appointmentService;
        this.appointmentRepo = appointmentRepo;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appointmentRepo.findAll();
    }

    @PostMapping("/book")
    public Appointment book(@RequestParam Long patientId,
                            @RequestParam Long doctorId,
                            @RequestParam String dateTime) {
        try {
            LocalDateTime dt = LocalDateTime.parse(dateTime); // e.g. "2025-11-25T10:30"
            return appointmentService.bookAppointment(patientId, doctorId, dt);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/{appointmentId}/complete")
    public Appointment markAsCompleted(@PathVariable Long appointmentId) {
        try {
            return appointmentService.markAsCompleted(appointmentId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}