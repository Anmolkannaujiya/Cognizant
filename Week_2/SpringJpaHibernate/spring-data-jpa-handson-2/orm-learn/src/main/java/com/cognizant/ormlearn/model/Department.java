package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.util.Set;

// Hands-on 3, 5 (2. spring-data-jpa-handson) - Department entity
// OneToMany side of the Employee-Department relationship

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private int id;

    @Column(name = "dp_name")
    private String name;

    // Hands-on 5 - OneToMany mapping back to Employee
    // mappedBy="department" means the FK lives on the employee side (em_dp_id)
    // EAGER so we get the employee list without a separate query
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Employee> employeeList;

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    // not including employeeList in toString to avoid circular output
    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
