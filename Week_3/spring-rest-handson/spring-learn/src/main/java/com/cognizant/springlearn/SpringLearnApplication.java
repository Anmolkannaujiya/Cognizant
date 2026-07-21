package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Hands-On 1: Create a Spring Web Project using Maven | Hands-On 2 (file 1): Spring Core – Load SimpleDateFormat bean
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START - main()");
        SpringApplication.run(SpringLearnApplication.class, args);
        displayDate();
        LOGGER.info("END - main()");
    }

    private static void displayDate() {
        LOGGER.info("START - displayDate()");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date date = format.parse("31/12/2018");
            System.out.println("Parsed Date: " + date);
            LOGGER.info("END - displayDate() : Parsed date = {}", date);
        } catch (ParseException e) {
            LOGGER.error("Error parsing date in displayDate()", e);
        }
    }
}
