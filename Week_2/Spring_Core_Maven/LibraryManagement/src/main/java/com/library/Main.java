package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        // ==================================================
        // Exercise 1 & Exercise 5
        // Load Spring IoC Container
        // ==================================================

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // ==================================================
        // Exercise 1 & Exercise 5
        // Retrieve Bean
        // ==================================================

        BookService service =
                context.getBean("bookService", BookService.class);

        // ==================================================
        // Exercise 2, Exercise 5 & Exercise 7
        // Verify Dependency Injection
        // ==================================================

        service.displayService();

    }

}