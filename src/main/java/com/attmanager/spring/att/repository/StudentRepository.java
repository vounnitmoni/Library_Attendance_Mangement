package com.attmanager.spring.att.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attmanager.spring.att.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "select name from attmanger.students where id = :id", nativeQuery = true)
    String findStudentName(@Param("id") String id);

    @Query(value = "select profile_pic from attmanger.students where id = :id", nativeQuery = true)
    byte[] findStudentProfile(@Param("id") String id);    
}
