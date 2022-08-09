package com.lacrimosica.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectRepository repository;

    ProjectController(ProjectRepository repository) {
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
    ResponseEntity<Project> assign(@PathVariable Long idp, @PathVariable Long ids) {
        return repository.findById(idp)
                .map(project -> {
                    project.setStatus(ProjectStatus.ASSIGNED);
                    project.setSupervisorId(ids);
                    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(project));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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

}
