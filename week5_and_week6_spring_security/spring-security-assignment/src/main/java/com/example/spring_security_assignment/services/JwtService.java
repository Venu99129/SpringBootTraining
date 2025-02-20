package com.example.spring_security_assignment.services;

import com.example.spring_security_assignment.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${spring.secretKey}")
    private String secretKey;
    private final SessionService sessionService;

    private SecretKey getToken(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        String accessToken =  Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*3))
                .signWith(getToken())
                .compact();

        sessionService.createSession(user.getId(),accessToken);
        return accessToken;
    }

    public String generateRefreshToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+  1000L  *60*60*24*30*6))
                .signWith(getToken())
                .compact();
    }

    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getToken())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }

//    public Long getUserIdFromExpiredToken(String token){
//        Claims claims = Jwts.parser()
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//        return Long.valueOf(claims.getSubject());
//    }
}
