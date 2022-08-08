package com.example.company;

import java.io.Serial;

public class ProjectNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    ProjectNotFoundException(Long id){
        super("could not find project " + id);
    }
}
