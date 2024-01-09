---

# Online Bookstore Application

The Online Bookstore Application is a simple Java 17 and Spring Boot based application that allows users to browse books, view book details, and perform basic CRUD operations. The application integrates with both REST and GraphQL APIs and uses two different databases for storing book information: a relational database (MySQL) and a NoSQL database (MongoDB).

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Database Setup](#database-setup)
    - [Running the Application](#running-the-application)
- [REST API](#rest-api)
- [GraphQL API](#graphql-api)
- [API Usage Examples](#api-usage-examples)

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

- Java 17
- MySQL
- MongoDB

## Getting Started

### Database Setup

#### Relational Database (MySQL)

1. Create a MySQL database named `bookstore_db`.
2. Update the `application.properties` file with your MySQL database connection details:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```

#### NoSQL Database (MongoDB)

1. Create a MongoDB database named `bookstore_mongo_db`.
2. Update the `application.properties` file with your MongoDB connection details:

   ```properties
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=bookstore_mongo_db
   ```

### Running the Application

1. Clone this repository:

   ```bash
   git clone https://github.com/your_username/online-bookstore.git
   cd online-bookstore
   ```

2. Build and run the application using Gradle:

   ```bash
   mvn spring-boot:run
   ```

3. The application will be accessible at [http://localhost:8080](http://localhost:8080).

## REST API

The application provides the following REST API endpoints for book operations:

- `GET /api/books`: Retrieve a list of all books.
- `GET /api/books/{id}`: Retrieve details of a specific book.
- `POST /api/books`: Create a new book.
- `PUT /api/books/{id}`: Update an existing book.
- `DELETE /api/books/{id}`: Delete a book.

## GraphQL API

The application also provides a GraphQL API for querying and mutating books. The GraphQL endpoint is [http://localhost:8080/graphql](http://localhost:8080/graphql).

### GraphQL Queries

- `query { getAllBooks }`: Retrieve a list of all books.
- `query { getBookById(id: ID) }`: Retrieve details of a specific book.

### GraphQL Mutations

- `mutation { createBook(book: BookInput) }`: Create a new book.
- `mutation { updateBook(id: ID, updatedBook: BookInput) }`: Update an existing book.
- `mutation { deleteBook(id: ID) }`: Delete a book.

## API Usage Examples

### Using REST API

#### Retrieve all books:

```bash
curl http://localhost:8080/api/books
```

#### Retrieve details of a specific book:

```bash
curl http://localhost:8080/api/books/{id}
```

#### Create a new book:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "title": "Sample Book",
  "author": "Sample Author",
  "price": 29.99,
  "publicationYear": 2022
}' http://localhost:8080/api/books
```

#### Update an existing book:

```bash
curl -X PUT -H "Content-Type: application/json" -d '{
  "title": "Updated Book Title",
  "author": "Updated Author",
  "price": 34.99,
  "publicationYear": 2023
}' http://localhost:8080/api/books/{id}
```

#### Delete a book:

```bash
curl -X DELETE http://localhost:8080/api/books/{id}
```

### Using GraphQL API

#### Retrieve all books:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "query": "query { getAllBooks }"
}' http://localhost:8080/graphql
```

#### Retrieve details of a specific book:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "query": "query { getBookById(id: 1) }"
}' http://localhost:8080/graphql
```

#### Create a new book:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "query": "mutation { createBook(book: { title: \"Sample Book\", author: \"Sample Author\", price: 29.99, publicationYear: 2022 }) { id title author } }"
}' http://localhost:8080/graphql
```

#### Update an existing book:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "query": "mutation { updateBook

(id: 1, updatedBook: { title: \"Updated Book Title\", author: \"Updated Author\", price: 34.99, publicationYear: 2023 }) { id title author } }"
}' http://localhost:8080/graphql
```

#### Delete a book:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "query": "mutation { deleteBook(id: 1) }"
}' http://localhost:8080/graphql
```

---