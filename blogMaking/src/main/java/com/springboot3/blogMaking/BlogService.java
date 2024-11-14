package com.springboot3.blogMaking;

import com.springboot3.blogMaking.dto.AddArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest addArticleRequest){
        return blogRepository.save(addArticleRequest.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

}
