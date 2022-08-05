package com.example.company;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;


import static org.springframework.hateoas.mediatype.problem.Problem.create;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; //for methodOn and linkTo


@RestController
public class ProjectController {
	private final ProjectRepository repository;
	private final ProjectModelAssembler assembler;
	
	ProjectController(ProjectRepository repository , ProjectModelAssembler assembler){
		this.repository =repository;
		this.assembler = assembler;
	}
	
	
	@GetMapping("/projects")
	@ResponseBody	//ASK
	CollectionModel<EntityModel<Project>> all(){
		
		List<EntityModel<Project>> projects = 
				repository.findAll().stream()
					.map(assembler::toModel)
					.collect(Collectors.toList());
		
		return CollectionModel.of(projects,
				linkTo(methodOn(ProjectController.class).all()).withSelfRel());
	}
	
	@GetMapping("/projects/{id}")
	@ResponseBody
	EntityModel<Project> one(@PathVariable Long id){
		
		Project project = repository.findById(id)
				.orElseThrow( () -> new ProjectNotFoundException(id));
	
		return assembler.toModel(project);
		
	}
	
	
	//Done
	@PostMapping("/projects")
	ResponseEntity<?> newProject(@RequestBody Project newProject) {
		EntityModel<Project> project = 
				assembler.toModel(repository.save(newProject));
		
		return ResponseEntity
				.created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(project);
	}
	
	//Done
	@PutMapping("/projects/{id}")
	ResponseEntity<?> replaceProject(@RequestBody Project newProject, @PathVariable Long id) {
		
		EntityModel<Project> updatedProject = 
				repository.findById(id)
					.map(p -> {
						
							p.setName(newProject.getName());
							p.setDescription(newProject.getDescription());
							p.setSupervisor(newProject.getSupervisor());
							return assembler.toModel(repository.save(p));
					}).orElseGet(() -> {
					
							newProject.setId(id);
							return  assembler.toModel(repository.save(newProject));
				});
		
		return ResponseEntity
				.created(updatedProject.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(updatedProject);
	}
	
	
	
	//Done
	@DeleteMapping("/projects/{id}/cancel")
	ResponseEntity<?> cancel(@PathVariable Long id) {
		Project project = repository.findById(id)
				.orElseThrow( () -> new ProjectNotFoundException(id));
		
		if(project.getStatus() == ProjectStatus.ASSIGNED) {
			project.setStatus(ProjectStatus.CANCELLED);
			return ResponseEntity.ok(assembler.toModel(repository.save(project)));
		}
			
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
					.body(create()
							.withTitle("Method not allowed")
							.withDetail("You can't cancel a project that is in the " + project.getStatus() + "state"));
		}
}