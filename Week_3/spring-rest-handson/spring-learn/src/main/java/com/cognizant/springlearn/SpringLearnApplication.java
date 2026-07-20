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

/**
 * ============================================================
 * Hands-On 1: Create a Spring Web Project using Maven
 * ============================================================
 *
 * Main entry point of the Spring Boot application.
 *
 * @SpringBootApplication is a convenience annotation that combines:
 *   - @Configuration      : Marks this class as a configuration class
 *   - @EnableAutoConfiguration : Enables Spring Boot's auto-configuration
 *   - @ComponentScan      : Scans for Spring components in the package
 *
 * Purpose of @SpringBootApplication:
 *   It bootstraps the entire Spring application context automatically,
 *   sets up the embedded Tomcat server (via spring-boot-starter-web),
 *   configures default beans, and starts the web application.
 *
 * Project Structure (as walked through by SME):
 *   src/main/java     - Application code (controllers, services, models)
 *   src/main/resources - Application configuration (application.properties, XML files)
 *   src/test/java     - Test code (JUnit + MockMVC tests)
 */
@SpringBootApplication
public class SpringLearnApplication {

    // Hands-On 1: Include logs to verify main() method is called (Step 9)
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    /**
     * Hands-On 1: Main method - entry point of the Spring Boot application.
     * Includes START and END logs to verify execution.
     *
     * Hands-On 2 (spring-rest-handson file 1): Also calls displayDate()
     * to demonstrate Spring XML bean loading.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Hands-On 1: Start log to verify main() is called (Step 9 & 10)
        LOGGER.info("START - main()");

        // Start the Spring Boot application (starts embedded Tomcat on port 8083)
        SpringApplication.run(SpringLearnApplication.class, args);

        // Hands-On 1 & Hands-On 2 (spring-rest-handson file 1):
        // Call displayDate() to demonstrate Spring Core XML configuration
        displayDate();

        // Hands-On 1: End log to verify main() completed successfully
        LOGGER.info("END - main()");
    }

    /**
     * ============================================================
     * Hands-On 2 (spring-rest-handson file 1):
     * Spring Core – Load SimpleDateFormat from Spring Configuration XML
     * ============================================================
     *
     * Demonstrates loading a Spring bean from an XML configuration file.
     *
     * Steps implemented:
     * 1. Create ApplicationContext using ClassPathXmlApplicationContext("date-format.xml")
     * 2. Retrieve dateFormat bean using getBean()
     * 3. Parse date string '31/12/2018' and display result
     */
    private static void displayDate() {
        LOGGER.info("START - displayDate()");

        try {
            // Step 1: Create ApplicationContext from Spring XML configuration file
            // ClassPathXmlApplicationContext looks for date-format.xml in src/main/resources
            ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");

            // Step 2: Get the dateFormat bean defined in date-format.xml
            // Bean id="dateFormat", class="java.text.SimpleDateFormat" with pattern "dd/MM/yyyy"
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

            // Step 3: Parse '31/12/2018' using the loaded format and display result
            Date date = format.parse("31/12/2018");
            System.out.println("Parsed Date: " + date);

            LOGGER.info("END - displayDate() : Parsed date = {}", date);

        } catch (ParseException e) {
            LOGGER.error("Error parsing date in displayDate()", e);
        }
    }
}
