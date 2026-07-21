package com.cognizant.springlearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Hands-On 2 (file 2): Custom exception mapped to HTTP 404 for missing country codes
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
