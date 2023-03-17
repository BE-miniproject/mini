package com.sparta.mini.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request의 header에서 토큰을 가져옴
        String token = jwtUtil.resolveToken(request);
        // token 유효성 검사 token null 일때?
        if (token != null) {
            if (!jwtUtil.validateToken(token)){
                throw new JwtException("토큰이 유효하지 않습니다.");
            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            // 인증 객체 생성
            setAuthentication(info.getSubject());
        }
        // 다음 필터로 넘김
        filterChain.doFilter(request,response);
    }

    //    SecurityContextHolder안에 인증객체 넣음
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

}