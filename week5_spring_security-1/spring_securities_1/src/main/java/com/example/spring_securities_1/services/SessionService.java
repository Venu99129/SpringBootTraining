package com.example.spring_securities_1.services;


import com.example.spring_securities_1.entities.SessionEntity;
import com.example.spring_securities_1.entities.User;
import com.example.spring_securities_1.exceptions.MultipleUserLoginException;
import com.example.spring_securities_1.exceptions.ResourceNotFoundException;
import com.example.spring_securities_1.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    // Validate if the token for the given user is valid
    public void isValidSession(String refreshToken) {
        SessionEntity session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("data not found with refreshToken: " + refreshToken));
        log.debug("session details : {}", session);
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

    // Create a new session for the user (remove old session if exists)
    public void createSession(User user, String refreshToken) {

        log.debug("Setting token for user {}: {}", user, refreshToken);
        // Check if a session already exists for the user
        List<SessionEntity> existingSessions = sessionRepository.findByUserOrderByLastUsedAtAsc(user);

        log.debug("{}", existingSessions.isEmpty());
        // Invalidate the old session by removing it
//        existingSession.ifPresent(sessionRepository::delete);
        log.debug("before deleting");
        if(existingSessions.size() >= SESSION_LIMIT){
//            existingSessions.sort(Comparator.comparing(SessionEntity::getLastUsedAt));

            SessionEntity lastRecentlyUsedSession = existingSessions.getFirst();
            sessionRepository.delete(lastRecentlyUsedSession);
        }

        log.debug("out side of if");
        // Create and save the new session
        SessionEntity newSession = SessionEntity.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();

        SessionEntity entity= sessionRepository.save(newSession);
        log.debug("created session entity: {}",entity);
    }

    // Remove session by user ID (log out the user)
}
