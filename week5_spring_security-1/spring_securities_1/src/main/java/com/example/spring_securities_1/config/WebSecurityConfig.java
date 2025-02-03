package com.example.spring_securities_1.config;

import com.example.spring_securities_1.filters.JwtAuthFilter;
import com.example.spring_securities_1.handlers.OAuthSuccessHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.spring_securities_1.entities.enums.Permission.*;
import static com.example.spring_securities_1.entities.enums.Role.ADMIN;
import static com.example.spring_securities_1.entities.enums.Role.CREATOR;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuthSuccessHandler authSuccessHandler;

    private static final String[] publicRoutes = {
            "/auth/**", "/home.html","/actuator"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(publicRoutes).permitAll()
                                .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyRole(ADMIN.name(),CREATOR.name())
                                .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyAuthority(POST_CREATE.name(),USER_CREATE.name())
                                .requestMatchers(HttpMethod.DELETE,"/posts/**").hasAuthority(POST_DELETE.name())
                                .requestMatchers(HttpMethod.DELETE,"/posts/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"/posts/**").hasAnyRole(ADMIN.name(), CREATOR.name())
                                .requestMatchers(HttpMethod.PUT,"/post/**").hasAuthority(POST_UPDATE.name())
                                // Ensure /auth/signup is explicitly permitted
//                                .requestMatchers("/posts/**").hasAnyRole("ADMIN")  // Restrict /posts/** to ADMIN role
                                .anyRequest().authenticated()  // Require authentication for any other request
                )
//                .exceptionHandling(exceptions ->
//                        exceptions
//                                .accessDeniedHandler(((request, response, accessDeniedException) -> {
//                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                                        response.getWriter().write("Access Denied: Insufficient Role");
//                                })))

                .csrf(csrf -> csrf.disable())  // Disabling CSRF
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Stateless session management
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2Config-> oauth2Config
                        .failureUrl("/login?error=true")
                        .successHandler(authSuccessHandler)
                )
                .build();
    }
//
//    @Bean
//    UserDetailsService inMemoryUserDetailsManager(){
//        UserDetails normalUser = User
//                .withUsername("venumadhav")
//                .password(passwordEncoder().encode("madhav"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
