package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Hands-On 2 (spring-rest-handson file 2):
 * - REST - Get country based on country code
 * - REST - Get country exceptional scenario
 *
 * Service class that handles business logic for country operations.
 * Uses Spring XML configuration (country.xml) to load country data.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /**
     * Hands-On 2 (spring-rest-handson file 2): REST - Get country based on country code
     *
     * Returns a Country matching the provided country code.
     * The match is case-insensitive (e.g., "in", "IN", "In" all return India).
     *
     * Implementation steps:
     * 1. Get the country list from country.xml via ApplicationContext
     * 2. Iterate through the list
     * 3. Case-insensitive match on country code
     * 4. Return matching country or throw CountryNotFoundException
     *
     * @param code the country code to look up (case-insensitive)
     * @return Country object matching the code
     * @throws CountryNotFoundException if no country with the given code exists
     */
    @SuppressWarnings("unchecked")
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry() : code = {}", code);

        // Load country list from Spring XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

        // Using Lambda expression for case-insensitive country code matching
        // (as mentioned in the hands-on: "Lambda expression can also be used")
        Country result = countryList.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Country not found for code: {}", code);
                    return new CountryNotFoundException("Country not found for code: " + code);
                });

        LOGGER.info("END - getCountry() : result = {}", result);
        return result;
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): REST - Get all countries
     *
     * Returns all countries loaded from country.xml
     *
     * @return List of all Country objects
     */
    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries()");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

        LOGGER.info("END - getAllCountries() : count = {}", countryList.size());
        return countryList;
    }
}
