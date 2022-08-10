package com.lacrimosica.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectRepository repository;
    private final EmployeeRepository employeeRepository;

    ProjectController(ProjectRepository repository , EmployeeRepository employeeRepository) {
        this.employeeRepository =employeeRepository;
        this.repository = repository;
    }

    @GetMapping("/projects")
    ResponseEntity<List<Project>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/projects/{id}")
    ResponseEntity<Project> one(@PathVariable Long id) {
        return repository.findById(id)
                .map(project -> ResponseEntity.status(HttpStatus.OK).body(project))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/projects")
    ResponseEntity<Project> newProject(@RequestBody Project newProject) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newProject));
    }

    @PutMapping("/projects/{idp}/assign/{ids}")
    ResponseEntity<?> assign(@PathVariable Long idp, @PathVariable Long ids) {
        Employee supervisor = employeeRepository.findById(ids)
                .orElseThrow(() -> new EmployeeNotFoundException(ids));
        Project project = repository.findById(idp)
                .orElseThrow( () -> new ProjectNotFoundException(idp));
        return repository.findById(idp)
                .map(p -> {
                    p.setStatus(ProjectStatus.ASSIGNED);
                    p.setSupervisorId(ids);
                    p.setSupervisor(supervisor);
                    supervisor.getProjects().putIfAbsent(p.getName(), p);
                    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(p));
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/projects/{idp}/unassign")
    ResponseEntity<Project> unassign(@PathVariable Long idp) {
        return repository.findById(idp)
                .map(project -> {
                    project.setStatus(ProjectStatus.UN_ASSIGNED);
                    project.setSupervisorId(null);
                    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(project));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/projects/{idp}/delete")
    ResponseEntity<?> deleteProject(@PathVariable Long idp){
        return repository.findById(idp)
                .map(project -> {
                    repository.delete(project);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Project with id " + idp + " doesn't exist"));
    }

    @DeleteMapping("/projects/{idp1}/{idp2}/delete")
    ResponseEntity<?> deleteProjects(@PathVariable Long idp1, @PathVariable Long idp2) {
        if (idp2 > repository.count()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Range of Project IDs");
        }

        for (Project project : repository.findAll()) {
            if (project.getId() >= idp1 || project.getId() <= idp2) {
                repository.delete(project);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
