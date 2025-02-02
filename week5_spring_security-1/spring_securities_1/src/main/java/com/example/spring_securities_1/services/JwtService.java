package com.example.spring_securities_1.services;

import com.example.spring_securities_1.entities.User;
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

    @Value("${jwt.secretKey}")
    private String JwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(JwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", user.getRoles().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*3))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User user){

        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+  1000L  *60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }

}
