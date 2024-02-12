package com.sjryu.boardback.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sjryu.boardback.provider.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
        
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
             // request 에서 token 꺼내옴
        String token = parseBearerTokken(request);

        // 검증
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // token 에서 email(사용자 아이디)을 꺼내옴
        String email = jwtProvider.validate(token);
        
        // 검증
        if (email == null){
            filterChain.doFilter(request, response);
            return;
        }

        // user 정보가 담긴 객체
        AbstractAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
            // 인증요청에 대한 세부정보 설정
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        
        // 토큰 정보를 context에 저장
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);

        SecurityContextHolder.setContext(securityContext);
            
        }  catch (Exception exception){
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
        
    }

    private String parseBearerTokken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        // authorization 필드를 헤더에서 가져옴
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        //가져온 authorization이 Bearer가 맞는지 확인
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        // 위의 조건을 통과했다면 토큰 반환
        String token = authorization.substring(7);
        return token;
    }
}
