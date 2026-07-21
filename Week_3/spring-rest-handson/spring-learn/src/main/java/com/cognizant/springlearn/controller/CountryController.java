package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Hands-On 2 (file 2): REST Country Web Service – GET /country, GET /countries, GET /countries/{code}
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.info("CountryController initialized");
    }

    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START - getCountryIndia()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country india = context.getBean("india", Country.class);
        LOGGER.info("END - getCountryIndia() : {}", india);
        return india;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries()");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("END - getAllCountries() : count = {}", countries.size());
        return countries;
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry() : code = {}", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("END - getCountry() : {}", country);
        return country;
    }
}
