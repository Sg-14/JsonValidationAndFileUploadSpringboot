package com.blog.student.StudentData.service.impl;

import com.blog.student.StudentData.entity.Topic;
import com.blog.student.StudentData.repository.TopicRepository;
import com.blog.student.StudentData.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicServiceImplementation implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic findByName(String name) {
        Optional<Topic> byName = topicRepository.findByName(name);
        if(byName.isPresent()){
            return byName.get();
        } else {
            Topic topic = new Topic();
            topic.setName(name);
            return topicRepository.save(topic);
        }
    }
}
