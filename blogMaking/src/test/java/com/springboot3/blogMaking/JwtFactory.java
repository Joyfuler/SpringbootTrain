package com.springboot3.blogMaking;

import com.springboot3.blogMaking.config.jwt.JwtProperties;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Getter
public class JwtFactory {
    private String subject = "test@gmail.com";
    // 테마 - 유저 이메일 정보.
    private Date issuedAt = new Date();
    // 토큰을 발행한 일자 = 현재
    private Date expiration = new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
    // 현재 시간에서 14일 뒤를 만료일로 설정.
    private Map<String, Object> claims = Collections.emptyMap();

    @Builder
    public JwtFactory(String subject, Date issuedAt, Date expiration,
                      Map<String, Object> claims){
        this.subject = (subject != null? subject : this.subject);
        this.issuedAt = (issuedAt != null? issuedAt : this.issuedAt);
        this.expiration = (expiration != null? expiration : this.expiration);
        this.claims = (claims != null ? claims : this.claims);
    }

    public static JwtFactory withDefaultValues(){
        return JwtFactory.builder().build();
    }

    public String createToken(JwtProperties jwtProperties){
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

}
