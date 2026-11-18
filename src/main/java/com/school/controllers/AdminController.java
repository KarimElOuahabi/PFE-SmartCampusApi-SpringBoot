package com.school.controllers;

import com.school.dao.AdminRepository;
import com.school.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable int id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return admin;
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getCin()));
        admin.setUsername(admin.getFirstName());
        return adminRepository.save(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@RequestBody Admin admin, @PathVariable Integer id) {
        Admin existing = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        existing.setFirstName(admin.getFirstName());
        existing.setLastName(admin.getLastName());
        existing.setCin(admin.getCin());
        existing.setPassword(passwordEncoder.encode(admin.getCin()));
        existing.setBirthDate(admin.getBirthDate());

        return adminRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable int id) {
        adminRepository.deleteById(id);
    }
}
