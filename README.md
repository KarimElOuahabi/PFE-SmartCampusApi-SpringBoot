# Smart Campus: Backend

REST API for **Smart Campus**, my Licence final-year project (PFE). Administrators, teachers and students manage schedules, assignments, marks, administrative documents and reclamations (requests and complaints) in one place.

The web client is [PFE-SmartCampusClient-React](https://github.com/KarimElOuahabi/PFE-SmartCampusClient-React).

## Features

- Three user types (`Admin`, `Teacher`, `Student`) with JWT login.
- Schedules per field and semester.
- Assignments, with student submissions.
- Marks per subject and session.
- Reclamations submitted by students and handled by administrators.
- Administrative document requests.
- Management of departments, fields, subjects, students, teachers and admins.

## Tech stack

Java 21 · Spring Boot · Spring Security · JWT · Spring Data JPA · PostgreSQL · Gradle

## Run it

You need JDK 21 and a PostgreSQL database.

1. Copy `.env.example` to `.env` and set `JWT_SECRET` to a long random Base64 string, for example `openssl rand -base64 -A 64`.
2. Adjust `DB_URL`, `DB_USERNAME` and `DB_PASSWORD` if your database is not the local default.
3. Start the API:

```bash
./gradlew bootRun
```

It runs on `http://localhost:8080` and creates the tables automatically.
