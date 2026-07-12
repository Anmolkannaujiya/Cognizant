package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    // Exercise 2 & Exercise 5
    // Dependency to be injected

    private BookRepository repository;

    // ======================================================
    // Exercise 7
    // Constructor Injection
    // ======================================================

    public BookService(BookRepository repository) {

        this.repository = repository;

        System.out.println("Constructor Injection Executed");

    }

    // ======================================================
    // Exercise 2 & Exercise 5
    // Setter Injection
    // ======================================================

    public void setRepository(BookRepository repository) {

        this.repository = repository;

        System.out.println("Setter Injection Executed");

    }

    // ======================================================
    // Exercise 1 & Exercise 5
    // Business Method
    // ======================================================

    public void displayService() {

        System.out.println("Book Service is working...");

        repository.displayRepository();

    }

}