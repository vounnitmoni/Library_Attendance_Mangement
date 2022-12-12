package com.attmanager.spring.att.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attmanager.spring.att.models.StudentJoinLibrary;

@Repository
public interface StudentJoinLibraryRepository extends JpaRepository<StudentJoinLibrary, Long> {
    @Query(value = "select join_date from attmanger.student_join_library where student = :id ", nativeQuery = true)
    LocalDate studentJoinDate(@Param("id") String id);

    @Query(value = "select * from attmanger.student_join_library where join_date = :date", nativeQuery = true)
    List<StudentJoinLibrary> joinInfoByDate(@Param("date") LocalDate date);

    @Query(value = "select * from attmanger.student_join_library where join_date between :date1 and :date2", nativeQuery = true)
    List<StudentJoinLibrary> findJoinInfoManually(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);
}
