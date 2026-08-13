# Echo - Backend

This is the backend repository for **Echo**, a full-stack blog platform. It provides a REST API built with Spring Boot for user authentication, post management, comments, likes, and database interactions.

🔗 **Frontend Repository:** [Echo Frontend](https://github.com/rishitsharma07/echo-frontend)

🌐 **Live Backend:** https://echo-backend-hv2v.onrender.com

## Tech Stack

* **Framework:** Spring Boot 3.5.0
* **Language:** Java 17
* **Database:** PostgreSQL
* **Data Access:** Spring Data JPA / Hibernate
* **Security:** Spring Security + JWT Authentication
* **Build Tool:** Maven
* **Containerization:** Docker
* **Deployment:** Render

## Features

* **JWT Authentication:** Secure user registration and login using JSON Web Tokens.
* **User Management:** User registration and authentication.
* **Post Management:** Create, read, update, and delete posts.
* **Comments:** Add and manage comments on posts.
* **Likes:** Like and unlike posts.
* **Authorization:** Users can only modify or delete their own posts.
* **RESTful API:** Clean REST endpoints for frontend communication.
* **Database Persistence:** PostgreSQL database with JPA/Hibernate.
* **CORS:** Configured to allow communication with the deployed Vercel frontend.
* **Dockerized Deployment:** Packaged and deployed as a Docker container on Render.

## API Endpoints

### Authentication

| Method | Endpoint                | Description           | Authentication |
| ------ | ----------------------- | --------------------- | -------------- |
| POST   | `/api/v1/auth/register` | Register a new user   | No             |
| POST   | `/api/v1/auth/login`    | Login and receive JWT | No             |

### Posts

| Method | Endpoint                  | Description        | Authentication |
| ------ | ------------------------- | ------------------ | -------------- |
| GET    | `/api/v1/posts`           | Get all posts      | No             |
| GET    | `/api/v1/posts/{id}`      | Get a post by ID   | No             |
| POST   | `/api/v1/posts`           | Create a post      | Yes            |
| PUT    | `/api/v1/posts/{id}`      | Update your post   | Yes            |
| DELETE | `/api/v1/posts/{id}`      | Delete your post   | Yes            |
| POST   | `/api/v1/posts/{id}/like` | Like/unlike a post | Yes            |

> Additional endpoints are available for comments and other platform functionality.

## Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 17 or higher
* Maven (optional, since the project includes Maven Wrapper)
* PostgreSQL

### Clone the Repository

```bash
git clone https://github.com/rishitsharma07/echo-backend.git
cd echo-backend
```

## Environment Variables

The application uses environment variables for database credentials and JWT configuration.

Create the following environment variables before running the application:

```text
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/your_database
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=your_secure_jwt_secret
```

The application also supports the `PORT` environment variable:

```text
PORT=8080
```

The server defaults to port `8080` when `PORT` is not specified.

**Never commit database passwords or JWT secrets to GitHub.**

## Running Locally

### 1. Start PostgreSQL

Create a PostgreSQL database and make sure the PostgreSQL server is running.

Configure the required environment variables:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
```

### 2. Run the Spring Boot Application

**Mac/Linux:**

```bash
./mvnw spring-boot:run
```

**Windows:**

```cmd
.\mvnw.cmd spring-boot:run
```

The backend will start at:

```text
http://localhost:8080
```

## Docker

The project includes a `Dockerfile` for containerized deployment.

Build the Docker image:

```bash
docker build -t echo-backend .
```

Run the container:

```bash
docker run -p 8080:8080 echo-backend
```

Make sure the required PostgreSQL and JWT environment variables are supplied when running the container.

## Deployment

The backend is deployed on **Render** using Docker.

### Production Architecture

```text
React + Vite Frontend
        │
        │ REST API
        ▼
Spring Boot Backend
        │
        │ JPA / Hibernate
        ▼
PostgreSQL Database
```

The production backend is available at:

https://echo-backend-hv2v.onrender.com

The frontend is deployed separately using Vercel.

## Security

Echo uses Spring Security and JWT authentication.

* Public endpoints are available for registration, login, and reading posts.
* Protected endpoints require a valid JWT.
* JWT tokens are sent using the `Authorization` header:

```text
Authorization: Bearer <token>
```

* Users can only update or delete posts they own.
* Database credentials and JWT secrets are provided through environment variables.

## Project Structure

```text
echo-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/blogapi/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Frontend

To experience the complete Echo platform, run or visit the frontend:

🔗 **Frontend Repository:** https://github.com/rishitsharma07/echo-frontend

🌐 **Live Frontend:** https://echo-frontend-gilt-gamma.vercel.app
