package com.lacrimosica.company;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
