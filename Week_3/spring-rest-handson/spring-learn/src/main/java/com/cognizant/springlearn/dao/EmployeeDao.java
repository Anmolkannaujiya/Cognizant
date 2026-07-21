package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Hands-On 3: DAO that loads employeeList from employee.xml at startup
@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static ArrayList<Employee> EMPLOYEE_LIST;

    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        LOGGER.info("START - EmployeeDao constructor: loading employee list from employee.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList");
        LOGGER.info("END - EmployeeDao constructor: loaded {} employees", EMPLOYEE_LIST.size());
    }

    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START - getAllEmployees()");
        LOGGER.info("END - getAllEmployees() : count = {}", EMPLOYEE_LIST.size());
        return EMPLOYEE_LIST;
    }
}
