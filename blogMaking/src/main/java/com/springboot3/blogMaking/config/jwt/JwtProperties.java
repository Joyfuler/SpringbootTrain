package com.springboot3.blogMaking.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt") // 프로퍼티를 가져올 때 사용하는 어노테이션.
public class JwtProperties {
    private String issuer;
    private String secretKey;

}
