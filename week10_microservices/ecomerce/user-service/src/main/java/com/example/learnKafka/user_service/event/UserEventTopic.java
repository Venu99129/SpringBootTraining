package com.example.learnKafka.user_service.event;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserEventTopic {

    private Long id;
    private String name;
}
