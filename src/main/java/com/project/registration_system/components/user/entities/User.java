package com.project.registration_system.components.user.entities;

import java.util.List;

import com.project.registration_system.components.course.entities.Course;
import com.yahoo.elide.annotation.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity()
@Include(name="user")
public class User {
    @Id @GeneratedValue
    private Long id;

    private String email;
    private String password;
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Course> courseList;
    
    public User() {}
    
    public User(Long id, String email, String password, List<Course> courseList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.courseList = courseList;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
    
}
