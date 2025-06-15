package com.project.registration_system.components.user.dtos;

import java.util.List;

public class CreateStudentDto {
    private String name;
    private String password;
    private List<String> courseName;
    private List<String> courseCode;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<String> getCourseName() {
        return courseName;
    }
    public void setCourseName(List<String> courseName) {
        this.courseName = courseName;
    }
    public List<String> getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(List<String> courseCode) {
        this.courseCode = courseCode;
    }
}
