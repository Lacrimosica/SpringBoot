package com.example.company;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;


//@RestController indicates that the data returned by each method
//will be written straight into the response body instead of rendering a template.

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
//    private final ProjectRepository projectRepository;
    private final EmployeeModelAssembler employeeAssembler;
//    private final ProjectModelAssembler projectAssembler;

    EmployeeController(EmployeeRepository employeeRepository,
                       EmployeeModelAssembler employeeAssembler)
    {
        this.employeeRepository = employeeRepository;
        this.employeeAssembler = employeeAssembler;
//        this.projectAssembler = projectAssembler;
//        this.projectRepository = projectRepository;
    }


    @GetMapping("/employees")
    @ResponseBody	//<=====not sure about this, ASK
    CollectionModel<EntityModel<Employee>> all(){

        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    //Done
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){

        EntityModel<Employee> employee =
                employeeAssembler.toModel(employeeRepository.save(newEmployee));

        return ResponseEntity
                .created(employee.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employee);
    }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {


        Employee employee = employeeRepository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employeeAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        Employee updatedEmployee = employeeRepository.findById(id) //
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                }) //
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = employeeAssembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 	//Using the getRequiredLink() method, you can retrieve the
                //Link created by the EmployeeModelAssembler with a SELF rel.
                //This method returns a Link which must be turned into a URI with the toUri method.
                .body(entityModel);
    }


    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();			//returns a HTTP 204 No Content response
    }


    //To get all the projects assigned to an employee
//    @GetMapping("/employees/{ids}/assignments")
//    CollectionModel<EntityModel<Project>> getAllAssignments(@PathVariable Long ids) {
//        List<EntityModel<Project>> projects =employeeRepository.findById(ids).
//                map(e -> e.getProjects().values().stream().
//                        map(projectAssembler::toModel)
//                        .collect(Collectors.toList()))
//                .orElseThrow(() -> new EmployeeNotFoundException(ids));
//
//        return CollectionModel.of(projects, linkTo(methodOn(ProjectController.class).all()).withSelfRel());
//    }

    //To Assign a project to an employee
//    @PostMapping("/employees/{ids}/assign/{idp}")
//    void assignProject(@PathVariable Long ids , @PathVariable Long idp) {
//        employeeRepository.findById(ids)
//                .map( e -> e.getProjects()
//                        .putIfAbsent(idp, projectRepository.findById(idp).orElseThrow( () -> new ProjectNotFoundException(idp))));
//
//        projectRepository.findById(idp)
//                .ifPresent( p -> p.setSupervisor( employeeRepository.findById(ids).orElseThrow( () -> new EmployeeNotFoundException(idp))));
//
//    }

//    @PutMapping("/employees/{ids}/unassign/{idp}")
//    ResponseEntity<?> unassignProject(@PathVariable Long ids , @PathVariable Long idp) {
//        Project project= projectRepository.findById(idp)
//                .orElseThrow( () -> new ProjectNotFoundException(idp));
//
//        Employee supervisor = employeeRepository.findById(ids)
//                .orElseThrow(() -> new EmployeeNotFoundException(ids));
//
//        ProjectStatus st = project.getStatus();
//
//        if(st == ProjectStatus.NOT_ASSIGNED || st == ProjectStatus.CANCELLED) {
//            return ResponseEntity.badRequest()
//                    .body("Cannot unassign a project that is in the " + st + "status");
//        }
//
//        supervisor.getProjects().remove(idp);
//        project.setSupervisor(null);	//is this good practice? ASK
//        project.setStatus(ProjectStatus.NOT_ASSIGNED);
//        return ResponseEntity.ok(projectAssembler.toModel(projectRepository.save(project)));
//    }


}









/*
Spring MVC’s ResponseEntity is used to create an HTTP 201 Created status message.
This type of response typically includes a Location response header, and we use the
URI derived from the model’s self-related link.
*Additionally, return the model-based version of the saved object.

//@RequestBody annotation maps the HttpRequestbody to a
//transfer or domain object, enabling automatic de-serialization
//of the in-bound httpRquestbody onto a java object
//what we are sending in as JSON or whatever format GET's
//de-serialized to Java Object, this is done automatically supposedly.
  */
