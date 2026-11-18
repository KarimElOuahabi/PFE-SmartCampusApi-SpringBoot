package com.school.controllers;

import com.school.config.JwtUtil;
import com.school.dao.AdminRepository;
import com.school.dao.BaseUserRepository;
import com.school.dao.StudentRepository;
import com.school.dao.TeacherRepository;
import com.school.dtos.AuthRequest;
import com.school.entities.Admin;
import com.school.entities.BaseUser;
import com.school.entities.Student;
import com.school.entities.Teacher;
import com.school.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping("/authenticate")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest req) {
        System.out.println(req.getUsername() + " @@@@@@@@@@@@@@" + req.getPassword());
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        BaseUser user = baseUserRepository.findByUsername(req.getUsername()).orElseThrow();
        System.out.println(user.getId() + "@@@@@@@" + user.getUsername() + "33333" + user.getPassword());
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_type", user.getUserType());
        String firstName = "";
        String lastName = "";
        Integer fieldId = 0;
        if(user.getUserType() == UserType.Admin){
            Admin admin = adminRepository.findById(user.getId()).get();
            firstName = admin.getFirstName();
            lastName = admin.getLastName();
        }
        else if(user.getUserType() == UserType.Teacher){
            Teacher teacher = teacherRepository.findById(user.getId()).get();
            firstName = teacher.getFirstName();
            lastName = teacher.getLastName();
            fieldId = teacher.getField().getId();
        }
        else if(user.getUserType() == UserType.Student){
            Student student = studentRepository.findById(user.getId()).get();
            firstName = student.getFirstName();
            lastName = student.getLastName();
            fieldId = student.getField().getId();
        }

        claims.put("firstName", firstName);
        claims.put("lastName", lastName);
        claims.put("userId", user.getId());
        claims.put("fieldId" , fieldId);
        String token = jwtUtil.generateToken(claims, req.getUsername());

        return ResponseEntity.ok(Map.of("jwt", token));
    }
}



