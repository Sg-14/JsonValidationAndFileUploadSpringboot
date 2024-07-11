package com.blog.student.StudentData.repository;

import com.blog.student.StudentData.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByNameAndInstitute(String name, String institute);
}
