package com.blog.student.StudentData.utils;

import lombok.Data;

import java.util.List;

public class CoursesClass {
    private String name;
    private String institute;
    private List<String> topics;

    @Override
    public String toString() {
        return "CoursesClass{" +
                "name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", topics=" + topics +
                '}';
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
