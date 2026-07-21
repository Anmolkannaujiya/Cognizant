package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

// Hands-On 3: DAO that loads departmentList from employee.xml at startup
@Repository
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private static ArrayList<Department> DEPARTMENT_LIST;

    @SuppressWarnings("unchecked")
    public DepartmentDao() {
        LOGGER.info("START - DepartmentDao constructor: loading department list from employee.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList");
        LOGGER.info("END - DepartmentDao constructor: loaded {} departments", DEPARTMENT_LIST.size());
    }

    public ArrayList<Department> getAllDepartments() {
        LOGGER.info("START - getAllDepartments()");
        LOGGER.info("END - getAllDepartments() : count = {}", DEPARTMENT_LIST.size());
        return DEPARTMENT_LIST;
    }
}
