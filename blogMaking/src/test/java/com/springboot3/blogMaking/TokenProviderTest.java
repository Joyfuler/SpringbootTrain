package com.springboot3.blogMaking;

import com.springboot3.blogMaking.config.jwt.JwtProperties;
import com.springboot3.blogMaking.config.jwt.TokenProvider;
import com.springboot3.blogMaking.dto.User;
import com.springboot3.blogMaking.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken() : 유저 정보와 만료 기간을 전달해 토큰")
    @Test
    void generateToken(){
        // 테스트용 더미데이터 1개 생성
        User testUser = userRepository.save(User.builder()
                .email("user.gmail.com")
                .password("test")
                .build());

        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));
        // 만료일 14일

        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        Assertions.assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("validToken() : 만료된 토큰시 유효성 검증에 실패")
    @Test
    void isValidToken(){
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build().createToken(jwtProperties);

        boolean result = tokenProvider.ValidToken(token);
        Assertions.assertThat(result).isFalse();
        // 만료일을 현재보다 이전으로 설정.
    }

    @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보를 가져온다.")
    @Test
    void getAuthentication(){
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        Authentication authentication = tokenProvider.getAuthentication(token);
        Assertions.assertThat(((UserDetails)authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    @DisplayName("getUserId(): 토큰으로 유저 ID 가져오기")
    @Test
    void getUserId(){
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        Long userIdByToken = tokenProvider.getUserId(token);
        Assertions.assertThat(userIdByToken).isEqualTo(userId);
    }


}
