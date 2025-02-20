package com.example.week_6_assignment.configs;

import com.example.week_6_assignment.auth.AuditAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class appConfig {

    @Bean
    AuditorAware<String> auditorAware(){
        return new AuditAwareImpl();
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
