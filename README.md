# TaskFlow - Task Management Backend

Spring Boot REST API for managing tasks. It is the backend foundation of TaskFlow, a
multi-user task and project management platform (see the roadmap below).

**Stack:** Java 17, Spring Boot 4, Spring Data JPA, MySQL, MapStruct, Lombok, springdoc OpenAPI.

## Run locally

1. Create a MySQL user for the app (do not use `root`).
2. Set the environment variables from `.env.example` (`DB_USERNAME`, `DB_PASSWORD`, optionally `DB_URL`).
3. Start the app:

```bash
./mvnw spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html  
Health check: http://localhost:8080/actuator/health

## Tests

```bash
./mvnw test
```

Tests use an in-memory H2 database (`test` profile); no MySQL is required.

## Current API

| Method | Path | Description |
|---|---|---|
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks?status=&priority=&page=&size=&sort=` | List tasks (filter + pagination) |
| GET | `/api/tasks/{id}` | Get one task |
| PUT | `/api/tasks/{id}` | Update a task (status/priority kept if omitted) |
| DELETE | `/api/tasks/{id}` | Delete a task (204) |
| GET | `/api/tasks/dashboard` | Task statistics |

## Roadmap

Users and JWT auth, projects and membership, assignment, comments, activity history,
notifications, analytics, Docker and CI/CD. See the development life cycle document.
