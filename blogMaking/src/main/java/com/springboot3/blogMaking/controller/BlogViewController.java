package com.springboot3.blogMaking.controller;

import com.springboot3.blogMaking.dto.Article;
import com.springboot3.blogMaking.service.BlogService;
import com.springboot3.blogMaking.dto.ArticleListViewResponse;
import com.springboot3.blogMaking.dto.ArticleViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream().map(ArticleListViewResponse::new).toList();
        // List<Article> 로 반환한 findAll을 다시 stream 메소드로 타입에 맞춰 고친 후 리스트 반환.
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if (id == null){
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }

}
