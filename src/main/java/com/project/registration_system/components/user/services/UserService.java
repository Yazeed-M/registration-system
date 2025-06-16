    package com.project.registration_system.components.user.services;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import com.project.registration_system.components.user.entities.User;
    import com.project.registration_system.components.user.repository.UserRepository;
import com.project.registration_system.dtos.MessageDto;

    @Service
    public class UserService {
        @Autowired
        private final RestTemplate restTemplate;
        private final UserRepository repo;
        private final KafkaTemplate<String, MessageDto> kafkaTemplate;
        public UserService(UserRepository repo, KafkaTemplate<String, MessageDto> kafkaTemplate, RestTemplate restTemplate){
            this.repo=repo;
            this.kafkaTemplate = kafkaTemplate;
            this.restTemplate=restTemplate;
        }

        public void createStudent(String name, String password, String courseList, String courseCode){
            User user= new User();
            user.setName(name);
            user.setPassword(password);
            User saved=this.repo.save(user);
            if(saved != null){
            MessageDto kafkaMessage = new MessageDto(courseCode, courseList, saved.getId());

                this.kafkaTemplate.send("user-created", kafkaMessage);
            }
            else{
                throw new IllegalArgumentException("could not save the user");
            }
        }

        @SuppressWarnings("empty-statement")
        public ResponseEntity<?> login(String name, String password){           
            User user = repo.findUserByName(name);
            if(user != null){
                Boolean matchPassword= (user.getPassword().equals(password));
                if(matchPassword){
                String url= "http://localhost:8080/api/auth/"+name;
                ResponseEntity<?> response = restTemplate.getForEntity(url, String.class);
                return response;
                }
            }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");            
        }
    }
