package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Hands-on 1 (2. spring-data-jpa-handson) - derived query methods (auto-generated SQL from method name)
// Hands-on 3 (3. spring-data-jpa-handson) - explicit @Query using HQL and native SQL
//
// Why two approaches?
//   - Derived query methods: quick, no SQL needed, good for simple conditions
//   - @Query HQL: more control, works with entity fields not column names
//   - @Query native: raw SQL, useful when HQL can't express the query

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // --- Hands-on 1: Derived query methods ---
    // spring reads the method name and generates the SQL automatically

    // search box - countries containing keyword
    List<Country> findByNameContaining(String keyword);

    // same but sorted A-Z
    List<Country> findByNameContainingOrderByNameAsc(String keyword);

    // alphabet index - countries starting with a letter
    List<Country> findByNameStartingWith(String letter);


    // --- Hands-on 3 (3. spring-data-jpa-handson): @Query HQL ---
    // HQL uses entity class name (Country) and field names (name), NOT table/column names
    // the %:keyword% wraps the param in SQL wildcards for LIKE matching

    @Query("SELECT c FROM Country c WHERE c.name LIKE %:keyword%")
    List<Country> searchByNameHql(@Param("keyword") String keyword);

    // HQL for starts-with - notice colon after :letter for the trailing wildcard
    @Query("SELECT c FROM Country c WHERE c.name LIKE :letter%")
    List<Country> findStartingWithHql(@Param("letter") String letter);

    // sorted HQL - ORDER BY in JPQL also uses field names not column names
    @Query("SELECT c FROM Country c WHERE c.name LIKE %:keyword% ORDER BY c.name ASC")
    List<Country> searchByNameHqlSorted(@Param("keyword") String keyword);


    // --- Hands-on 3 (3. spring-data-jpa-handson): @Query native SQL ---
    // nativeQuery=true means real SQL - uses actual table name (country) and column names (co_name)
    // useful when you need DB-specific functions or complex joins HQL can't handle

    @Query(value = "SELECT * FROM country WHERE co_name LIKE %:keyword%", nativeQuery = true)
    List<Country> searchByNameNative(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM country WHERE co_name LIKE :letter%", nativeQuery = true)
    List<Country> findStartingWithNative(@Param("letter") String letter);
}
