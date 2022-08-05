package com.example.accessingdatarest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//at runtime, Spring Data REST automatically creates an implementation for this interface. then it uses
//the below annotation to direct spring MVC to create RESTful end points at "/people"


//@RepositoryRestResource is not required for a repository to be exported. It is used only to change 
//the export details such as using /people instead of the default value of /persons

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>{
	
	List<Person> findByLastName(@Param("name") String name);
}
