package com.project.registration_system.components.course.services;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;

public class CourseService {
    
    @KafkaListener(topics="user-created", groupId="my-group")
    public void k(Map<String, Object> message){
        System.out.print(message);
    }
}
