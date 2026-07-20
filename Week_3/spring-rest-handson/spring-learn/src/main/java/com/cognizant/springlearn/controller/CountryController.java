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

/**
 * Hands-On 2 (spring-rest-handson file 2):
 * - REST - Country Web Service       : GET /country
 * - REST - Get all countries         : GET /countries
 * - REST - Get country by code       : GET /countries/{code}
 * - REST - Get country exceptional   : throws CountryNotFoundException -> 404
 *
 * @RestController marks this class as a REST controller.
 * Spring's Dispatcher Servlet routes incoming HTTP requests to this controller
 * based on the @RequestMapping and @GetMapping annotations.
 *
 * Bean-to-JSON transformation:
 * When a Country object (or List<Country>) is returned, Spring uses
 * Jackson library (included via spring-boot-starter-web) to automatically
 * serialize the Java object into JSON format.
 * The Content-Type in the response will be 'application/json'.
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    /**
     * Autowired CountryService for business logic delegation.
     * MockMVC test checks the log from this constructor.
     */
    @Autowired
    private CountryService countryService;

    public CountryController() {
        // Log to verify CountryController is instantiated by Spring
        // MockMVC test: "Check if the log in the constructor of CountryController is called"
        LOGGER.info("CountryController initialized");
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): REST - Country Web Service
     *
     * Returns India country details loaded from country.xml Spring bean.
     *
     * Method Annotation: @RequestMapping (as specified in the hands-on)
     * URL: /country
     * Method Name: getCountryIndia()
     *
     * What happens in the controller method:
     * 1. Spring's Dispatcher Servlet receives GET /country request
     * 2. Routes to this method based on @RequestMapping("/country")
     * 3. ApplicationContext loads 'india' bean from country.xml
     * 4. Returns Country object -> Jackson converts it to JSON automatically
     * 5. HTTP Response Content-Type: application/json
     *
     * Sample Request: http://localhost:8083/country
     * Sample Response: { "code": "IN", "name": "India" }
     *
     * @return Country object (auto-converted to JSON by Jackson)
     */
    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START - getCountryIndia()");

        // Load India bean from Spring XML configuration (country.xml)
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country india = context.getBean("india", Country.class);

        LOGGER.info("END - getCountryIndia() : {}", india);
        return india;
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): REST - Get all countries
     *
     * Returns all countries from country.xml bean list.
     *
     * Method Annotation: @GetMapping("/countries") (as specified in the hands-on)
     * URL: /countries
     * Method Name: getAllCountries()
     *
     * Sample Request: http://localhost:8083/countries
     * Sample Response:
     * [
     *   { "code": "IN", "name": "India" },
     *   { "code": "US", "name": "United States" },
     *   { "code": "JP", "name": "Japan" },
     *   { "code": "DE", "name": "Germany" }
     * ]
     *
     * @return List of Country objects (auto-converted to JSON array by Jackson)
     */
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries()");

        List<Country> countries = countryService.getAllCountries();

        LOGGER.info("END - getAllCountries() : count = {}", countries.size());
        return countries;
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): REST - Get country based on country code
     * Hands-On 2 (spring-rest-handson file 2): REST - Get country exceptional scenario
     *
     * Returns a specific country by its code (case-insensitive).
     * Throws CountryNotFoundException (HTTP 404) if code not found.
     *
     * Method Annotation: @GetMapping("/countries/{code}") (as specified in the hands-on)
     * @PathVariable extracts the country code from the URL path.
     *
     * Sample Request: http://localhost:8083/country/in  OR  /countries/IN
     * Sample Response: { "code": "IN", "name": "India" }
     *
     * Exceptional Scenario:
     * Sample Request: http://localhost:8083/country/az
     * Sample Response: { "status": 404, "error": "Not Found", "message": "Country not found" }
     *
     * @param code country code from URL path (case-insensitive)
     * @return Country object for the matching code
     * @throws CountryNotFoundException if country code not found (HTTP 404)
     */
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry() : code = {}", code);

        // Delegate to CountryService which handles case-insensitive matching
        // and throws CountryNotFoundException if not found
        Country country = countryService.getCountry(code);

        LOGGER.info("END - getCountry() : {}", country);
        return country;
    }
}
