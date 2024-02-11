package com.sjryu.boardback.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    
    private String secretKey = "S3cr3tK3y";

    public String create(String email){
        // 만료시간(현재시간에서 +1시간)
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // jwt 생성
        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
            .compact();

        return jwt;

    }

    // 생성된 jwt 검증
    public String validate(String jwt){
        Claims claims = null;

        try{
            claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(jwt).getBody();
        }
        catch (Exception exception){

        }

        return claims.getSubject();

    }

}
