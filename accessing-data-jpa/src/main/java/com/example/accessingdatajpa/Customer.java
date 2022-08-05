package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//indicated that it is a JPA entity
//(Because no @Table annotation exists, it is assumed that this entity is mapped to a table named Customer.)
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)	//this indicates that the ID should be generated automatically
  private Long id;
  private String firstName;
  private String lastName;

  //first and last name are left unannotated. It is assumed that they are mapped to 
  //columns that share the same names as the properties themselves.
  
  
  protected Customer() {}	//this exists only for the sake of JPA and is not used directly

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}