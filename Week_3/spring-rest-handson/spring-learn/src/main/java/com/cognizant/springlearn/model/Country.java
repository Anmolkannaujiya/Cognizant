package com.cognizant.springlearn.model;

/**
 * Hands-On 2 (spring-rest-handson file 2): REST - Country Web Service
 *
 * Model class representing a Country.
 * When returned from a @RestController method, Spring automatically
 * converts this bean to JSON using Jackson (bean to JSON transformation).
 *
 * Sample JSON output:
 * {
 *   "code": "IN",
 *   "name": "India"
 * }
 */
public class Country {

    /** ISO country code (e.g., "IN", "US", "JP", "DE") */
    private String code;

    /** Full country name */
    private String name;

    // Default constructor required for Spring XML bean instantiation
    public Country() {
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
