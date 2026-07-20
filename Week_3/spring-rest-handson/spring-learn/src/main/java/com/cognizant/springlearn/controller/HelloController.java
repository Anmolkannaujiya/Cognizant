package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hands-On 2 (spring-rest-handson file 2): Hello World RESTful Web Service
 *
 * REST Controller that handles GET /hello requests.
 *
 * @RestController = @Controller + @ResponseBody
 * Means this class handles HTTP requests and the return values are
 * automatically written to the HTTP response body.
 *
 * When accessed at http://localhost:8083/hello, it returns the string "Hello World!!"
 * The HTTP response Content-Type will be text/plain.
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    public HelloController() {
        // Log to verify HelloController is instantiated by Spring
        LOGGER.info("HelloController initialized");
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): Hello World RESTful Web Service
     *
     * Method: GET
     * URL: /hello
     * Sample Request: http://localhost:8083/hello
     * Sample Response: Hello World!!
     *
     * @GetMapping is a shortcut for @RequestMapping(method = RequestMethod.GET)
     * The Dispatcher Servlet receives the request and routes it to this method.
     *
     * @return hardcoded string "Hello World!!"
     */
    @GetMapping("/hello")
    public String sayHello() {
        // Hands-On 2: "Don't forget to include start and end log in the sayHello() method"
        LOGGER.info("START - sayHello()");

        String response = "Hello World!!";

        LOGGER.info("END - sayHello() : response = {}", response);
        return response;
    }
}
