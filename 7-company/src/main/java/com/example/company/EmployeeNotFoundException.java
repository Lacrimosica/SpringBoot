package com.example.company;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    EmployeeNotFoundException(Long id){
        super("could not find employee " + id);
    }
}
