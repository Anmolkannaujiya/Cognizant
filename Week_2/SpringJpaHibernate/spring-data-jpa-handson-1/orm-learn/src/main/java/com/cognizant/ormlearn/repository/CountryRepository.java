package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

// Hands-on 1 & 5
// extending JpaRepository gives us findAll, findById, save, deleteById etc for free
// no need to write any SQL or implementation code here

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Hands-on 5 - query method
    // spring data jpa figures out the SQL from the method name itself
    // basically does: SELECT * FROM country WHERE co_name LIKE '%keyword%'
    List<Country> findByNameContaining(String keyword);
}