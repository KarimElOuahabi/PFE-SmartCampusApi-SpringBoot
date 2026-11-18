package com.school.controllers;

import com.school.dao.TeacherRepository;
import com.school.dao.FieldRepository;
import com.school.dtos.TeacherDto;
import com.school.entities.Field;
import com.school.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacher;
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody TeacherDto dto) {
        Teacher teacher = fillTeacherFromDto(new Teacher(),dto);
        teacherRepository.save(teacher);
        return teacher;
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@RequestBody TeacherDto dto, @PathVariable Integer id) {
        Teacher student = fillTeacherFromDto(teacherRepository.findById(id).get() ,dto);
        teacherRepository.save(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherRepository.deleteById(id);
    }


    private Teacher fillTeacherFromDto(Teacher teacher , TeacherDto dto) {
        teacher.setCIN(dto.getCin());
        teacher.setFirstName(dto.getFname());
        teacher.setUserType(dto.getUserType());
        teacher.setLastName(dto.getLname());
        teacher.setBirthDate(dto.getBirthDate());
        teacher.setInscriptionDate(dto.getInscriptionDate());
        teacher.setUsername(dto.getFname());
        teacher.setPassword(passwordEncoder.encode(dto.getCin()));

        if (dto.getFieldId() != null) {
            Field field = fieldRepository.findById(dto.getFieldId())
                    .orElseThrow(() -> new RuntimeException("Field not found"));
            teacher.setField(field);
        } else {
            teacher.setField(null);
        }
        return teacher;
    }

    private TeacherDto convertToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setCin(teacher.getCIN());
        dto.setFname(teacher.getFirstName());
        dto.setLname(teacher.getLastName());
        dto.setBirthDate(teacher.getBirthDate());
        dto.setInscriptionDate(teacher.getInscriptionDate());

        if (teacher.getField() != null) {
            dto.setFieldId(teacher.getField().getId());
        }

        return dto;
    }

}
