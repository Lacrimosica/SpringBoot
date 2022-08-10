//package com.lacrimosica.company;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//class LoadDatabase {
//
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
//                                   OrderRepository orderRepository,
//                                   ProjectRepository projectRepository) {
//
//        return args -> {
//            employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
//            employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));
//
//            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
//
//
//            orderRepository.save(new Order("MacBook Pro", OrderStatus.COMPLETED));
//            orderRepository.save(new Order("iPhone", OrderStatus.IN_PROGRESS));
//
//            orderRepository.findAll().forEach(order -> {
//                log.info("Preloaded " + order);
//            });
//
//            projectRepository.save(new Project("Project 1" ));
//            projectRepository.save(new Project("Project 2" ));
//            projectRepository.save(new Project("Project 3" ));
//        };
//    }
//}