package com.springboot3.blogMaking.service;

import com.springboot3.blogMaking.dto.Article;
import com.springboot3.blogMaking.dto.AddArticleRequest;
import com.springboot3.blogMaking.dto.UpdateArticleRequest;
import com.springboot3.blogMaking.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id));
    }

    public void delete(Long id){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번의 글을 찾지 못했습니다."));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }


    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found :" + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent()); // 받아온 값으로 업데이트.

        return article;

    }

    private static void authorizeArticleAuthor(Article article){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!article.getAuthor().equals(userName)){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }

    public Article save(AddArticleRequest addArticleRequest, String userName){
        return blogRepository.save(addArticleRequest.toEntity(userName));
    }

}
