package com.blog.student.StudentData.mappers;

import com.blog.student.StudentData.entity.Course;
import com.blog.student.StudentData.entity.Student;
import com.blog.student.StudentData.entity.Topic;
import com.blog.student.StudentData.payload.CourseDto;
import com.blog.student.StudentData.payload.StudentDto;
import com.blog.student.StudentData.payload.TopicDto;
import com.blog.student.StudentData.service.CourseService;
import com.blog.student.StudentData.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    @Autowired
    CourseService courseService;

    @Autowired
    TopicService topicService;

    public Student dtoToStudent(StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setBranch(studentDto.getBranch());
        student.setDob(studentDto.getDob());

        List<Course> courses = new ArrayList<>();
        for(CourseDto courseDto: studentDto.getCourses()){
            Course course = courseService.findOrAddCourse(courseDto.getName(), courseDto.getInstitute());

            List<Topic> topics = new ArrayList<>();
            for(TopicDto topic: courseDto.getTopics()){
                Topic topic1 = topicService.findByName(topic.getTopic());
                topics.add(topic1);
            }
            course.setTopics(topics);
            courses.add(course);
        }
        student.setCourses(courses);
        return student;
    }

    public void updateStudent(Student student, StudentDto studentDto){
        student.setName(studentDto.getName());
        student.setBranch(studentDto.getBranch());
        student.setDob(studentDto.getDob());

        List<Course> courses = new ArrayList<>();
        for(CourseDto courseDto: studentDto.getCourses()){
            Course course = new Course();
            course.setName(courseDto.getName());
            course.setInstitute(courseDto.getInstitute());
            List<Topic> topics = new ArrayList<>();
            for(TopicDto topic: courseDto.getTopics()){
                Topic topic1 = new Topic();
                topic1.setName(topic.getTopic());
                topics.add(topic1);
            }
            course.setTopics(topics);
            courses.add(course);
        }
        student.setCourses(courses);
    }
}
