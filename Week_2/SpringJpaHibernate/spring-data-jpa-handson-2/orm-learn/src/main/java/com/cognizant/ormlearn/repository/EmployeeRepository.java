package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Hands-on 3, 4, 5, 6 (2. spring-data-jpa-handson) - basic CRUD
// Hands-on 3 (3. spring-data-jpa-handson) - @Query HQL and native SQL
//
// Key difference HQL vs native here:
//   HQL: e.department.name - navigates the @ManyToOne relationship automatically (no manual JOIN needed)
//   Native: requires a manual JOIN between employee and department tables

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // --- Hands-on 3 (3. spring-data-jpa-handson): @Query HQL ---

    // HQL join through relationship - no JOIN keyword needed, hibernate handles it
    // e.department.name navigates the @ManyToOne mapping we defined in Employee
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findByDepartmentNameHql(@Param("deptName") String deptName);

    // HQL - permanent employees only, sorted by salary descending
    @Query("SELECT e FROM Employee e WHERE e.permanent = true ORDER BY e.salary DESC")
    List<Employee> findPermanentEmployeesHql();

    // HQL - employees with salary in a range
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findBySalaryRangeHql(@Param("minSalary") double minSalary,
                                        @Param("maxSalary") double maxSalary);


    // --- Hands-on 3 (3. spring-data-jpa-handson): @Query native SQL ---
    // native SQL needs explicit JOIN since it doesn't know about JPA relationships

    @Query(value = "SELECT e.* FROM employee e JOIN department d ON e.em_dp_id = d.dp_id WHERE d.dp_name = :deptName",
           nativeQuery = true)
    List<Employee> findByDepartmentNameNative(@Param("deptName") String deptName);

    // native - employees earning above a salary threshold
    @Query(value = "SELECT * FROM employee WHERE em_salary > :salary ORDER BY em_salary DESC",
           nativeQuery = true)
    List<Employee> findBySalaryGreaterThanNative(@Param("salary") double salary);
}
