# LibraryManagementSystem
#  Library Management System

A Spring Boot RESTful API for managing books in a library.

##  Technologies Used

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- JUnit & Mockito (for testing)
- Swagger/OpenAPI

##  Features

- CRUD operations for books
- Data validation
- Basic Spring Security
- Swagger UI for API documentation
- In-memory H2 database
- Unit testing with Mockito and MockMvc

##  API Endpoints

| Method | Endpoint     | Description          |
|--------|--------------|----------------------|
| GET    | `/books`     | Get all books        |
| GET    | `/books/{id}`| Get book by ID       |
| POST   | `/books`     | Add a new book       |
| PUT    | `/books/{id}`| Update a book        |
| DELETE | `/books/{id}`| Delete a book        |

## Getting Started

```bash
# Clone repo
git clone https://github.com/your-username/library-management-system.git

# Go into the project
cd library-management-system

# Build and run
mvn spring-boot:run

