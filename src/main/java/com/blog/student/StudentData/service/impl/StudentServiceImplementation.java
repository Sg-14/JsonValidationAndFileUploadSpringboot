package com.blog.student.StudentData.service.impl;

import com.blog.student.StudentData.entity.Student;
import com.blog.student.StudentData.mappers.StudentMapper;
import com.blog.student.StudentData.payload.StudentDto;
import com.blog.student.StudentData.repository.StudentRepository;
import com.blog.student.StudentData.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudents() {
        List<Student> all = studentRepository.findAll();
        return all;
    }

    @Override
    public Student getStudent(long id) {
        Student byId = studentRepository.findById(id)
                .orElseThrow();
        return byId;
    }

    @Override
    public Student addStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToStudent(studentDto);
        studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudent(long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow();
        studentMapper.updateStudent(student, studentDto);
        studentRepository.save(student);
        return null;
    }

    @Override
    public List<Student> searchStudent(String query) {
        List<Student> students = studentRepository.searchStudent(query);
        return students;
    }
}
