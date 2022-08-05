package com.example.company;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// by applying the @Component annotation the assembler will be automatically created when the application starts.

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{

	
	//t is based on converting a non-model object (Employee) into a model-based object (EntityModel<Employee>).
	
	@Override
	public EntityModel<Employee> toModel(Employee employee) {	
		return EntityModel.of(employee,
				linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}
}


/*

Spring HATEOAS’s abstract base class for all models is RepresentationModel. But for simplicity, 
I recommend using EntityModel<T> as your mechanism to easily wrap all POJOs as models.

*/