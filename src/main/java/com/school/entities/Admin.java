package com.school.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.school.enumeration.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class Admin extends BaseUser {
    private String cin;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public Admin(UserType userType, Integer id) {
        super(userType, id);
    }

    public Admin() {
        super();
    }
}
