package com.school.dtos;

import com.school.enumeration.AcademicLevel;
import com.school.enumeration.MarkSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MarkSaveDto {
    private Integer id;
    private String academicYear;
    private AcademicLevel academicLevel;
    private float grade;
    private MarkSession session;
    private Integer subjectId;
    private Integer studentId;

}
