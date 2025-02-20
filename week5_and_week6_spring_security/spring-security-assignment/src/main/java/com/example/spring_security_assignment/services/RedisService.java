package com.example.spring_security_assignment.services;

import com.example.spring_security_assignment.exceptions.MultipleUserLoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

//    private final RedisTemplate<Long, String> redisTemplate;
//
//    public void setValue(Long key, String value) {
//        log.debug("Setting token for user {}: {}", key, value);
//        log.info(getValue(key)); // Log the current value (if any)
//
//        // Delete the old session before setting the new one
//        redisTemplate.delete(key);
//
//        // Set the new session value
//        redisTemplate.opsForValue().set(key, value);
//
//        log.debug("Session created for userId: {} with token: {}", key, value);
//    }
//
//    public String getValue(Long key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public void isValidToken(Long id, String token) {
//        String value = getValue(id);
//
//        if (value == null || !value.equals(token)) {
//            throw new MultipleUserLoginException("Only one user can access the application. Your token is invalid. userId: " + id);
//        }
//    }
}
