package com.example.hospital.managment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospital.managment.entity.Department;
import com.example.hospital.managment.repository.DepartmentRepository;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    public Department saveDepartment(Department d) {
        return repo.save(d);
    }
}
