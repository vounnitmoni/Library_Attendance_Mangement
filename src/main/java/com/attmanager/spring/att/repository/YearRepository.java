package com.attmanager.spring.att.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attmanager.spring.att.models.Year;

public interface YearRepository extends JpaRepository<Year, Integer> {
    
}
