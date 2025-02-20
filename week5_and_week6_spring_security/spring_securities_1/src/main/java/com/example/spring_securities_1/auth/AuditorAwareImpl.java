package com.example.spring_securities_1.auth;

import com.example.spring_securities_1.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        get security context
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.of(user.getId().toString());
        }
        catch (Exception ex){
            log.info(ex.getLocalizedMessage());
                return Optional.of("Higher admin");

        }
//        get authentication
//        get the principle
//        get the username

    }
}
