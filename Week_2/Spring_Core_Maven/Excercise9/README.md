# Exercise 9 – Creating a Spring Boot Application

## Overview

This project is an implementation of **Exercise 9** from the Cognizant Deep Skilling Program – Week 2.

The objective of this exercise is to build a basic **Spring Boot Library Management System** that demonstrates the use of Spring Boot, Spring Data JPA, H2 Database, and REST APIs for performing CRUD operations.

Unlike the previous exercises, which used the Spring Core Framework with XML configuration, this project uses **Spring Boot's auto-configuration** and annotation-based approach.

---

## Technologies Used

- Java 21
- Spring Boot 4.x
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- IntelliJ IDEA

---

## Project Structure

```
LibraryManagement
│
├── pom.xml
│
└── src
    ├── main
    │
    ├── java
    │   └── com.library
    │       ├── LibraryManagementApplication.java
    │       │
    │       ├── controller
    │       │      BookController.java
    │       │
    │       ├── entity
    │       │      Book.java
    │       │
    │       └── repository
    │              BookRepository.java
    │
    └── resources
           application.properties
```

---

## Features Implemented

- Spring Boot Project Creation
- REST API Development
- Spring Data JPA Repository
- H2 In-Memory Database
- Entity Mapping using JPA
- CRUD Operations for Books
- Automatic Dependency Injection
- Embedded Tomcat Server

---

## Dependencies

- Spring Web
- Spring Data JPA
- H2 Database

---

## REST Endpoints

### Add a Book

**POST**

```
/books
```

Example Request Body

```json
{
    "title": "Spring Boot",
    "author": "John Doe"
}
```

---

### Get All Books

**GET**

```
/books
```

---

## H2 Database Console

Spring Boot automatically enables the H2 Console.

Open the browser and navigate to:

```
http://localhost:8080/h2-console
```

Use the following credentials:

**JDBC URL**

```
jdbc:h2:mem:librarydb
```

**Username**

```
sa
```

**Password**

```
(leave blank)
```

---

## Application Configuration

The project uses `application.properties` to configure:

- H2 Database
- JPA
- Hibernate
- SQL Logging

Example configuration:

```properties
spring.datasource.url=jdbc:h2:mem:librarydb
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.h2.console.enabled=true
```

---

## Execution Flow

```
Client
   │
   ▼
HTTP Request
   │
   ▼
BookController
   │
   ▼
BookRepository
   │
   ▼
Spring Data JPA
   │
   ▼
H2 Database
```

---

## Learning Outcomes

After completing this exercise, you will be able to:

- Create a Spring Boot project using Spring Initializr.
- Configure a Spring Boot application.
- Create JPA Entity classes.
- Create Repository interfaces using Spring Data JPA.
- Build REST APIs using Spring Web.
- Connect and use an H2 in-memory database.
- Perform basic CRUD operations.
- Understand Spring Boot auto-configuration.

---

## Author

**Anmol Kannaujiya**

Cognizant Deep Skilling Program

Week 2 – Exercise 9: Creating a Spring Boot Application