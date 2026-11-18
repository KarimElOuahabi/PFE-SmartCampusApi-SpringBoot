package com.school.controllers;

import com.school.dao.ScheduleRepository;
import com.school.dao.FieldRepository;
import com.school.entities.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @GetMapping
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @PostMapping
    public String uploadSchedule(
            @RequestParam("title") String title,
            @RequestParam("fieldId") Integer fieldId,
            @RequestParam("file") MultipartFile file) {

        try {
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            Schedule schedule = new Schedule();
            schedule.setTitle(title);
            schedule.setField(fieldRepository.findById(fieldId).get());
            schedule.setFileBase64(base64);

            scheduleRepository.save(schedule);

            return "Schedule uploaded successfully!";
        } catch (IOException e) {
            return "Error uploading file: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(
            @PathVariable Integer id,
            @RequestParam("title") String title,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("fieldId") Integer fieldId
    ) throws IOException {

        Schedule existing = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        existing.setTitle(title);
        existing.setField(fieldRepository.findById(fieldId).orElseThrow());

        if (file != null && !file.isEmpty()) {
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            existing.setFileBase64(base64);
        }

        return scheduleRepository.save(existing);
    }

    @GetMapping("/{id}")
    public Schedule getScheduleById(@PathVariable Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Integer id) {
        scheduleRepository.deleteById(id);
    }
}