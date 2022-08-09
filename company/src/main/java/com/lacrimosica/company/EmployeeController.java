package com.lacrimosica.company;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {
    private final EmployeeRepository repository;
    private final ProjectRepository projectRepository;

    EmployeeController(EmployeeRepository repository,
                       ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository= projectRepository;
    }

    @GetMapping("/employees")
    ResponseEntity<List<Employee>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
    // end::get-aggregate-root[]

    @PostMapping("/employees")
    ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newEmployee));
    }

    // Single item

    @GetMapping("/employees/{id}")
    ResponseEntity<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(employee));
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newEmployee));
                });
    }


    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/employees/{id}/projects")
    ResponseEntity<List<Project>> getAssignedProjects(@PathVariable Long id){

       List<Project> projects = projectRepository.findAll().stream().filter(project -> project.getSupervisorId() == id)
               .collect(Collectors.toList());
        if(projects.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }
}