package com.school.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignmentSolutionDto {
    private Integer id;
    private Integer assignmentId;
    private Integer studentId;
    private String fileBase64Solution;
}
