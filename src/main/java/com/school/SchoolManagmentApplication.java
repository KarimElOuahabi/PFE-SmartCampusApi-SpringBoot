package com.school;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagmentApplication {
    public static void main(String[] args) {

        System.out.println("New JWT Secret: " +
                Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded()));
        SpringApplication.run(SchoolManagmentApplication.class, args);
    }

}
