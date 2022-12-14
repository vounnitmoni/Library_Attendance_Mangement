package com.attmanager.spring.att.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    private String id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "year", referencedColumnName = "id")
    private Year year;
    private Integer gender;
    private String province;

    @Lob
    private byte[] profilePic;
    
    @ManyToOne
    @JoinColumn(name = "academic_year", referencedColumnName = "id")
    private AcademicYear academicYear;

    public Student(String id, String name, LocalDate dob, Department department, Year year, Integer gender,
            String province, byte[] profilePic) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.department = department;
        this.year = year;
        this.gender = gender;
        this.province = province;
        this.profilePic = profilePic;
    }
    public Student(String name, LocalDate dob, Integer gender, String province) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.province = province;
    }
    public Student() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Year getYear() {
        return year;
    }
    public void setYear(Year year) {
        this.year = year;
    }
    public byte[] getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
    
}
