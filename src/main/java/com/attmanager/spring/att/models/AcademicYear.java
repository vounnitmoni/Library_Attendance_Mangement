package com.attmanager.spring.att.models;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "academic_year")
public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate fromYear;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate toYear;

    @OneToMany(mappedBy = "academicYear")
    private Set<Student> student;

    public AcademicYear() {
    }

    public AcademicYear(LocalDate fromYear, LocalDate toYear) {
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFromYear() {
        return fromYear;
    }

    public void setFromYear(LocalDate fromYear) {
        this.fromYear = fromYear;
    }

    public LocalDate getToYear() {
        return toYear;
    }

    public void setToYear(LocalDate toYear) {
        this.toYear = toYear;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }
    

    
}
