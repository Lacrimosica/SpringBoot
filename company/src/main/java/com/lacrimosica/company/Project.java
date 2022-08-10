package com.lacrimosica.company;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COMPANY_PROJECTS")
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Long supervisorId = null;
    private String name;
    private ProjectStatus status;

    @Transient
    private Employee supervisor;

    public Project() {}

    public Project(String name) {
        this.name = name;
        this.status = ProjectStatus.UN_ASSIGNED;
    }

    //all getters and setters
    public Long getId() {
        return id;
    }

    public Long getSupervisorId() {
        return this.supervisorId;
    }

    public String getName() {
        return name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(supervisorId, project.supervisorId) &&
                Objects.equals(name, project.name) &&
                status == project.status;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, supervisorId, name, status);
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", supervisorId=" + supervisorId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
