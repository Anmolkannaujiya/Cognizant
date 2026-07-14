package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Hands-on 2 (2. spring-data-jpa-handson) - derived query methods
// Hands-on 3 (3. spring-data-jpa-handson) - @Query HQL and native SQL on stock table

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // --- Hands-on 2: Derived query methods ---

    // FB September 2019 - date range
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // GOOGL close > 1250
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal price);

    // top 3 highest volume overall
    List<Stock> findTop3ByOrderByVolumeDesc();

    // top 3 lowest NFLX close
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);


    // --- Hands-on 3 (3. spring-data-jpa-handson): @Query HQL ---
    // HQL uses entity field names: code, date, close, volume (not column names)
    // YEAR() and MONTH() are HQL functions supported by Hibernate 6
    // This achieves the same as findByCodeAndDateBetween but written explicitly as HQL

    @Query("SELECT s FROM Stock s WHERE s.code = :code AND YEAR(s.date) = :year AND MONTH(s.date) = :month")
    List<Stock> findByCodeYearMonthHql(@Param("code") String code,
                                       @Param("year") int year,
                                       @Param("month") int month);

    // HQL - get all stocks of a company sorted by date
    @Query("SELECT s FROM Stock s WHERE s.code = :code ORDER BY s.date ASC")
    List<Stock> findByCodeOrderedHql(@Param("code") String code);

    // HQL - stocks where volume is above a threshold
    @Query("SELECT s FROM Stock s WHERE s.volume > :minVolume ORDER BY s.volume DESC")
    List<Stock> findHighVolumeHql(@Param("minVolume") long minVolume);


    // --- Hands-on 3 (3. spring-data-jpa-handson): @Query native SQL ---
    // nativeQuery=true - raw MySQL SQL using actual column names (st_code, st_date etc.)
    // MONTH() and YEAR() here are MySQL functions (not HQL), so nativeQuery=true is needed

    @Query(value = "SELECT * FROM stock WHERE st_code = :code AND MONTH(st_date) = :month AND YEAR(st_date) = :year",
           nativeQuery = true)
    List<Stock> findByCodeYearMonthNative(@Param("code") String code,
                                          @Param("year") int year,
                                          @Param("month") int month);

    // native - stocks where close price is above a value, sorted by date
    @Query(value = "SELECT * FROM stock WHERE st_code = :code AND st_close > :minClose ORDER BY st_date ASC",
           nativeQuery = true)
    List<Stock> findByCodeAndCloseGreaterThanNative(@Param("code") String code,
                                                    @Param("minClose") BigDecimal minClose);
}
