package com.springboot3.blogMaking.repository;

import com.springboot3.blogMaking.dto.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
}
