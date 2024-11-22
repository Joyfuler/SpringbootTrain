package com.springboot3.blogMaking.service;

import com.springboot3.blogMaking.domain.RefreshToken;
import com.springboot3.blogMaking.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String token){
        return refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 토큰입니다!"));
    }
}
