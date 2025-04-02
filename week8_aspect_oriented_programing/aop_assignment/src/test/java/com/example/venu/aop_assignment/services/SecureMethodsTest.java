package com.example.venu.aop_assignment.services;

import com.example.venu.aop_assignment.entities.User;
import com.example.venu.aop_assignment.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SecureMethodsTest {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecureMethods secureMethods;

    @Test
    public void testSecuredMethod(){
        User user = User.builder()
                .name("venu")
                .email("venumadhav@gmail.com")
                .roles("ADMIN")
                .password("asdf123")
                .build();

        user = userRepository.save(user);
        String jwtToken = jwtService.generateAccessToken(user);
        secureMethods.secureMethod1(jwtToken);

    }

    @Test
    public void testSecuredMethodFailure(){
        User user = User.builder()
                .name("anand")
                .email("anand@gmail.com")
                .roles("USER")
                .password("asdf123")
                .build();

        user = userRepository.save(user);
        String jwtToken = jwtService.generateAccessToken(user);
        secureMethods.secureMethod1(jwtToken);

    }


}