package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    // stock repository is used directly as per hands-on 2 instructions
    private static StockRepository stockRepository;

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside Main");

        countryService    = context.getBean(CountryService.class);
        stockRepository   = context.getBean(StockRepository.class);
        employeeService   = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService      = context.getBean(SkillService.class);

        // ---------------------------------------------------------------
        // 2. spring-data-jpa-handson - Hands-on 1: Country Query Methods
        // ---------------------------------------------------------------
        testSearchCountriesByKeyword();
        testSearchCountriesSorted();
        testCountriesStartingWithLetter();

        // ---------------------------------------------------------------
        // 2. spring-data-jpa-handson - Hands-on 2: Stock Query Methods
        // ---------------------------------------------------------------
        testFacebookSeptember2019();
        testGoogleCloseGreaterThan1250();
        testTop3HighestVolume();
        testTop3LowestNetflix();

        // ---------------------------------------------------------------
        // 2. spring-data-jpa-handson - Hands-on 4: ManyToOne (Employee -> Department)
        // ---------------------------------------------------------------
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();

        // ---------------------------------------------------------------
        // 2. spring-data-jpa-handson - Hands-on 5: OneToMany (Department -> Employees)
        // ---------------------------------------------------------------
        testGetDepartment();

        // ---------------------------------------------------------------
        // 2. spring-data-jpa-handson - Hands-on 6: ManyToMany (Employee <-> Skill)
        // ---------------------------------------------------------------
        testAddSkillToEmployee();

        // ---------------------------------------------------------------
        // 3. spring-data-jpa-handson - HQL (@Query with JPQL) demos
        // implemented in the SAME project because:
        //   - same entities (Country, Stock, Employee) are reused
        //   - only @Query annotations were added to existing repositories
        //   - no new tables or entities were needed
        //   - creating a 3rd project just for @Query would be redundant
        // ---------------------------------------------------------------
        testCountryHqlSearch();
        testCountryHqlSorted();
        testCountryHqlStartingWith();
        testStockHqlByYearMonth();
        testStockHqlHighVolume();
        testEmployeeHqlByDepartment();
        testEmployeeHqlPermanent();
        testEmployeeHqlSalaryRange();

        // ---------------------------------------------------------------
        // 3. spring-data-jpa-handson - Native Query demos
        // ---------------------------------------------------------------
        testCountryNativeSearch();
        testStockNativeByYearMonth();
        testStockNativeCloseGreaterThan();
        testEmployeeNativeByDepartment();
        testEmployeeNativeSalaryFilter();
    }

    // ================================================================
    // 2. spring-data-jpa-handson - Hands-on 1
    // ================================================================

    private static void testSearchCountriesByKeyword() {
        LOGGER.info("Start - Hands-on 1 [derived query: findByNameContaining]");
        List<Country> countries = countryService.searchByKeyword("ou");
        LOGGER.debug("Countries containing 'ou': {}", countries);
        LOGGER.info("End");
    }

    private static void testSearchCountriesSorted() {
        LOGGER.info("Start - Hands-on 1 [derived query: findByNameContainingOrderByNameAsc]");
        List<Country> countries = countryService.searchByKeywordSorted("ou");
        LOGGER.debug("Countries with 'ou' sorted A-Z: {}", countries);
        LOGGER.info("End");
    }

    private static void testCountriesStartingWithLetter() {
        LOGGER.info("Start - Hands-on 1 [derived query: findByNameStartingWith]");
        List<Country> countries = countryService.searchByStartingLetter("Z");
        LOGGER.debug("Countries starting with Z: {}", countries);
        LOGGER.info("End");
    }

    // ================================================================
    // 2. spring-data-jpa-handson - Hands-on 2
    // stock repository is used directly (not via service) as per instructions
    // ================================================================

    private static void testFacebookSeptember2019() {
        LOGGER.info("Start - Hands-on 2 [stock: date range query]");
        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end   = LocalDate.of(2019, 9, 30);
        List<Stock> stocks = stockRepository.findByCodeAndDateBetween("FB", start, end);
        LOGGER.debug("FB September 2019 ({} records):", stocks.size());
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testGoogleCloseGreaterThan1250() {
        LOGGER.info("Start - Hands-on 2 [stock: greater than query]");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
        LOGGER.debug("GOOGL close > 1250 ({} records):", stocks.size());
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testTop3HighestVolume() {
        LOGGER.info("Start - Hands-on 2 [stock: top 3 volume]");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        LOGGER.debug("Top 3 highest volume:");
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testTop3LowestNetflix() {
        LOGGER.info("Start - Hands-on 2 [stock: top 3 lowest NFLX]");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        LOGGER.debug("NFLX 3 lowest close:");
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    // ================================================================
    // 2. spring-data-jpa-handson - Hands-on 4 (ManyToOne)
    // ================================================================

    private static void testGetEmployee() {
        LOGGER.info("Start - Hands-on 4 [ManyToOne: get employee with department]");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        // hibernate auto-joins department because @ManyToOne fetch is EAGER by default
        LOGGER.debug("Department: {}", employee.getDepartment());
        // Hands-on 6 - skills loaded because @ManyToMany is set to EAGER
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start - Hands-on 4 [ManyToOne: add employee]");
        Employee emp = new Employee();
        emp.setName("Test Employee");
        emp.setSalary(60000.00);
        emp.setPermanent(false);
        emp.setDateOfBirth(new Date());
        Department dept = departmentService.get(1);
        emp.setDepartment(dept);
        employeeService.save(emp);
        LOGGER.debug("Saved employee (id is now set by hibernate): {}", emp);
        LOGGER.info("End");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start - Hands-on 4 [ManyToOne: update employee department]");
        Employee emp = employeeService.get(1);
        LOGGER.debug("Before: department = {}", emp.getDepartment());
        emp.setDepartment(departmentService.get(2));
        employeeService.save(emp);
        LOGGER.debug("After: employee = {}", emp);
        LOGGER.info("End");
    }

    // ================================================================
    // 2. spring-data-jpa-handson - Hands-on 5 (OneToMany)
    // ================================================================

    private static void testGetDepartment() {
        LOGGER.info("Start - Hands-on 5 [OneToMany: get department with employees]");
        // EAGER fetch means employee list loads alongside department in the same query
        // (if you change to LAZY in Department.java you'll get LazyInitializationException here)
        Department dept = departmentService.get(1);
        LOGGER.debug("Department: {}", dept);
        LOGGER.debug("Employees in department: {}", dept.getEmployeeList());
        LOGGER.info("End");
    }

    // ================================================================
    // 2. spring-data-jpa-handson - Hands-on 6 (ManyToMany)
    // ================================================================

    private static void testAddSkillToEmployee() {
        LOGGER.info("Start - Hands-on 6 [ManyToMany: add skill to employee]");
        Employee emp = employeeService.get(1);
        LOGGER.debug("Skills before: {}", emp.getSkillList());
        Skill skill = skillService.get(2); // Python
        emp.getSkillList().add(skill);
        employeeService.save(emp);
        LOGGER.debug("Skills after adding Python: {}", emp.getSkillList());
        LOGGER.info("End");
    }

    // ================================================================
    // 3. spring-data-jpa-handson - HQL (@Query JPQL) demos
    // Note: implemented in this same project (spring-data-jpa-handson-2)
    //   because all needed entities and tables already exist here.
    //   Only @Query annotations were added to the repositories.
    // ================================================================

    private static void testCountryHqlSearch() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query country search]");
        // HQL uses entity field names (c.name), not column names (co_name)
        List<Country> countries = countryService.searchByKeywordHql("ou");
        LOGGER.debug("HQL search 'ou' ({} results): {}", countries.size(), countries);
        LOGGER.info("End");
    }

    private static void testCountryHqlSorted() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query sorted country search]");
        List<Country> countries = countryService.searchByKeywordHqlSorted("ou");
        LOGGER.debug("HQL sorted search 'ou': {}", countries);
        LOGGER.info("End");
    }

    private static void testCountryHqlStartingWith() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query starts with]");
        List<Country> countries = countryService.searchByStartingLetterHql("Z");
        LOGGER.debug("HQL countries starting with Z: {}", countries);
        LOGGER.info("End");
    }

    private static void testStockHqlByYearMonth() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query stock by year and month]");
        // YEAR() and MONTH() are HQL functions in Hibernate 6 - no need for nativeQuery here
        List<Stock> stocks = stockRepository.findByCodeYearMonthHql("FB", 2019, 9);
        LOGGER.debug("HQL: FB September 2019 ({} records):", stocks.size());
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testStockHqlHighVolume() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query high volume stocks]");
        List<Stock> stocks = stockRepository.findHighVolumeHql(20000000L);
        LOGGER.debug("HQL: stocks with volume > 20M ({} records):", stocks.size());
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testEmployeeHqlByDepartment() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query employees by department name]");
        // HQL navigates e.department.name - hibernate handles the join automatically
        List<Employee> employees = employeeService.findByDepartmentNameHql("Engineering");
        LOGGER.debug("HQL: Engineering employees: {}", employees);
        LOGGER.info("End");
    }

    private static void testEmployeeHqlPermanent() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query permanent employees]");
        List<Employee> employees = employeeService.findPermanentEmployees();
        LOGGER.debug("HQL: permanent employees: {}", employees);
        LOGGER.info("End");
    }

    private static void testEmployeeHqlSalaryRange() {
        LOGGER.info("Start - Hands-on 3 [HQL: @Query salary range]");
        List<Employee> employees = employeeService.findBySalaryRange(65000, 90000);
        LOGGER.debug("HQL: employees salary 65k-90k: {}", employees);
        LOGGER.info("End");
    }

    // ================================================================
    // 3. spring-data-jpa-handson - Native SQL (@Query nativeQuery=true) demos
    // ================================================================

    private static void testCountryNativeSearch() {
        LOGGER.info("Start - Hands-on 3 [Native: @Query country search]");
        // native SQL uses actual column name co_name, not field name name
        List<Country> countries = countryService.searchByKeywordNative("ou");
        LOGGER.debug("Native SQL search 'ou' ({} results): {}", countries.size(), countries);
        LOGGER.info("End");
    }

    private static void testStockNativeByYearMonth() {
        LOGGER.info("Start - Hands-on 3 [Native: @Query stock by year and month]");
        // native SQL uses MySQL MONTH() and YEAR() functions + actual column names
        List<Stock> stocks = stockRepository.findByCodeYearMonthNative("FB", 2019, 9);
        LOGGER.debug("Native: FB September 2019 ({} records):", stocks.size());
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testStockNativeCloseGreaterThan() {
        LOGGER.info("Start - Hands-on 3 [Native: @Query stock close > value]");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThanNative("GOOGL", new BigDecimal("1250"));
        LOGGER.debug("Native: GOOGL close > 1250 ({} records):", stocks.size());
        stocks.forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testEmployeeNativeByDepartment() {
        LOGGER.info("Start - Hands-on 3 [Native: @Query employees by department]");
        // native SQL needs explicit JOIN - no JPA relationship navigation here
        List<Employee> employees = employeeService.findByDepartmentNameNative("Engineering");
        LOGGER.debug("Native: Engineering employees: {}", employees);
        LOGGER.info("End");
    }

    private static void testEmployeeNativeSalaryFilter() {
        LOGGER.info("Start - Hands-on 3 [Native: @Query salary filter]");
        List<Employee> employees = employeeService.findBySalaryGreaterThan(70000);
        LOGGER.debug("Native: employees salary > 70000: {}", employees);
        LOGGER.info("End");
    }
}
