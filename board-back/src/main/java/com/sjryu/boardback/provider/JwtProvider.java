package com.sjryu.boardback.provider;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    @Value("${secret-key}")
    private String secretKey;

    public String create(String email){
        // 만료시간(현재시간에서 +1시간)
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // jwt 생성
        String jwt = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
            .compact();

        return jwt;

    }

    // 생성된 jwt 검증
    public String validate(String jwt){
        Claims claims = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try{
            claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        return claims.getSubject();

    }

}
