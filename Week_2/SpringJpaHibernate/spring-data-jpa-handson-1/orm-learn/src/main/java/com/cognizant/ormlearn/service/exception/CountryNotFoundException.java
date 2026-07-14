package com.cognizant.ormlearn.service.exception;

/**
 * ============================================================
 * Hands-on 6: Find a country based on country code
 * ------------------------------------------------------------
 * CountryNotFoundException is a custom checked exception that
 * is thrown by CountryService.findCountryByCode() when no
 * country matching the given code exists in the database.
 * ============================================================
 */
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new CountryNotFoundException with a detail message.
     * description of which country code was not found
     */
    public CountryNotFoundException(String message) {
        super(message);
    }
}
