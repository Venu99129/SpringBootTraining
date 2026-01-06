package com.example.learnKafka.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-random-topic}")
    private String UserRandomTopic;

    @Bean
    public NewTopic userRansomTopic(){
        return new NewTopic(UserRandomTopic, 3, (short) 1);
    }

    @Value("${kafka.topic.user-created-topic}")
    private String UserCreatedTopic;

    @Bean
    public NewTopic userCreatedTopic(){
        return new NewTopic(UserCreatedTopic, 3, (short) 1);
    }

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
