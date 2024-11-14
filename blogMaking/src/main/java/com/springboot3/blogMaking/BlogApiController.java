package com.springboot3.blogMaking;

import com.springboot3.blogMaking.dto.AddArticleRequest;
import com.springboot3.blogMaking.dto.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article newArticle = blogService.save(request);

        // HTTP에 응답할 때 리턴되는 값을 반환. 잘 되면 200 ~ 201. 400은 요청 값 오류,
        // 404는 페이지 없음, 500은 서버 문제 (꺼짐 등)

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

}
