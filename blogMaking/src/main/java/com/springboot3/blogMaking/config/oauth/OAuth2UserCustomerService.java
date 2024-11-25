package com.springboot3.blogMaking.config.oauth;

import com.springboot3.blogMaking.dto.User;
import com.springboot3.blogMaking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserCustomerService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;


    // Oauth 서비스 정보를 기반으로, 사용자의 객체를 불러온다. (이름, 이메일, 프사 등)
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException{
        OAuth2User user = super.loadUser(oAuth2UserRequest);
        saveOrUpdate(user); // 유저가 없는 경우에는 DB에 생성하고, 있다면 갱신.
        return user;
    }

    public User saveOrUpdate(OAuth2User oAuth2User){
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email)
                .map(e -> e.update(name)).
                orElse(User.builder().email(email).nickname(name).build());
        return userRepository.save(user);
    }
}


