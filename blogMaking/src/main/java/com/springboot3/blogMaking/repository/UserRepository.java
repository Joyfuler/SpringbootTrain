package com.springboot3.blogMaking.repository;

import com.springboot3.blogMaking.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // 이메일은 unique이므로 사용자 식별 가능.
}
