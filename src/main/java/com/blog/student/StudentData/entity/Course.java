package com.blog.student.StudentData.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "courses"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String institute;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_topic",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id")
    )
    List<Topic> topics;

    @Override
    public String toString() {
        return "Course{" + '\n' +
                "id=" + id +
                ", name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", topics=" + topics +
                '}';
    }
}
