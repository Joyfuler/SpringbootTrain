package com.springboot3.blogMaking.service;

import com.springboot3.blogMaking.config.jwt.TokenProvider;
import com.springboot3.blogMaking.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createAccessToken(String refreshToken){
        if (!tokenProvider.ValidToken(refreshToken)){
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
