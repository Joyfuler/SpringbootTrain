package com.springboot3.blogMaking.service;

import com.springboot3.blogMaking.dto.AddUserRequest;
import com.springboot3.blogMaking.dto.User;
import com.springboot3.blogMaking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    // 회원 정보를 추가하는 메서드. 회원가입 등에서 사용
    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 저장할 때 password는 암호화를 진행하여 저장한다.
    public Long save(AddUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저입니다."));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저입니다."));
    }
}
