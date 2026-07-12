# Library Management - Spring Core (Week 2)

## Overview

This project is a combined implementation of the following Cognizant Deep Skilling Week 2 exercises:

- Exercise 1 – Configuring a Basic Spring Application
- Exercise 2 – Implementing Dependency Injection
- Exercise 4 – Creating and Configuring a Maven Project
- Exercise 5 – Configuring the Spring IoC Container
- Exercise 7 – Implementing Constructor and Setter Injection

The project demonstrates the fundamental concepts of the Spring Framework, including Spring IoC, Dependency Injection, XML-based bean configuration, and Maven project setup.

---

## Technologies Used

- Java 21
- Spring Framework 6.x
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
    │   └── com
    │       └── library
    │           ├── Main.java
    │           ├── repository
    │           │      └── BookRepository.java
    │           └── service
    │                  └── BookService.java
    │
    └── resources
            └── applicationContext.xml
```

---

## Exercises Implemented

### Exercise 1 – Configuring a Basic Spring Application

- Created a Maven project.
- Added Spring Core dependency.
- Configured `applicationContext.xml`.
- Defined Spring beans.
- Loaded the Spring IoC Container.
- Retrieved beans from the container.

---

### Exercise 2 – Implementing Dependency Injection

- Implemented Setter Injection.
- Wired `BookRepository` into `BookService`.
- Verified Dependency Injection using Spring IoC.

---

### Exercise 4 – Creating and Configuring a Maven Project

- Configured the Maven project.
- Added Spring Context dependency.
- Added Spring AOP dependency.
- Added Spring Web MVC dependency.
- Configured the Maven Compiler properties.

---

### Exercise 5 – Configuring the Spring IoC Container

- Configured Spring IoC Container using XML.
- Managed bean creation through `applicationContext.xml`.
- Demonstrated bean management and dependency configuration.

> **Note:** This exercise builds upon Exercises 1 and 2, so the implementation is largely the same.

---

### Exercise 7 – Constructor and Setter Injection

- Implemented Constructor Injection.
- Implemented Setter Injection.
- Configured both injection techniques using XML.
- Verified successful dependency injection.

---

## Spring Concepts Covered

- Spring IoC Container
- Bean Configuration
- XML Configuration
- Dependency Injection (DI)
- Setter Injection
- Constructor Injection
- Maven Dependency Management

---

## Execution Flow

```
Main
 │
 ▼
Load applicationContext.xml
 │
 ▼
Spring IoC Container
 │
 ├── Creates BookRepository Bean
 │
 ├── Creates BookService Bean
 │
 ├── Performs Constructor Injection
 │
 ├── Performs Setter Injection
 │
 ▼
Returns BookService Bean
 │
 ▼
displayService()
 │
 ▼
displayRepository()
```

---

## Expected Output

```
Constructor Injection Executed
Setter Injection Executed
Book Service is working...
Book Repository is working...
```

---

## Learning Outcomes

After completing this project, you will be able to:

- Create a Maven-based Spring project.
- Configure Spring using XML.
- Understand Spring IoC Container.
- Configure Spring Beans.
- Perform Setter-based Dependency Injection.
- Perform Constructor-based Dependency Injection.
- Manage dependencies using Maven.

---

## Author

**Anmol Kannaujiya**

Cognizant Deep Skilling Program – Week 2