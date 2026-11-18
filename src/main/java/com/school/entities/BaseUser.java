package com.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.enumeration.UserType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;


    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column
    private String password;

    @Column
    private String username;

    public BaseUser() {
    }

    public BaseUser(UserType userType, Integer id) {
        this.userType = userType;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
