package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Hands-on 3, 4, 5 (2. spring-data-jpa-handson)
// basic CRUD for department

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
