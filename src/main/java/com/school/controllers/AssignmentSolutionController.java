package com.school.controllers;

import com.school.dao.AssignmentRepository;
import com.school.dao.AssignmentSolutionRepository;
import com.school.dao.StudentRepository;
import com.school.dtos.AssignmentSolutionDto;
import com.school.entities.Assignment;
import com.school.entities.AssignmentSolution;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/assignments-solutions")
public class AssignmentSolutionController {

    @Autowired
    AssignmentSolutionRepository assignmentSolutionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;


    @PostMapping
    public String addAssignmentSolution(@RequestParam("assignmentId") Integer assignmentId,
                                        @RequestParam("studentId") Integer studentId,
                                        @RequestParam("file") MultipartFile file){
        try{
            AssignmentSolution assignmentSolution = new AssignmentSolution();
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            System.out.println(base64);
            assignmentSolution.setFileBase64Solution(base64);
            assignmentSolution.setStudent(studentRepository.findById(studentId).get());
            assignmentSolution.setAssignment(assignmentRepository.findById(assignmentId).get());
            assignmentSolutionRepository.save(assignmentSolution);
            return "Assignment Solution uploaded successfully!";
        }
        catch(Exception e){
            return "Error uploading file: " + e.getMessage();
        }
    }

    @GetMapping("/{assignmentId}/student/{studentId}")
    @Transactional
    public AssignmentSolution getById(@PathVariable("assignmentId") Integer assignmentId,
                                      @PathVariable("studentId") Integer studentId) {
        return assignmentSolutionRepository.findByAssignment_IdAndStudent_Id(assignmentId, studentId);
    }

    @GetMapping("/{assignmentId}")
    @Transactional
    public List<AssignmentSolution> getAssignmentSolutionByAssignmentId(@PathVariable Integer assignmentId) {
        return assignmentSolutionRepository.findByAssignment_Id(assignmentId);
    }

}
