package com.attmanager.spring.att.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "student_join_library")
public class StudentJoinLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student", referencedColumnName = "id", insertable = false , updatable = false)
    private Student student;
    @Column(name = "student")
    private String studentid;
    
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime joinTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    public StudentJoinLibrary(String studentid, LocalTime joinTime, LocalDate joinDate) {
        this.studentid = studentid;
        this.joinTime = joinTime;
        this.joinDate = joinDate;
    }

    public StudentJoinLibrary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalTime joinTime) {
        this.joinTime = joinTime;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    
}
