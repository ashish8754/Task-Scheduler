

# Task Scheduler & Tracker App ⏰

This is a backend project built using Spring Boot that allows users to create, manage, and track tasks with optional scheduled execution times. Tasks automatically change state based on their schedule and user actions (e.g., PENDING → DUE → COMPLETED or MISSED).

## Features

- Create tasks with title, description, and optional schedule
- Task status auto-updates: PENDING → DUE → MISSED
- Mark tasks as completed
- Task recurrence support (planned)
- Task filtering (by status, time) (planned)
- Spring Scheduled job for periodic status updates
- Input validation using Jakarta Validation

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2/PostgreSQL (configurable)
- Spring Validation
- RESTful API design

## Progress Summary

✅ Defined Task entity and TaskStatus enum  
✅ Created TaskRequest and TaskResponse DTOs  
✅ Built TaskRepository with query methods  
✅ Implemented TaskService with task creation logic  
✅ Exposed POST /api/tasks in TaskController  
⏳ Next: Add scheduler to auto-update status (PENDING → DUE → MISSED)  
⏳ Next: Implement GET endpoints for listing/filtering tasks  
⏳ Optional: Add recurring task logic and filtering by time/status  
⏳ Add test cases for all services and controllers

---

## HLD / LLD Notes 🧠

### Low-Level Design (LLD)

- **Entity Modeling**: Started with identifying the core object `Task` by listing its real-world properties: title, description, scheduled time, status, and created time.
- **Status Enum**: Introduced `TaskStatus` enum to model valid state transitions (`PENDING`, `DUE`, `COMPLETED`, `MISSED`).
- **DTO Separation**: Used `TaskRequest` and `TaskResponse` to separate API models from internal entity logic.
- **Service Layer**: Business logic such as defaulting status and computing `createdAt` is handled in `TaskService`, keeping controllers clean and maintainable.
- **Validation**: Used `@NotBlank` for required fields to ensure clean request data.

### High-Level Design (HLD)

- **Architecture**: Follows classic 3-layered architecture:
  - Controller Layer (REST APIs)
  - Service Layer (Business logic)
  - Repository Layer (DB access via Spring Data JPA)
- **Status Transitions**: Auto-managed via Spring’s `@Scheduled` method, running periodic checks on `scheduledTime`.
- **Scalability Tip**: For production systems, replace in-process scheduler with distributed job queues like Quartz, or message brokers like RabbitMQ.
- **Extensibility**: Designed with forward-compatible fields like `recurring`, and clean separation of concerns to allow adding task filters, notifications, etc.