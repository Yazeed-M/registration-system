    package com.project.registration_system.components.user.services;

    import java.util.HashMap;
    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;

    import com.project.registration_system.components.user.entities.User;
    import com.project.registration_system.components.user.repository.UserRepository;

    @Service
    public class UserService {
        @Autowired
        private final UserRepository repo;
        private final String TOPIC="user-created";
        private final KafkaTemplate<String, Object> kafkaTemplate;
        public UserService(UserRepository repo, KafkaTemplate<String, Object> kafkaTemplate){
            this.repo=repo;
            this.kafkaTemplate = kafkaTemplate;
        }

        public void createStudent(String name, String password, List<String> courseList, List<String> courseCode){
            User user= new User();
            user.setName(name);
            user.setPassword(password);
            User saved=this.repo.save(user);
            if(saved != null){
            HashMap<String, List<String>> kafkaMessage = new HashMap<>();

            kafkaMessage.put("courseCode", courseCode);
            kafkaMessage.put("courseList", courseList);

                this.kafkaTemplate.send("user-created", kafkaMessage);
            }
        }
    }
