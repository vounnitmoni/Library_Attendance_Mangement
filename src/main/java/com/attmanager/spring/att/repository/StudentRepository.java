package com.attmanager.spring.att.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attmanager.spring.att.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    
}
