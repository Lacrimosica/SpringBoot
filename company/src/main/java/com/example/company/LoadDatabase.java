package com.example.company;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  /*previous version
  //what the runner does it to request a copy of the employeeRepository
  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {
	  
	  //creates two entities and stores them
    return args -> {
      log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
      log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));
    };
  }
  */
  
  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository , ProjectRepository projectRepository) {

    return args -> {
      employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
      employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

      employeeRepository.findAll().forEach(employee -> {
    	  log.info("Preloaded " + employee);
      });

      
      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });
      
      
      projectRepository.save(new Project("REST API" , "create a rest API"));
      projectRepository.save(new Project("Consulting" , "consult a company"));
     
      projectRepository.findAll().forEach(project -> {
    	  log.info("Preloade " + project);
      });
      
    };
  }
}