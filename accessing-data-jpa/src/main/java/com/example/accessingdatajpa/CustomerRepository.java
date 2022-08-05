package com.example.accessingdatajpa;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	
	//CustomerRepository inherits several methods for working with Customer persistence.
	//and they are all defined by their method signatures and Spring Data creates the implementation when you
	//run the application
  List<Customer> findByLastName(String lastName);
  Customer findById(long id);
  
}