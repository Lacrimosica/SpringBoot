package com.example.company;

public class OrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	OrderNotFoundException(Long id){
		super("could not find order " + id);
	}
}
