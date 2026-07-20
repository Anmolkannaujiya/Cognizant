package com.cognizant.springlearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Hands-On 2 (spring-rest-handson file 2): REST - Get country exceptional scenario
 *
 * Custom exception thrown when the requested country code does not exist in the list.
 *
 * @ResponseStatus maps this exception to an HTTP 404 NOT_FOUND response.
 * When this exception is thrown from a controller method, Spring will automatically
 * respond with:
 * {
 *   "timestamp": "...",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Country not found",
 *   "path": "/country/az"
 * }
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException() {
        super("Country not found");
    }

    public CountryNotFoundException(String message) {
        super(message);
    }
}
