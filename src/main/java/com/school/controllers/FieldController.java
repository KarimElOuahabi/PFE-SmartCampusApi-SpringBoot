package com.school.controllers;

import com.school.dao.FieldRepository;
import com.school.dao.DepartmentRepository;
import com.school.dtos.FieldDto;
import com.school.entities.Field;
import com.school.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/fields")
public class FieldController {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // GET all fields
    @GetMapping
    public List<Field> getFields() {
        return fieldRepository.findAll();
    }

    // GET field by ID
    @GetMapping("/{id}")
    public Field getFieldById(@PathVariable Integer id) {
        return fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));
    }

    // CREATE new field
    @PostMapping
    public Field createField(@RequestBody FieldDto fieldDto) {
        Field newField = new Field();
        if (fieldDto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(fieldDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            newField.setDepartment(dept);
            newField.setName(fieldDto.getName());
            newField.setAcademicLevel(fieldDto.getAcademicLevel());
        }
        return fieldRepository.save(newField);
    }

    // UPDATE  TODO
    @PutMapping("/{id}")
    public Field updateField(@RequestBody FieldDto fieldDto, @PathVariable Integer id) {
        Field existing = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));

        existing.setName(fieldDto.getName());
        existing.setAcademicLevel(fieldDto.getAcademicLevel());

        if (fieldDto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(fieldDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(dept);
        }

        return fieldRepository.save(existing);
    }

    // DELETE field
    @DeleteMapping("/{id}")
    public void deleteField(@PathVariable Integer id) {
        fieldRepository.deleteById(id);
    }
}
