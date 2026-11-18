package com.school.controllers;

import com.school.dao.AdminRepository;
import com.school.dao.DepartmentRepository;
import com.school.entities.Admin;
import com.school.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AdminRepository adminRepository;

    // GET all departments
    @GetMapping
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    // GET a department by ID
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // CREATE a new department
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        if (department.getAdmin() == null || department.getAdmin().getId() == null) {
            throw new RuntimeException("Admin must be provided");
        }

        Admin admin = adminRepository.findById(department.getAdmin().getId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        department.setAdmin(admin);

        if (department.getFields() != null) {
            department.getFields().forEach(field -> field.setDepartment(department));
        }

        return departmentRepository.save(department);
    }

    // UPDATE a department
    @PutMapping("/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable Integer id) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setName(department.getName());
        existing.setDescription(department.getDescription());

        if (department.getAdmin() != null && department.getAdmin().getId() != null) {
            Admin admin = adminRepository.findById(department.getAdmin().getId())
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            existing.setAdmin(admin);
        }

        if (department.getFields() != null) {
            existing.getFields().clear();
            department.getFields().forEach(field -> field.setDepartment(existing));
            existing.getFields().addAll(department.getFields());
        }

        return departmentRepository.save(existing);
    }

    // DELETE a department
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentRepository.deleteById(id);
    }
}
