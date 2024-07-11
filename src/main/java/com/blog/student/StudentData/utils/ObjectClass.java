package com.blog.student.StudentData.utils;

import lombok.Data;

import java.util.List;

public class ObjectClass {
    private String name;
    private String dob;
    private String branch;
    private List<CoursesClass> courses;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<CoursesClass> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesClass> courses) {
        this.courses = courses;
    }
}

