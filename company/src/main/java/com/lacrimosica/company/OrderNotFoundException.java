package com.lacrimosica.company;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
            super("Could not find employee " + id);
    }
}
