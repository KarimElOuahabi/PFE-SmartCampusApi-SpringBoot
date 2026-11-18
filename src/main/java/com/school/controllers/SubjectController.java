package com.school.controllers;

import com.school.dao.AssignmentRepository;
import com.school.dao.FieldRepository;
import com.school.dao.SubjectRepository;
import com.school.dao.TeacherRepository;
import com.school.dtos.SubjectDto;
import com.school.entities.Assignment;
import com.school.entities.Reclamation;
import com.school.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @GetMapping
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public Subject createSubject(@RequestBody SubjectDto subjectDto){
        Subject newSubject = new Subject();
        newSubject.setName(subjectDto.getName());
        newSubject.setDescription(subjectDto.getDescription());
        newSubject.setTeacher(teacherRepository.findById(subjectDto.getTeacherId()).get());
        newSubject.setSemester(subjectDto.getSemester());
        newSubject.setField(fieldRepository.findById(subjectDto.getFieldId()).get());
        return subjectRepository.save(newSubject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@RequestBody SubjectDto subjectDto, @PathVariable Integer id){
        Subject existing = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        existing.setName(subjectDto.getName());
        existing.setDescription(subjectDto.getDescription());
        existing.setTeacher(teacherRepository.findById(subjectDto.getTeacherId()).get());
        existing.setSemester(subjectDto.getSemester());
        existing.setField(fieldRepository.findById(subjectDto.getFieldId()).get());
        return subjectRepository.save(existing);
    }

    @GetMapping("/{id}")
    public Subject getById(@PathVariable Integer id) {
        return subjectRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
    }

    @GetMapping("teacher/{teacheId}")
    public List<Subject> getSubjectByTeacher(@PathVariable Integer teacheId) {
        return subjectRepository.findSubjectByTeacher_Id(teacheId);
    }

}
