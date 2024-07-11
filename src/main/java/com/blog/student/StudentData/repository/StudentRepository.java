package com.blog.student.StudentData.repository;

import com.blog.student.StudentData.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE " +
            "s.name LIKE CONCAT('%', :query, '%')")
    List<Student> searchStudent(String query);
}
