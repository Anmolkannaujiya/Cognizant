package com.cognizant.ormlearn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Hands-on 1 & 5
// This class represents the 'country' table in our ormlearn database
// JPA needs this to know how to map the object to a table row

@Entity  // tells hibernate this is a DB entity
@Table(name = "country")  // maps to the country table
public class Country {

    // primary key - maps to co_code column in DB
    @Id
    @Column(name = "co_code")
    private String code;

    // maps to co_name column
    @Column(name = "co_name")
    private String name;

    // JPA needs a default constructor, so adding it
    public Country() {
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

    // just for printing/logging purposes
    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}