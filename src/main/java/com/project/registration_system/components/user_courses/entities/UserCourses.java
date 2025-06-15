package com.project.registration_system.components.user_courses.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserCourses {
    @Id @GeneratedValue
    private Long id;
    
    private Long userId;
    private Long courseId;

    private LocalDateTime enrolled_At;
}
