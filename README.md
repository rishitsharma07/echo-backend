# Echo - Backend

This is the backend repository for the **Echo** blog platform. It serves a robust REST API using Spring Boot, handling user authentication, post management, and database interactions.

🔗 **Frontend Repository:** [Echo Frontend](https://github.com/rishitsharma07/echo-frontend)

## Tech Stack
- **Framework**: Spring Boot 3
- **Language**: Java 17
- **Database**: H2 Database (In-memory)
- **Data Access**: Spring Data JPA
- **Security**: Spring Security with JWT Authentication

## Features
- **Secure Authentication**: JWT-based login and registration endpoints.
- **RESTful API**: Endpoints for creating, reading, editing, and deleting posts and comments.
- **Data Relationships**: Complex entity mapping between Users, Posts, Comments, and Likes using JPA.

## Getting Started

To run the backend locally, make sure you have Java 17+ installed.

1. **Run the Spring Boot application:**
   - **Mac/Linux:**
     ```bash
     ./mvnw spring-boot:run
     ```
   - **Windows:**
     ```cmd
     .\mvnw.cmd spring-boot:run
     ```

2. The server will start on `http://localhost:8080`.

## Database Access

The project uses an in-memory **H2 Database** which resets on startup. You can view the live tables using the H2 Console:

- **Console URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:file:./data/blogdb`
- **Username**: `SA`
- **Password**: *(leave blank)*

*(Note: To experience the full platform, be sure to run the [Echo Frontend](https://github.com/rishitsharma07/echo-frontend) as well).*
