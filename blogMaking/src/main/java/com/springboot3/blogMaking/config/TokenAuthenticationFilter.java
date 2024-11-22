package com.springboot3.blogMaking.config;

import com.springboot3.blogMaking.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "나토큰";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 요청한 헤더의 Authorization 키의 값 조회
        String token = getAccessToken(authorizationHeader);
    }

    private String getAccessToken(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            return authorizationHeader.substring((TOKEN_PREFIX.length()));
            // 토큰 접두사인 "나토큰" 을 제외한 나머지 부분을 가져온다.
            // 만일 "나토큰" 으로 시작하지 않는 경우에는 null을 반환한다.
        } else {
            return null;
        }
    }



}
