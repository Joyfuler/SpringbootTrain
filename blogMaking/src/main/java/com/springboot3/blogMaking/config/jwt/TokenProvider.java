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
// 서비스 코드?!
public class TokenProvider {
    private final JwtProperties jwtProperties;

    // 토큰 생성 메서드. 패러미터로 만료되는 시간과 유저 정보를 받는다.
    // 이후 set ~ 메서드에 이 값을 다른 곳에 넣을 수 있도록 한다.
    // 암호화는 비공개되는 값을 HS256 방식으로 암호화한다.
    public String generateToken(User user, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    private String makeToken(Date expireDate, User user){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // 잘못된 토큰이 아닌지 검증한다.
    // 만일 에러 발생시 유효하지 않은 토큰이므로 false 반환.
    // 검증 후 문제가 없다면 true를 반환한다.
    public boolean validToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // 토큰을 기반으로 해당 유저의 정보를 리턴한다.
    // User의 경우는 스프링 시큐리티에서 제공하는 User 클래스를 임포트해 사용할것.
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken
                (new org.springframework.security.core.userdetails.User
                        (claims.getSubject(), "", authorities),
                        token, authorities);
    }

    public Long getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

}

