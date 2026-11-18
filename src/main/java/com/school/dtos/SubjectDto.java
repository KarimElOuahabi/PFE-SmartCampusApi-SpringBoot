package com.school.dtos;

import com.school.enumeration.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDto {
    private Integer id;
    private String name;
    private String description;
    private Integer teacherId;
    private Integer fieldId;
    private Semester semester;
}
