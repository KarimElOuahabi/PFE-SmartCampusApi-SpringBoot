package com.school.controllers;

import com.school.dao.StudentRepository;
import com.school.dao.FieldRepository;
import com.school.dtos.StudentDto;
import com.school.entities.Field;
import com.school.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FieldRepository fieldRepository;

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("field/{fieldId}")
    public List<Student> getStudentsByFieldId(@PathVariable int fieldId) {
        return studentRepository.findByField_Id(fieldId);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student;
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentDto dto) {
        Student student = fillStudentFromDto(new Student(),dto);
        studentRepository.save(student);
        return student;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody StudentDto dto, @PathVariable Integer id) {
        Student student = fillStudentFromDto(studentRepository.findById(id).get() ,dto);
        studentRepository.save(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }


    private Student fillStudentFromDto(Student student , StudentDto dto) {
        student.setCIN(dto.getCin());
        student.setCNE(dto.getCne());
        student.setFirstName(dto.getFname());
        student.setLastName(dto.getLname());
        student.setUserType(dto.getUserType());
        student.setUsername(dto.getFname());
        student.setPassword(passwordEncoder.encode(dto.getCin()));
        student.setBirthDate(dto.getBirthDate());
        student.setInscriptionDate(dto.getInscriptionDate());


        if (dto.getFieldId() != null) {
            Field field = fieldRepository.findById(dto.getFieldId())
                    .orElseThrow(() -> new RuntimeException("Field not found"));
            student.setField(field);
        } else {
            student.setField(null);
        }
        student.setTypeBac(dto.getTypeBac());
        return student;
    }

    private StudentDto convertToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setCin(student.getCIN());
        dto.setCne(student.getCNE());
        dto.setFname(student.getFirstName());
        dto.setLname(student.getLastName());
        dto.setBirthDate(student.getBirthDate());
        dto.setInscriptionDate(student.getInscriptionDate());

        if (student.getField() != null) {
            dto.setFieldId(student.getField().getId());
        }

        dto.setTypeBac(student.getTypeBac());

        return dto;
    }

}
