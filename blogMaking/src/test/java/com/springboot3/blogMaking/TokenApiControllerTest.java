package com.springboot3.blogMaking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot3.blogMaking.config.jwt.JwtProperties;
import com.springboot3.blogMaking.domain.RefreshToken;
import com.springboot3.blogMaking.dto.CreateAccessTokenRequest;
import com.springboot3.blogMaking.dto.User;
import com.springboot3.blogMaking.repository.RefreshTokenRepository;
import com.springboot3.blogMaking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    public JwtProperties jwtProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach // 매번 실행전에 실행
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        userRepository.deleteAll();
    }

    @DisplayName("createNewAccessToken: 새로운 액세스 토큰을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception{
        final String url = "/api/token";

        // 리프레시 토큰을 발급할 유저를 생성하고, 이를 데이터베이스에 저장한다.
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());


        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);

        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        CreateAccessTokenRequest createAccessTokenRequest = new CreateAccessTokenRequest();
        createAccessTokenRequest.setRefreshToken(refreshToken);
        final String requestBody = objectMapper.writeValueAsString(createAccessTokenRequest);

        // 토큰 추가 api에 요청을 보낸다. 타입은 json 타입이며, 요청 본문으로 함께 보낸다.
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // 응답 코드가 201 (created) 인지 확인하고, 액세스 토큰이 비어있지 않은지 확인한다.
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").isNotEmpty());
    }
}
