package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ============================================================
 * Hands-On 2 (spring-rest-handson file 2):
 * MockMVC - Test get country service
 * MockMVC - Test get country service for exceptional scenario
 * ============================================================
 *
 * End-to-end testing of RESTful Web Services using MockMVC.
 *
 * @SpringBootTest loads the full application context for integration testing.
 * @AutoConfigureMockMvc configures MockMvc automatically so we can perform
 *   HTTP requests without starting a real HTTP server.
 *
 * Test execution:
 * - In Eclipse: Right-click SpringLearnApplicationTests.java > Run As > JUnit Test
 * - In command line: mvn clean test
 */
@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    // ============================================================
    // Hands-On 2 (spring-rest-handson file 2): MockMVC - Test get country service
    // Step: Include CountryController instance variable and autowire it
    // ============================================================
    @Autowired
    private CountryController countryController;

    // ============================================================
    // Hands-On 2 (spring-rest-handson file 2): MockMVC - Test get country service
    // Step: Autowire MockMvc for performing HTTP requests in tests
    // ============================================================
    @Autowired
    private MockMvc mvc;

    /**
     * Hands-On 2 (spring-rest-handson file 2): MockMVC - Test get country service
     *
     * Test 1: Verify the CountryController is loaded by Spring context.
     * assertNotNull checks that Spring successfully created and injected the controller.
     * Also verifies that the log in CountryController constructor is called.
     */
    @Test
    public void contextLoads() {
        // Verify CountryController bean is loaded in Spring application context
        assertNotNull(countryController);
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): MockMVC - Test get country service
     *
     * Test 2: Invoke GET /country and verify the response.
     *
     * Assertions:
     * - HTTP status is 200 OK (status().isOk())
     * - JSON response contains a "code" field (jsonPath("$.code").exists())
     * - "code" field value is "IN"  (jsonPath("$.code").value("IN"))
     * - JSON response contains a "name" field (jsonPath("$.name").exists())
     * - "name" field value is "India" (jsonPath("$.name").value("India"))
     *
     * mvc.perform(get("/country")) - simulates an HTTP GET request to /country
     * actions.andExpect(...)       - asserts conditions on the response
     * jsonPath("$.code")           - uses JSONPath to access the "code" field
     *                                $ refers to the root JSON object
     */
    @Test
    public void testGetCountry() throws Exception {
        // Perform GET /country request using MockMvc
        ResultActions actions = mvc.perform(get("/country"));

        // Verify HTTP Status is 200 OK
        actions.andExpect(status().isOk());

        // Verify "code" field exists in the JSON response
        actions.andExpect(jsonPath("$.code").exists());

        // Verify "code" field value is "IN"
        actions.andExpect(jsonPath("$.code").value("IN"));

        // Verify "name" field exists in the JSON response
        actions.andExpect(jsonPath("$.name").exists());

        // Verify "name" field value is "India"
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): MockMVC - Test get country service
     *
     * Test 3: Test GET /countries returns an array of countries.
     *
     * Assertions:
     * - HTTP status is 200 OK
     * - Response is a JSON array with 4 elements
     * - First element has code "IN"
     */
    @Test
    public void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));

        actions.andExpect(status().isOk());

        // JSONPath for array: $[0] refers to first element of the array
        actions.andExpect(jsonPath("$[0].code").value("IN"));
        actions.andExpect(jsonPath("$[1].code").value("US"));
        actions.andExpect(jsonPath("$[2].code").value("JP"));
        actions.andExpect(jsonPath("$[3].code").value("DE"));
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2): MockMVC - Test get country service
     *
     * Test 4: Test GET /countries/{code} with valid code returns correct country.
     * Tests case-insensitivity: passing lowercase "in" should return India.
     */
    @Test
    public void testGetCountryByCode() throws Exception {
        // Test with lowercase code - verifies case-insensitive matching
        ResultActions actions = mvc.perform(get("/countries/in"));

        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Hands-On 2 (spring-rest-handson file 2):
     * MockMVC - Test get country service for exceptional scenario
     *
     * Test 5: Test GET /countries/{code} with invalid code returns 404.
     *
     * When an invalid country code is provided (e.g., "az"),
     * CountryService throws CountryNotFoundException which is annotated with
     * @ResponseStatus(NOT_FOUND, reason = "Country not found")
     * resulting in HTTP 404 response.
     *
     * Assertions:
     * - HTTP status is 404 NOT FOUND (status().isNotFound())
     * - Response reason contains "Country not found"
     */
    @Test
    public void testGetCountryException() throws Exception {
        // Perform GET /countries/az - "az" is not a valid country code
        ResultActions actions = mvc.perform(get("/countries/az"));

        // Validate HTTP 404 NOT FOUND status
        actions.andExpect(status().isNotFound());

        // Validate the reason message matches the @ResponseStatus reason
        actions.andExpect(status().reason("Country not found"));
    }
}
