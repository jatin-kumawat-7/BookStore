# Bookstore REST API

Overview:
This is a backend RESTful API for managing books and authors. It allows clients to perform CRUD operations on books and authors, with support for filtering, pagination, and sorting. The project uses Java, Spring Boot, and an in-memory H2 database.

Features:

Create, read, update, and delete books and authors.

Filter books by title or category.

Pagination and sorting support for large datasets.

H2 console for easy database inspection.

Swagger/OpenAPI documentation for API endpoints.

Technologies Used:

Java 22

Spring Boot 3

Spring Data JPA

H2 Database (in-memory)

Springdoc OpenAPI (Swagger)

Maven

Getting Started:

Clone the repository.

Open in IntelliJ IDEA or any Java IDE.

Run the project using:

mvn spring-boot:run


Access H2 console at: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:bookdb

Username: sa

Password: (leave blank)

Access API endpoints at: http://localhost:8080/api/books and http://localhost:8080/api/authors.

Sample Data:

{
  "id": 1,
  "title": "Java Basics",
  "category": "Programming",
  "price": 499.0,
  "author": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
