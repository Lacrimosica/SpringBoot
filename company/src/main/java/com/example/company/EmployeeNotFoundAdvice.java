package com.example.company;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


//this whole class is an MVC configuration that is used to render an HTTP 404.
@ControllerAdvice
class EmployeeNotFoundAdvice {

  @ResponseBody											//signals that this advice is rendered straight 
  														//in to the response body.
  
  
  @ExceptionHandler(EmployeeNotFoundException.class)	//configures the advice to only respond if 
  														//an EmployeeNotFoundException is thrown
  
  
  @ResponseStatus(HttpStatus.NOT_FOUND)					//says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.
  
  String employeeNotFoundHandler(EmployeeNotFoundException ex) {
    return ex.getMessage();								//the returned body is the exceptions's message
  }
}