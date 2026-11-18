package com.school.controllers;

import com.school.dao.AssignmentRepository;
import com.school.dao.SubjectRepository;
import com.school.entities.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Assignment> getAssignments() {
        return assignmentRepository.findAll();
    }

    @PostMapping
    public String uploadAssignment(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("subjectId") Integer subjectId,
            @RequestParam("dueDate") String dueDate,
            @RequestParam("file") MultipartFile file) {

        try {
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());

            Assignment assignment = new Assignment();
            assignment.setTitle(title);
            assignment.setDescription(description);
            assignment.setDueDate(dueDate);
            assignment.setSubject(subjectRepository.findById(subjectId).get());
            assignment.setFileBase64(base64);
            System.out.println("base64" + base64);

            assignmentRepository.save(assignment);

            System.out.println("Succes");
            return "Assignment uploaded successfully!";
        } catch (IOException e) {
            return "Error uploading file: " + e.getMessage();
        }
    }


    @PutMapping("/{id}")
    public String updateAssignmentWithFile(
            @PathVariable Integer id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("subjectId") Integer subjectId,
            @RequestParam("dueDate") String dueDate,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        try {
            Assignment existing = assignmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Assignment not found"));

            existing.setTitle(title);
            existing.setDescription(description);
            existing.setDueDate(dueDate);
            existing.setSubject(subjectRepository.findById(subjectId).get());

            if (file != null && !file.isEmpty()) {
                String base64 = Base64.getEncoder().encodeToString(file.getBytes());
                existing.setFileBase64(base64);
            }

            assignmentRepository.save(existing);
            return "Assignment updated successfully!";
        } catch (IOException e) {
            throw new RuntimeException("Error updating assignment", e);
        }
    }


    @GetMapping("/{id}")
    public Assignment getById(@PathVariable Integer id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable Integer id) {
        assignmentRepository.deleteById(id);
    }
}

