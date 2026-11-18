package com.school.dtos;

import com.school.enumeration.AcademicLevel;
import com.school.enumeration.MarkSession;
import com.school.enumeration.Semester;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkReadDto {
    private Long id;
    private Double grade;
    private String subjectName;
    private MarkSession session;
    private String academicYear;
    private AcademicLevel academicLevel;
    private Semester semester;
}
