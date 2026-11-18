package com.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.enumeration.Semester;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")  // Each subject is taught by one teacher
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Assignment> assignments = new ArrayList<>();


}