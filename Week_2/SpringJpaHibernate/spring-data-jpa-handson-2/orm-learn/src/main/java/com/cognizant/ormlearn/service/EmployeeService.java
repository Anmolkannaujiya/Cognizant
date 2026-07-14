package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Hands-on 4, 5, 6 (2. spring-data-jpa-handson) - basic get/save
// Hands-on 3 (3. spring-data-jpa-handson) - HQL and native query methods added

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // --- Hands-on 4: basic get and save ---

    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start");
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start");
        employeeRepository.save(employee);
        LOGGER.info("End");
    }

    // --- Hands-on 3 (3. spring-data-jpa-handson): HQL query methods ---

    // HQL - JPA navigates the @ManyToOne to department automatically
    @Transactional
    public List<Employee> findByDepartmentNameHql(String deptName) {
        return employeeRepository.findByDepartmentNameHql(deptName);
    }

    // HQL - get all permanent employees sorted by salary
    @Transactional
    public List<Employee> findPermanentEmployees() {
        return employeeRepository.findPermanentEmployeesHql();
    }

    // HQL - salary range filter
    @Transactional
    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryRangeHql(minSalary, maxSalary);
    }

    // --- Hands-on 3 (3. spring-data-jpa-handson): native query methods ---

    // native SQL - needs explicit JOIN since it doesn't know JPA relationships
    @Transactional
    public List<Employee> findByDepartmentNameNative(String deptName) {
        return employeeRepository.findByDepartmentNameNative(deptName);
    }

    // native SQL - salary above threshold
    @Transactional
    public List<Employee> findBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThanNative(salary);
    }
}
