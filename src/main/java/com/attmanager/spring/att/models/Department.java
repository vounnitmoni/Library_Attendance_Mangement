package com.attmanager.spring.att.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    private String department;
    private String description;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Student> student;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Option> depOption;
    public Department(@Size(max = 50) String department, String description) {
        this.department = department;
        this.description = description;
    }
    public Department() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Student> getStudent() {
        return student;
    }
    public void setStudent(Set<Student> student) {
        this.student = student;
    }
    public Set<Option> getDepOption() {
        return depOption;
    }
    public void setDepOption(Set<Option> depOption) {
        this.depOption = depOption;
    }
    
    
}
