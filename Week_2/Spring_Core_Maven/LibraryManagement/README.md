# Week 2 - Exercise 1
# Configuring a Basic Spring Application

## Objective

The objective of this exercise is to understand the fundamentals of the Spring Framework by creating a basic Spring Core application using XML-based configuration. The exercise demonstrates how Spring's Inversion of Control (IoC) container manages object creation and dependency injection.

---

## Scenario

A company is developing a web application for managing a library. Instead of manually creating objects using the `new` keyword, the application uses the Spring Framework to manage object creation and dependencies.

---

## Technologies Used

- Java 21
- Spring Framework 6.x
- Maven
- IntelliJ IDEA
- XML Configuration

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
    │           ├── MainApp.java
    │           ├── repository
    │           │      └── BookRepository.java
    │           └── service
    │                  └── BookService.java
    │
    └── resources
           └── applicationContext.xml
```

---

## Features

- Maven project setup
- Spring Core dependency integration
- XML-based bean configuration
- Bean creation using Spring IoC Container
- Setter-based Dependency Injection
- Loading Spring Application Context
- Retrieving beans from the Spring container

---

## Spring Concepts Covered

### Inversion of Control (IoC)

Spring takes control of creating and managing Java objects (beans) instead of the programmer creating them manually.

### Dependency Injection (DI)

Spring automatically injects required dependencies into a class. In this exercise, `BookRepository` is injected into `BookService` using Setter Injection.

### Bean

A bean is a Java object that is created and managed by the Spring IoC Container.

### Application Context

`applicationContext.xml` contains the bean definitions and dependency configuration used by the Spring container.

---

## Execution Flow

```
MainApp
   │
   ▼
Loads applicationContext.xml
   │
   ▼
Spring IoC Container Starts
   │
   ├── Creates BookRepository Bean
   │
   ├── Creates BookService Bean
   │
   ├── Injects BookRepository into BookService
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
Book Service is working...
Book Repository is working...
```

---

## Learning Outcomes

After completing this exercise, you will be able to:

- Create a Maven-based Spring project.
- Configure Spring using XML.
- Define and manage beans in the Spring IoC Container.
- Perform Setter-based Dependency Injection.
- Load the Spring Application Context.
- Retrieve and use Spring-managed beans.

---

