package com.project.registration_system.components.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User_Notification {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String notification_body;
    private String course_name;
    private String user_name;
    
    public User_Notification(){}

    public User_Notification(Long id, String notification_body, String course_name, String user_name) {
        this.id = id;
        this.notification_body = notification_body;
        this.course_name = course_name;
        this.user_name = user_name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNotification_body() {
        return notification_body;
    }
    public void setNotification_body(String notification_body) {
        this.notification_body = notification_body;
    }
    public String getCourse_name() {
        return course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    
}
