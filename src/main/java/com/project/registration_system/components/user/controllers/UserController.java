package com.project.registration_system.components.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.registration_system.components.user.dtos.CreateStudentDto;
import com.project.registration_system.components.user.entities.User;
import com.project.registration_system.components.user.services.UserService;

@RestController
@RequestMapping(path= "api/v1/")
public class UserController{
    
    @Autowired
    private final UserService userService;
    public UserController(com.project.registration_system.components.user.services.UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path ="private/create-user")
    public void createStudent(@RequestBody() CreateStudentDto createStudentDto){
        this.userService.createStudent(createStudentDto.getName(),createStudentDto.getPassword(), createStudentDto.getCourseName(), createStudentDto.getCourseCode());
    }
    
    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody User user){
        return userService.login(user.getName(), user.getPassword());
    }

}