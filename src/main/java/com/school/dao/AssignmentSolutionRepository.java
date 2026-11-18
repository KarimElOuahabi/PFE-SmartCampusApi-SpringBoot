package com.school.dao;

import com.school.entities.Assignment;
import com.school.entities.Student;
import com.school.entities.AssignmentSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentSolutionRepository extends JpaRepository<AssignmentSolution, Integer> {
    List<AssignmentSolution> findAssignmentSolutionByAssignmentAndStudent(Assignment assignment, Student student);

    List<AssignmentSolution> findByAssignment(Assignment assignment);

    List<AssignmentSolution> findByAssignment_Id(Integer assignmentId);

    AssignmentSolution findByAssignment_IdAndStudent_Id(Integer assignmentId, Integer studentId);

}
