# Smart Campus: Backend

REST API for **Smart Campus**, my Licence final-year project (PFE). Administrators, teachers and students manage schedules, assignments, marks, administrative documents and reclamations (requests and complaints) in one place.

## Features

- **User types:** `Admin`, `Teacher` and `Student`, with JWT login.
- **Schedules:** timetables per field and semester.
- **Assignments:** teachers publish them and students submit solutions.
- **Marks:** entered per subject and session.
- **Reclamations:** submitted by students and handled by administrators.
- **Administrative documents:** students and teachers request official documents.
- **Academic structure:** management of departments, fields, subjects, students, teachers and admins.

## Tech stack

Java 21 · Spring Boot · Spring Security · JWT · Spring Data JPA · PostgreSQL · Gradle

## Related project

[PFE-SmartCampusClient-React](https://github.com/KarimElOuahabi/PFE-SmartCampusClient-React): the web client for this API.
