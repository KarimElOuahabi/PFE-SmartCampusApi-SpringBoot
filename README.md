# Smart Campus: Backend

REST API for **Smart Campus**, my Licence final-year project (PFE). It powers a campus management portal where administrators, teachers and students manage academic life in one place: schedules, assignments, marks, administrative documents and reclamations (complaints/requests).

The web client lives in [PFE-SmartCampusClient-React](https://github.com/KarimElOuahabi/PFE-SmartCampusClient-React).

## Features

- **Three user types** (`Admin`, `Teacher`, `Student`) with JWT authentication and BCrypt-hashed passwords.
- **Schedules:** timetables per field and semester.
- **Assignments:** teachers publish assignments, students submit solutions.
- **Marks:** teachers enter marks per subject and session, students read their own.
- **Reclamations:** students submit requests and administrators handle them.
- **Administrative documents:** students request official documents (`AdRequest`), administrators process them.
- **Academic structure management:** CRUD for departments, fields (programmes), subjects, students, teachers and admins.

## Tech stack

Java 21 · Spring Boot 3.4 · Spring Web · Spring Data JPA · Spring Security · JWT (jjwt) · PostgreSQL · Lombok · Gradle

## Project structure

```
src/main/java/com/school/
├── config/        security, JWT filter and utils, CORS, JPA auditing
├── controllers/   REST controllers, one per resource
├── dao/           Spring Data repositories
├── dtos/          request/response objects
├── entities/      JPA entities
├── enumeration/   user types, semesters, statuses, document types
└── services/      user details service
```

## API overview

| Resource | Base path |
| --- | --- |
| Authentication | `/authenticate` |
| Admins, teachers, students | `/admins`, `/teachers`, `/students` |
| Departments, fields, subjects | `/departments`, `/fields`, `/subjects` |
| Schedules | `/schedules` |
| Assignments and solutions | `/assignments`, `/assignments-solutions` |
| Marks | `/marks` |
| Reclamations | `/reclamations` |
| Administrative document requests | `/adrequests` |

Most resources expose the usual `GET`, `POST`, `PUT /{id}` and `DELETE /{id}`, plus a few lookups such as `student/{studentId}` or `subject/{subjectId}`.

## Running locally

Requirements: JDK 21 and a PostgreSQL database.

1. Create a database and set the connection in `src/main/resources/application.properties` (defaults to `postgres` on `localhost:5432`).
2. Set your own `jwt.secret` there. The value in the repo is a development placeholder, do not reuse it.
3. Start the API:

```bash
./gradlew bootRun    # http://localhost:8080
```

Tables are created automatically (`spring.jpa.hibernate.ddl-auto=update`). Run the tests with `./gradlew test`.

## Notes

- All routes are currently open at the HTTP layer (`permitAll`) and CORS allows any origin. Tighten both before any real deployment.
