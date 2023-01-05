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
    @Query(value = "select join_date from attmanger.student_join_library where student_id = :id and id = (select max(id) from attmanger.student_join_library)", nativeQuery = true)
    LocalDate studentJoinDate(@Param("id") String id);

    @Query(value = "select * from attmanger.student_join_library where join_date = :date group by student_id", nativeQuery = true)
    List<StudentJoinLibrary> joinInfoByDate(@Param("date") LocalDate date);

    @Query(value = "select * from attmanger.student_join_library where join_date between :date1 and :date2", nativeQuery = true)
    List<StudentJoinLibrary> findJoinInfoManually(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    @Query(value = "select student_id from attmanger.student_join_library where where join_date between :date1", nativeQuery = true)
    List<StudentJoinLibrary> findStudentIdByDate(@Param("date1") LocalDate date1);

    @Query(value = "select year.year, count(join_date) as quantity from attmanger.student_join_library " + 
    "inner join attmanger.students on student_join_library.student_id = students.id " +
    "inner join attmanger.year on year.id = students.year " +
    "group by year.year, join_date", nativeQuery = true)
    List<Object[]> reporbystudentyear();

    @Query(value = "select year.year, count(distinct students.year) as quantity from attmanger.student_join_library " + 
    "inner join attmanger.students on student_join_library.student_id = students.id " +
    "inner join attmanger.year on year.id = students.year " +
    "where student_join_library.join_date between :date1 and :date2 group by year.year, join_date", nativeQuery = true)
    List<Object[]> reporByStudentYearAndDate(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    @Query(value = "select departments.department, count(distinct students.department) as quantity from attmanger.student_join_library " +
    "inner join attmanger.students on students.id = student_join_library.student_id " +
    "inner join attmanger.departments on students.department = departments.id group by departments.department, join_date", nativeQuery = true)
    List<Object[]> reporByStudentDep();

    @Query(value = "select departments.department, count(distinct students.department) as quantity from attmanger.student_join_library " +
    "inner join attmanger.students on students.id = student_join_library.student_id " +
    "inner join attmanger.departments on students.department = departments.id where student_join_library.join_date between :date1 and :date2 group by departments.department, join_date", nativeQuery = true)
    List<Object[]> reporByStudentDepAndDate(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    @Query(value = "select concat(departments.department,'_', year.year) as department_and_year, count(students.department) as quantity from attmanger.student_join_library " +
    "inner join attmanger.students on students.id = student_join_library.student_id " + 
    "inner join attmanger.departments on students.department = departments.id " +
    "inner join attmanger.year on students.year = year.id " +
    "group by department_and_year", nativeQuery= true)
    List<Object[]> reportByStudentDepAndYear();

    Boolean existsByStudentid(String studentid);
}
