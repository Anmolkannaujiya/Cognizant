package com.cognizant.ormlearn.service.exception;

// Hands-on 6
// Custom exception for when we can't find a country by its code
// instead of returning null, we throw this so the caller knows what went wrong

public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String message) {
        super(message);
    }
}
