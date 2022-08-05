package com.example.company;

import org.springframework.data.jpa.repository.JpaRepository;

//we specify the domain as Employee alongside with the id as Long

//when using JpaRepository we don't even need to provide the method signatures 
//and i guess they are inferred just by looking at the domain/class that we provide it
public interface EmployeeRepository extends JpaRepository <Employee, Long>{
}

