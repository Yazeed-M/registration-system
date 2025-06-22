package com.project.registration_system.components.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.registration_system.apis.internal_apis.AuthApis;
import com.project.registration_system.components.user.dtos.CreateStudentDto;
import com.project.registration_system.components.user.dtos.MessageDto;
import com.project.registration_system.components.user.entities.User;
import com.project.registration_system.components.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository repo;
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final AuthApis authInternalApi;
    public UserService(UserRepository repo, KafkaTemplate<String, MessageDto> kafkaTemplate, AuthApis authInternalApi) {
        this.repo = repo;
        this.kafkaTemplate = kafkaTemplate;
        this.authInternalApi = authInternalApi;
    }

    public void createStudent(CreateStudentDto createStudentDto) {

        User onDb = repo.findUserByName(createStudentDto.getName());
        if (onDb != null) {
            handleExistentUsers(onDb, createStudentDto.getCourseName(), createStudentDto.getCourseCode());
        } else {
            User user = new User();
            user.setName(createStudentDto.getName());
            user.setPassword(createStudentDto.getPassword());
            User saved = this.repo.save(user);

            MessageDto payload = new MessageDto();

            payload.setCourseCode(createStudentDto.getCourseCode());
            payload.setCourseName(createStudentDto.getCourseName());
            payload.setUserId(saved.getId());

            this.kafkaTemplate.send("user-created", payload);
        }
    }

    @SuppressWarnings("empty-statement")
    public ResponseEntity<?> login(String name, String password
    ) {
        User user = repo.findUserByName(name);
        if (user != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, user.getPassword())) {
                return authInternalApi.authenticationServiceApis(name, "/api/auth/");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    }

    private void handleExistentUsers(User user, String courseName, String courseCode) {
        MessageDto payload = new MessageDto();
        payload.setCourseCode(courseCode);
        payload.setCourseName(courseName);
        payload.setUserId(user.getId());

        this.kafkaTemplate.send("user-created", payload);
    }
}
