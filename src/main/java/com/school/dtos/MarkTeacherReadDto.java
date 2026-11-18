package com.school.dtos;

import com.school.enumeration.AcademicLevel;
import com.school.enumeration.MarkSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkTeacherReadDto {
    private Long id;
    private StudentDto student;
    private float mark;
    private MarkSession markSession;
    private SubjectDto subject;
    private AcademicLevel academicLevel;
    private String academicYear;
}
