package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Hands-On 3: REST Department Web Service – GET /departments
@RestController
@CrossOrigin(origins = "*")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController() {
        LOGGER.info("DepartmentController initialized");
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        LOGGER.info("START - getAllDepartments()");
        List<Department> departments = departmentService.getAllDepartments();
        LOGGER.info("END - getAllDepartments() : count = {}", departments.size());
        return departments;
    }
}
