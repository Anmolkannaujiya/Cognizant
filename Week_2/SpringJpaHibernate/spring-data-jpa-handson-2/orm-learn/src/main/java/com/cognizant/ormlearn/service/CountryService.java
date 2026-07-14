package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Hands-on 1 (2. spring-data-jpa-handson) - country query method demos
// Hands-on 3 (3. spring-data-jpa-handson) - HQL and native query wrappers added below

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // --- Hands-on 1: derived query methods ---

    @Transactional
    public List<Country> searchByKeyword(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }

    @Transactional
    public List<Country> searchByKeywordSorted(String keyword) {
        return countryRepository.findByNameContainingOrderByNameAsc(keyword);
    }

    @Transactional
    public List<Country> searchByStartingLetter(String letter) {
        return countryRepository.findByNameStartingWith(letter);
    }

    // --- Hands-on 3 (3. spring-data-jpa-handson): HQL @Query wrappers ---

    @Transactional
    public List<Country> searchByKeywordHql(String keyword) {
        return countryRepository.searchByNameHql(keyword);
    }

    @Transactional
    public List<Country> searchByKeywordHqlSorted(String keyword) {
        return countryRepository.searchByNameHqlSorted(keyword);
    }

    @Transactional
    public List<Country> searchByStartingLetterHql(String letter) {
        return countryRepository.findStartingWithHql(letter);
    }

    // --- Hands-on 3 (3. spring-data-jpa-handson): native query wrappers ---

    @Transactional
    public List<Country> searchByKeywordNative(String keyword) {
        return countryRepository.searchByNameNative(keyword);
    }

    @Transactional
    public List<Country> searchByStartingLetterNative(String letter) {
        return countryRepository.findStartingWithNative(letter);
    }
}
