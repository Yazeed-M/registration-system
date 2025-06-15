package com.project.registration_system.components.course.entities;

import java.util.List;

import com.project.registration_system.components.user.entities.User;
import com.yahoo.elide.annotation.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity()
@Include(name= "course")
public class Course {
    @Id @GeneratedValue
    private Long id;

    private String courseName;
    private String courseCode;

    @ManyToMany(mappedBy="Courses")
    private List<User> users ;
    
    public Course(){}

    public Course(Long id, String courseName, String courseCode, List<User> users) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.users = users;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
}
