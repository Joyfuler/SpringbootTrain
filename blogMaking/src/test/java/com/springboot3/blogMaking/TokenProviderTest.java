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
    private TokenProvider provider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만든다.")
    @Test
    void generateToken(){
        User tokenUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());

        String token = provider.generateToken(tokenUser, Duration.ofDays(14));
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        Assertions.assertThat(userId).isEqualTo(tokenUser.getId());
    }

    @DisplayName("valieToken(): 만료된 토큰인 때 유효성 검증 실패하는지 테스트")
    @Test
    public void isValidToken(){
        String token = JwtFactory.builder().expiration
                (new Date(new Date().getTime() - Duration.ofDays(14).toMillis())).build()
                .createToken(jwtProperties);

        boolean result = provider.validToken(token);
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("getAuthentication() : 토큰 정보가 정확할 경우 인증 정보를 가져올 수 있다.")
    @Test
    public void getAuthentication(){
        String userEmail = "user@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        Authentication authentication = provider.getAuthentication(token);
        Assertions.assertThat(((UserDetails) authentication.getPrincipal())
                        .getUsername())
                        .isEqualTo(userEmail);

    }

    @DisplayName("getUserId() : 토큰으로 유저 ID를 가져올 수 있는지 테스트.")
    @Test
    public void getUserId(){
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        Long userIdByToken = provider.getUserId(token);
        Assertions.assertThat(userIdByToken).isEqualTo(userId);
    }



}
