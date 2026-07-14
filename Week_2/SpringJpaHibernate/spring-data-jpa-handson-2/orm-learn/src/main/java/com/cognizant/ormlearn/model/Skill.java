package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.util.Set;

// Hands-on 3, 6 (2. spring-data-jpa-handson) - Skill entity
// ManyToMany side - mappedBy means the join table is defined on Employee side

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sk_id")
    private int id;

    @Column(name = "sk_name")
    private String name;

    // inverse side of the ManyToMany - mappedBy refers to the field name in Employee
    @ManyToMany(mappedBy = "skillList")
    private Set<Employee> employeeList;

    public Skill() {
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

    // not including employeeList to avoid circular output
    @Override
    public String toString() {
        return "Skill{id=" + id + ", name='" + name + "'}";
    }
}
