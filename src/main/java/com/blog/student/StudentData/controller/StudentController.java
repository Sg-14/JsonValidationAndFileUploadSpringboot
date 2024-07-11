package com.blog.student.StudentData.controller;

import com.blog.student.StudentData.entity.Student;
import com.blog.student.StudentData.payload.StudentDto;
import com.blog.student.StudentData.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentDto studentDto){
        Student student = studentService.addStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudent(){

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping("search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam("query") String query){
        return ResponseEntity.ok(studentService.searchStudent(query));
    }
}
