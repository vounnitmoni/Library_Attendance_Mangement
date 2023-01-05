package com.attmanager.spring.att.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attmanager.spring.att.models.AcademicYear;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Integer> {
}
