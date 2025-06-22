package com.project.registration_system.components.user.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.registration_system.components.user.dtos.MessageDto;
import com.project.registration_system.components.user.dtos.UserNotificationsDto;
import com.project.registration_system.components.user.entities.User;
import com.project.registration_system.components.user.entities.User_Notification;
import com.project.registration_system.components.user.repository.UserNotificationRepository;
import com.project.registration_system.components.user.repository.UserRepository;

@Service
public class UserNotificationsService {

    @Autowired
    private final UserRepository userRepository;
    private final UserNotificationRepository userNotificationsRepository;

    public UserNotificationsService(com.project.registration_system.components.user.repository.UserRepository userRepository, UserNotificationRepository userNotificationsRepository) {
        this.userRepository = userRepository;
        this.userNotificationsRepository = userNotificationsRepository;
    }

    @KafkaListener(topics = "user-added", groupId = "notification-listener")
    public void sendNotificationsMethod(String message) {

        try {
            UserNotificationsDto dto = new ObjectMapper().readValue(message, UserNotificationsDto.class);
            
            if (dto.getUser_id() != null) {
                Optional<User> user = userRepository.findById(dto.getUser_id());
                if (user.isPresent()) {
                    String username = user.get().getName();
                    User_Notification record = new User_Notification();
                    String notificationBody = username + " has been added to course: " + dto.getCourse_name();
                    record.setNotification_body(notificationBody);
                    record.setCourse_name(dto.getCourse_name());
                    record.setUser_name(username);
                    
                    userNotificationsRepository.save(record);
                }
            }
        } catch (JsonProcessingException ex) {
        }
    }
}
