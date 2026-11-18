package com.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.enumeration.AcademicLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AcademicLevel academicLevel;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "field", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Subject> subjects;

    @OneToMany(mappedBy = "field")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();
}
