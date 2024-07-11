package com.blog.student.StudentData.service;

import com.blog.student.StudentData.entity.Topic;

public interface TopicService {
    Topic findByName(String name);
}
