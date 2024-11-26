package com.springboot3.blogMaking.service;

import com.springboot3.blogMaking.dto.Article;
import com.springboot3.blogMaking.dto.AddArticleRequest;
import com.springboot3.blogMaking.dto.UpdateArticleRequest;
import com.springboot3.blogMaking.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest addArticleRequest, String userName){
        return blogRepository.save(addArticleRequest.toEntity(userName));
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id));
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }


    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found :" + id));

        article.update(request.getTitle(), request.getContent()); // 받아온 값으로 업데이트.

        return article;

    }
}
