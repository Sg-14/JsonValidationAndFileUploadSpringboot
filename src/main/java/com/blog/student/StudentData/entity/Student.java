package com.blog.student.StudentData.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "students"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    private String dob;
    private String branch;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;

    @Override
    public String toString() {
        return "Student{" + '\n' +
                "id=" + id + '\n'+
                "name='" + name + '\'' + '\n'+
                "dob='" + dob + '\'' + '\n'+
                "branch='" + branch + '\'' +'\n'+
                "courses=" + courses + '\n'+
                '}';
    }
}
