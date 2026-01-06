package com.example.notification_service.consumer;

import com.example.learnKafka.user_service.event.UserEventTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "User-Random-Topic")
    public void handleUserRandomTopic1(String message){
        log.info("message received1 :{}",message);
    }

    @KafkaListener(topics = "User-Random-Topic")
    public void handleUserRandomTopic2(String message){
        log.info("message received2 :{}",message);
    }

    @KafkaListener(topics = "User_created-Topic")
    public void handleUserCratedTopic(UserEventTopic userEvent){
        log.info("message received UserCratedTopic: {}",userEvent.toString());
    }
}
