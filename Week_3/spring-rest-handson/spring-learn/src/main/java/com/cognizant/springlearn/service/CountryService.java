package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

// Hands-On 2 (file 2): Service for country lookup by code and fetching all countries
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry() : code = {}", code);

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

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

    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries()");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

        LOGGER.info("END - getAllCountries() : count = {}", countryList.size());
        return countryList;
    }
}
