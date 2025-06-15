package com.project.registration_system.components.course.entities;

import com.project.registration_system.components.user.entities.User;
import com.yahoo.elide.annotation.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity()
@Include(name= "course")
public class Course {
    @Id @GeneratedValue
    private Long id;

    
    private String courseName;
    private String courseCode;

    @ManyToOne()
    @JoinColumn(name= "user_id")
    private User userId;
    
    public Course(){}

    public Course(Long id, String courseName, String courseCode, User userId) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.userId = userId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

}
