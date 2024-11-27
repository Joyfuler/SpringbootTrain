package com.springboot3.blogMaking.config;

import com.springboot3.blogMaking.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userService;

    @Bean
    public WebSecurityCustomizer configure(){
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
        // 시큐리티 기능을 사용하지 않도록 설정함.
    }

    // 특정 http 요청에 security 기능을 부여함. (로그인 / 회원가입 / 로그아웃 등 인증과 인가 처리에 사용)
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user"))
                                .permitAll()
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        // 로그인은 form 기반의 로그인 설정을 진행. loginPage는 /login, 로그인 성공시 articles로 이동함.
                .defaultSuccessUrl("/articles")
                ).logout(logout -> logout.logoutSuccessUrl("/login")
                        // 로그아웃 성공시 다시 로그인 페이지로 이동한다. 또한 session을 모두 없애도록 한다.
                        .invalidateHttpSession(true)
                ).csrf(AbstractHttpConfigurer::disable)
                // csrf 공격을 방지하는 코드이나 실습용으로 disable 처리하였음.
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception{
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);

        // 사용자 서비스 설정. --> 정보를 가져올 서비스 클래스 설정. userDetails Service를 상속받은 클래스여야 함.
        // passwordEncoder : 비밀번호 암호를 위한 인코더 설정.
        return new ProviderManager(provider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
