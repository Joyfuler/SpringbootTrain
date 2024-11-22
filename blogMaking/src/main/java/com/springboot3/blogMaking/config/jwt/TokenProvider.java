package com.springboot3.blogMaking.config.jwt;

import com.springboot3.blogMaking.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredDuration) {
        Date nowDate = new Date();
        return makeToken(new Date(nowDate.getTime() + expiredDuration.toMillis()), user);
    }

    public String makeToken(Date expire, User user){
        Date nowDate = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더타입.
                .setIssuer(jwtProperties.getIssuer()) // 발급자
                .setIssuedAt(nowDate) // 발급된 시각
                .setExpiration(expire) // 만료되는 일시
                .setSubject(user.getEmail()) // 토큰의 주제(사용자의 이메일을 테마로 사용)
                .claim("id", user.getId()) // 클레임(정보)로 id를 추가.
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                //HS256 알고리즘과 비밀키를 사용하여 암호화.
                .compact(); // 문자열로 변환 후 반환. (String type)
    }

    public boolean ValidToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
                    // 비밀키를 가져와 토큰 파싱. 유효하지 않은 경우 오류가 발생한다.
            return true;

        } catch (Exception e){
            return false;
        }
    }
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_USER"));
        // 기본 권한을 ROLE_USER로 설정.

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities), token, authorities);
    }

    // 토큰에서 유저 정보를 리턴.
    public Long getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
        // id값을 가져오고, 타입은 Long으로 설정한다.
    }

    // 토큰 내의 클레임 데이터를 추출한다. 클레임 본문을 반환.
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
