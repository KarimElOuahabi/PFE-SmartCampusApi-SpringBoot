package com.school.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.school.enumeration.TypeBac;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student extends BaseUser {
    private String CIN;
    private String CNE;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate inscriptionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id")
    private Field field;

    @Enumerated(EnumType.STRING)
    private TypeBac typeBac;
}
