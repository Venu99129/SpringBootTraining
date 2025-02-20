package com.example.spring_security_assignment.filter;

import com.example.spring_security_assignment.entities.User;
import com.example.spring_security_assignment.services.JwtService;
import com.example.spring_security_assignment.services.RedisService;
import com.example.spring_security_assignment.services.SessionService;
import com.example.spring_security_assignment.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    //private final RedisService redisService;
    private final SessionService sessionService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader("Authorization");

            if (bearerToken == null || !bearerToken.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = bearerToken.split("Bearer ")[1];

            Long userId = jwtService.getUserIdFromToken(token);

            log.debug("JwtFilter reached. Bearer token: {}", token);

           // redisService.isValidToken(userId,token);
            sessionService.isValidSession(userId,token);
            
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.loadUserByUserId(userId);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);
            }
        }catch (Exception ex){
            handlerExceptionResolver.resolveException(request,response,null,ex);
        }
    }
}
