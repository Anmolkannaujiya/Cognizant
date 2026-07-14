package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import jakarta.transaction.Transactional;

// service layer for country - all business logic goes here
// @Transactional on each method means spring handles the hibernate session
// and transaction automatically (no manual beginTransaction/commit needed)

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Hands-on 1 - get all countries from the DB
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Hands-on 6 - find a single country by its code
    // findById returns Optional so we have to check if it's actually there
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);

        if (!result.isPresent()) {
            // not found, throw our custom exception
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }

        return result.get();
    }

    // Hands-on 7 - add a new country
    // save() does an INSERT if the entity doesn't exist yet
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Hands-on 8 - update country name by code
    // fetch -> change name -> save() triggers an UPDATE in DB
    @Transactional
    public void updateCountry(String code, String newName) throws CountryNotFoundException {
        Country country = findCountryByCode(code);
        country.setName(newName);
        countryRepository.save(country);
    }

    // Hands-on 9 - delete a country by code
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Hands-on 5 - search countries by partial name
    // uses the query method we defined in the repository
    @Transactional
    public List<Country> findCountriesByNameContaining(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }
}