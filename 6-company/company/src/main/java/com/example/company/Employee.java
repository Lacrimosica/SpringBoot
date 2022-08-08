package com.example.company;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity is a JPA annotation to make this object ready for storage in a JPA-based data store.
@Entity
public class Employee {

    //id is marked with more JPA annotations to indicate it’s the primary key and
    //automatically populated by the JPA provider.
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Long id;

    private String firstName;
    private String lastName;
    private String role;
    @SuppressWarnings("JpaAttributeTypeInspection")
//    private HashSet<Project> projects;


    //a constructor that does nothing and is later overloaded
    //a custom constructor is created when we need to create a new instance, but don’t yet have an id.
    public Employee(){}

    Employee(String firstName, String lastName, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

//    public HashSet<Project> getProjects() {
//        return projects;
//    }
//
//    public void takeProject(Project project) {
//        this.projects.add(project);
//    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", role='" + this.role + '\'' + '}';
    }
}

