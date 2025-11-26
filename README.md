# Bookstore REST API

## Objective
- Create a backend API to manage books and authors
- Support full CRUD operations, filtering, pagination, and sorting

## Key Features
- Manage books and authors with create, read, update, and delete operations
- Filter books by title or category
- Pagination and sorting support
- H2 console for database inspection
- Swagger/OpenAPI documentation for API exploration
- Ready-to-test endpoints with Postman

## Project Structure
- **Book class** – Represents a book entity with title, category, price, and linked author
- **Author class** – Represents an author entity with name and email
- **Repositories** – `BookRepository` and `AuthorRepository` handle database operations
- **Controllers** – `BookController` and `AuthorController` expose REST endpoints
- **Application class** – Entry point for running the Spring Boot application

## Sample JSON Response
```json
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
