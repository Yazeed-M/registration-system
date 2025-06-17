package com.project.registration_system.components.course.services;

import java.util.Optional;

import static org.apache.kafka.common.requests.FetchMetadata.log;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private final LockService lockService;

    public CourseService(CourseRepository courseRepo, JobScheduler jobScheduler, UserCourseRepository userCourseRepository, StringRedisTemplate redisTemplate, LockService lockService) {
        this.courseRepo = courseRepo;
        this.userCourseRepository = userCourseRepository;
        this.jobScheduler = jobScheduler;
        this.lockService = lockService;
    }

    @KafkaListener(topics = "user-created", groupId = "course")
    public void createCourse(MessageDto message) {

            if (message == null) {
                log.warn("Received null message from Kafka");
                return;
            }
            //adds the user if the course exists in db
            Optional<Course> courseOnDb = courseRepo.findCourseByCourseName(message.getCourseName());
            if (courseOnDb.isPresent()) {
                handleExistentCourse(courseOnDb, message);
                return;
            }

            // from here forward lock the logic for 24 hours 
            String redisKey = "lock:create:course:daily";
            boolean isLocked = lockService.createLock(redisKey);
            if (isLocked) {
                Course course = new Course();
                course.setCourseCode(message.getCourseCode());
                course.setCourseName(message.getCourseName());
                Course saved = courseRepo.save(course);

                if (saved != null) {
                    jobScheduler.enqueue(() -> this.createRecord(message.getUserId(), saved.getId()));
                }
            }
            else{
                log.info("the lock is active");
            }
        }
    

    public void createRecord(Long userId, Long courseId) {

        UserCourse userCourseRecord = new UserCourse();
        userCourseRecord.setUserId(userId);
        userCourseRecord.setCourseId(courseId);

        this.userCourseRepository.save(userCourseRecord);
    }

    private void handleExistentCourse(Optional<Course> course, MessageDto message) {
        jobScheduler.enqueue(() -> this.createRecord(message.getUserId(), course.get().getId()));
    }
}
