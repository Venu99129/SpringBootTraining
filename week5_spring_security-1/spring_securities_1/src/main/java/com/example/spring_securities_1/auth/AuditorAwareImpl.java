package com.example.spring_securities_1.auth;

import com.example.spring_securities_1.entities.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        get security context
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        get authentication
//        get the principle
//        get the username
        return Optional.of(user.getId().toString());
    }
}
