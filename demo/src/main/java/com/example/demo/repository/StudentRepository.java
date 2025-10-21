package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Spring Data JPA automatically generates these SQL queries at runtime

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Custom queries by naming convention
    List<Student> findByCourse(String course); // sekect * from student where course = 'Math'
    List<Student> findByNameStartingWith(String prefix); // select * from student where name LIKE 'H%'
    List<Student> findByCourseContaining(String keywork);// select * from student where course like '%AI%'
    
    long countByCourse(String course); // select count(*) from student where course = 'Math'

}
