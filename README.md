# Secure Blog API

**Author:** Anar Aliyev  
**Student ID:** 39252

A secure RESTful API built with Spring Boot, featuring user authentication and blog post management.

## Features

- User registration and authentication
- Role-based access control
- Blog post creation and management
- RESTful API endpoints
- H2 database with Flyway migrations

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- H2 Database
- Flyway
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/secure-blog.git
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Authentication

- `POST /auth/register` - Register a new user
- `POST /auth/login` - Authenticate user and get token

### Posts

- `GET /api/posts` - Get all posts
- `POST /api/posts` - Create a new post
- `GET /api/posts/{id}` - Get post by ID
- `PUT /api/posts/{id}` - Update post
- `DELETE /api/posts/{id}` - Delete post

## Security

The API uses Spring Security for authentication and authorization. All endpoints except `/auth/**` require authentication.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
