package com.example.company;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY_PROJECTS")
public class Project {
	
	private @Id @GeneratedValue Long id;
	private Employee supervisor;
	private String name;
	private String description;
	private ProjectStatus status;
	
	
	Project() {};
	
	Project(String name, String description){
		this.name = name;
		this.description = description;
		this.status = ProjectStatus.NOT_ASSIGNED;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, supervisor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(supervisor, other.supervisor);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", supervisor=" + supervisor + ", name=" + name + ", description=" + description
				+ "]";
	}
	
}
