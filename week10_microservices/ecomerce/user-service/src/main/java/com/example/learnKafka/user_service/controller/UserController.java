package com.example.learnKafka.user_service.controller;

import com.example.learnKafka.user_service.Repositories.UserRepository;
import com.example.learnKafka.user_service.dtos.UserRequestDTO;
import com.example.learnKafka.user_service.entites.User;
import com.example.learnKafka.user_service.event.UserEventTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<Long , UserEventTopic> kafkaTemplate;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Value("${kafka.topic.user-random-topic}")
    private String UserRandomTopic;

//    @PostMapping("/{message}")
//    public ResponseEntity<String> sendMessage(@PathVariable String message){
//        for(int i=0;i<1000;i++) {
//            kafkaTemplate.send(UserRandomTopic, i, message+i);
//        }
//        return ResponseEntity.ok("message Queued");
//    }

    @Value("${kafka.topic.user-created-topic}")
    private String UserCreatedTopic;

    @PostMapping()
    public ResponseEntity<String> CreateUser(@RequestBody UserRequestDTO userRequestDTO){
        User userRequest = mapper.map(userRequestDTO , User.class);

        User user = userRepository.save(userRequest);
        UserEventTopic userEvent = mapper.map(user, UserEventTopic.class);
        log.info("event invoked :{}",userEvent);
        kafkaTemplate.send(UserCreatedTopic, user.getId(), userEvent);

        return ResponseEntity.ok("user created");

    }
}
