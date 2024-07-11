package com.blog.student.StudentData.service;


import com.blog.student.StudentData.entity.Student;
import com.blog.student.StudentData.payload.StudentDto;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudent(long id);
    Student addStudent(StudentDto studentDto);
    void deleteStudent(long id);
    Student updateStudent(long id, StudentDto studentDto);
    List<Student> searchStudent(String query);
}
