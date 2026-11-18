package com.school.dtos;

import com.school.enumeration.TypeBac;
import com.school.enumeration.UserType;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private Integer id;
    private String cin;
    private String cne;
    private String fname;
    private String lname;
    private UserType userType;
    private LocalDate birthDate;
    private LocalDate inscriptionDate;
    private Integer fieldId;
    private TypeBac typeBac;
}
