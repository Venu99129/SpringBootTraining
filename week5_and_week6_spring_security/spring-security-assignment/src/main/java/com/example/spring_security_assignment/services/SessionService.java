package com.example.spring_security_assignment.services;

import com.example.spring_security_assignment.entities.SessionEntity;
import com.example.spring_security_assignment.exceptions.MultipleUserLoginException;
import com.example.spring_security_assignment.exceptions.ResourceNotFoundException;
import com.example.spring_security_assignment.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;

    // Validate if the token for the given user is valid
    @Transactional
    public void isValidSession(Long userId, String token) {
        SessionEntity session = sessionRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("data not found with userId: "+userId));
        log.debug("session details : {}",session);
        if(session != null && !session.getToken().equals(token))
            throw  new MultipleUserLoginException("multiple users cannot login at a time with userId : "+userId);
    }

    // Create a new session for the user (remove old session if exists)
    public void createSession(Long userId, String token) {

        log.debug("Setting token for user {}: {}", userId, token);
        // Check if a session already exists for the user
        Optional<SessionEntity> existingSession = sessionRepository.findByUserId(userId);

        log.debug("{}", existingSession.isEmpty());
        // Invalidate the old session by removing it
//        existingSession.ifPresent(sessionRepository::delete);
        log.debug("before deleting");
        if(!existingSession.isEmpty()){
            sessionRepository.deleteById(existingSession.get().getId());
        }

        log.debug("out side of if");
        // Create and save the new session
        SessionEntity newSession = SessionEntity.builder()
                .userId(userId)
                .token(token)
                .build();
        SessionEntity entity= sessionRepository.save(newSession);
        log.debug("created session entity: {}",entity);
    }

    // Remove session by user ID (log out the user)
    @Transactional
    public void removeSession(Long userId) {
        Optional<SessionEntity> session = sessionRepository.findByUserId(userId);

        session.ifPresent(sessionRepository::delete);
    }
}
