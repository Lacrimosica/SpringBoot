package com.example.company;

import java.io.Serial;

public class OrderNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    OrderNotFoundException(Long id){
        super("could not find order " + id);
    }
}
