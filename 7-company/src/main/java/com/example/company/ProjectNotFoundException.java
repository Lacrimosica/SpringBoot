package com.example.company;

public class ProjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    ProjectNotFoundException(Long id){
        super("could not find project " + id);
    }
}
