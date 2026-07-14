package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

// main entry point for the spring boot app
// @SpringBootApplication handles component scanning, auto config etc

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // keeping countryService static so we can use it in static test methods below
    private static CountryService countryService;

    public static void main(String[] args) {

        // run the app and hold onto the context so we can get beans from it
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        LOGGER.info("Inside Main");

        countryService = context.getBean(CountryService.class);

        // Hands-on 1
        testGetAllCountries();

        // Hands-on 6
        testFindCountryByCode();

        // Hands-on 7
        testAddCountry();

        // Hands-on 8
        testUpdateCountry();

        // Hands-on 9
        testDeleteCountry();

        // Hands-on 5 - query methods
        testFindCountriesByNameContaining();
    }

    // Hands-on 1 - just prints all countries, good for checking the DB connection works
    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    // Hands-on 6 - testing findById basically, also checking the exception works
    private static void testFindCountryByCode() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country: {}", country);

            // this one doesn't exist, should throw CountryNotFoundException
            countryService.findCountryByCode("XX");

        } catch (CountryNotFoundException e) {
            LOGGER.warn("caught exception as expected: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    // Hands-on 7 - adding a dummy country to test insert
    private static void testAddCountry() {
        LOGGER.info("Start");
        try {
            Country newCountry = new Country();
            newCountry.setCode("ZZ");
            newCountry.setName("Test Country");

            countryService.addCountry(newCountry);
            LOGGER.debug("added: {}", newCountry);

            // verify it's actually in the DB now
            Country fetched = countryService.findCountryByCode("ZZ");
            LOGGER.debug("fetched after add: {}", fetched);

        } catch (CountryNotFoundException e) {
            LOGGER.error("shouldn't happen here: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    // Hands-on 8 - update the country we just added
    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country");
            LOGGER.debug("updated ZZ successfully");

            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("after update: {}", updated);

        } catch (CountryNotFoundException e) {
            LOGGER.error("country not found during update: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    // Hands-on 9 - delete ZZ and confirm it's gone
    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        LOGGER.debug("deleted ZZ");

        try {
            // this should throw since we just deleted it
            countryService.findCountryByCode("ZZ");

        } catch (CountryNotFoundException e) {
            LOGGER.debug("confirmed deleted - exception caught: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    // Hands-on 5 - testing the query method that searches by partial name
    private static void testFindCountriesByNameContaining() {
        LOGGER.info("Start");

        List<Country> countries = countryService.findCountriesByNameContaining("land");
        LOGGER.debug("countries with 'land' in name: {}", countries);

        List<Country> islands = countryService.findCountriesByNameContaining("Islands");
        LOGGER.debug("island countries: {}", islands);

        LOGGER.info("End");
    }
}