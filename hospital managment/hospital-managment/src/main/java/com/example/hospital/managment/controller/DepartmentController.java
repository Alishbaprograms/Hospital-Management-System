package com.example.hospital.managment.controller;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.managment.entity.Department;
import com.example.hospital.managment.repository.DepartmentRepository;


import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepo;

    public DepartmentController(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentRepo.save(department);
    }
}