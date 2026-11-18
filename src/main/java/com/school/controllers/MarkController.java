package com.school.controllers;

import com.school.dao.MarkRepository;
import com.school.dao.StudentRepository;
import com.school.dao.SubjectRepository;
import com.school.dtos.*;
import com.school.entities.*;
import com.school.enumeration.AcademicLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    MarkRepository markRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("student/{studentId}")
    public List<MarkReadDto> getMarksByStudentId(@PathVariable Integer studentId) {
        List<Mark> marks = markRepository.findAllByStudent_id(studentId);
        return marks.stream().map(mark -> {
            MarkReadDto dto = new MarkReadDto();
            dto.setId(mark.getId());
            dto.setGrade((double) mark.getGrade()); // converting float to Double
            dto.setSubjectName(mark.getSubject().getName());
            dto.setSession(mark.getSession());
            dto.setAcademicYear(mark.getAcademicYear());
            dto.setAcademicLevel(mark.getAcademicLevel());
            dto.setSemester(mark.getSubject().getSemester());
            return dto;
        }).toList();
    }

    @GetMapping("subject/{subjectId}")
    public List<MarkTeacherReadDto> getMarkBySubjectId(@PathVariable Integer subjectId) {
        List<Mark> marks = markRepository.findAllBySubject_id(subjectId);
        return marks.stream().map(mark -> {
            MarkTeacherReadDto dto = new MarkTeacherReadDto();
            dto.setId(mark.getId());
            dto.setMark(mark.getGrade());
            dto.setMarkSession(mark.getSession());
            dto.setAcademicYear(mark.getAcademicYear());
            dto.setAcademicLevel(mark.getAcademicLevel());
            Subject subject = mark.getSubject();
            dto.setSubject(new SubjectDto(Math.toIntExact(subject.getId()), subject.getName(), subject.getDescription(), subject.getTeacher().getId(), subject.getField().getId(), subject.getSemester()));
            Student student = mark.getStudent();
            dto.setStudent(new StudentDto(student.getId(), student.getCIN(), student.getCNE(), student.getFirstName(), student.getLastName(), student.getUserType(),student.getBirthDate(), student.getInscriptionDate(), student.getField().getId(), student.getTypeBac()));
            return dto;
        }).toList();
    }

    @PostMapping
    public Mark createMark(@RequestBody MarkSaveDto markSaveDto) {
        Mark mark = new Mark();
        mark.setAcademicYear(markSaveDto.getAcademicYear());
        mark.setGrade(markSaveDto.getGrade());
        mark.setSession(markSaveDto.getSession());

        // Récupérer subject
        Subject subject = subjectRepository.findById(markSaveDto.getSubjectId()).orElseThrow(() -> new RuntimeException("Subject not found"));
        mark.setSubject(subject);

        // Récupérer student
        Student student = studentRepository.findById(Math.toIntExact(markSaveDto.getStudentId())).orElseThrow(() -> new RuntimeException("Student not found"));
        mark.setStudent(student);

        // Récupérer academicLevel via subject.getField().getAcademicLevel()
        AcademicLevel level = subject.getField().getAcademicLevel();
        mark.setAcademicLevel(level);

        return markRepository.save(mark);
    }


    @PutMapping("{markId}")
    public Mark updateMark(@RequestBody MarkSaveDto markSaveDto, @PathVariable Integer markId) {
        Mark mark = markRepository.findById(markId).orElseThrow(() -> new RuntimeException("Mark not found"));
        mark.setAcademicYear(markSaveDto.getAcademicYear());
        mark.setGrade(markSaveDto.getGrade());
        mark.setSession(markSaveDto.getSession());

        Subject subject = subjectRepository.findById(markSaveDto.getSubjectId()).orElseThrow(() -> new RuntimeException("Subject not found"));
        mark.setSubject(subject);

        Student student = studentRepository.findById(Math.toIntExact(markSaveDto.getStudentId())).orElseThrow(() -> new RuntimeException("Student not found"));
        mark.setStudent(student);

        AcademicLevel level = subject.getField().getAcademicLevel();
        mark.setAcademicLevel(level);

        return markRepository.save(mark);
    }


    @DeleteMapping("{markId}")
    public ResponseEntity<String> deleteMark(@PathVariable Integer markId) {
        if (!markRepository.existsById(markId)) {
            return ResponseEntity.notFound().build();
        }
        markRepository.deleteById(markId);
        return ResponseEntity.ok("Mark deleted successfully");
    }


}
