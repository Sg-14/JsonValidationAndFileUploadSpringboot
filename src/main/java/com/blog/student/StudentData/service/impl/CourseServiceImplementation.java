package com.blog.student.StudentData.service.impl;

import com.blog.student.StudentData.entity.Course;
import com.blog.student.StudentData.repository.CourseRepository;
import com.blog.student.StudentData.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void saveCourse(Course course) {

    }

    @Override
    public Course findOrAddCourse(String name, String institute) {
        Optional<Course> byNameAndInstitute = courseRepository.findByNameAndInstitute(name, institute);
        if(byNameAndInstitute.isPresent()){
            return byNameAndInstitute.get();
        }
        else{
            Course course = new Course();
            course.setName(name);
            course.setInstitute(institute);
            return courseRepository.save(course);
        }
    }
}
