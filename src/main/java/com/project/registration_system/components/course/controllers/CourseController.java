package com.project.registration_system.components.course.controllers;
// revise if needed or not
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.registration_system.components.course.services.CourseService;

@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseController {
    
    @Autowired
    private final CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }
}
