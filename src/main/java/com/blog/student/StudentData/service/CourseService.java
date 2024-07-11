package com.blog.student.StudentData.service;

import com.blog.student.StudentData.entity.Course;

public interface CourseService {
    void saveCourse(Course course);
    Course findOrAddCourse(String name, String institute);
}
