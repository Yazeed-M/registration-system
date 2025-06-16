package com.project.registration_system.components.course.services;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.project.registration_system.components.course.entities.Course;
import com.project.registration_system.components.course.entities.UserCourse;
import com.project.registration_system.components.course.repositories.CourseRepository;
import com.project.registration_system.components.course.repositories.UserCourseRepository;
import com.project.registration_system.dtos.MessageDto;

@Service
public class CourseService {
    
    @Autowired
    private final CourseRepository courseRepo;
    private final UserCourseRepository userCourseRepository;
    private final JobScheduler jobScheduler;

    public CourseService(CourseRepository courseRepo, JobScheduler jobScheduler, UserCourseRepository userCourseRepository) {
        this.courseRepo = courseRepo;
        this.userCourseRepository = userCourseRepository;
        this.jobScheduler = jobScheduler;
    }

    @KafkaListener(topics="user-created", groupId="course")
    public void createCourse(MessageDto message){
        Course course= new Course();
        course.setCourseCode(message.getCourseCode());
        course.setCourseName(message.getCourseName());
        Course saved= courseRepo.save(course);

        if(saved != null){
            jobScheduler.enqueue(() -> this.createRecord(message.getUserId(), saved.getId()));
        }
    }

    public void createRecord(Long userId, Long courseId){
        
        UserCourse userCourseRecord= new UserCourse();
        userCourseRecord.setUserId(userId);
        userCourseRecord.setCourseId(courseId);
        
        this.userCourseRepository.save(userCourseRecord);
    }
}
