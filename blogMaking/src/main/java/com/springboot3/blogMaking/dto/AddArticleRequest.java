package com.springboot3.blogMaking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;


    // Builder 패턴을 활용해 DTO를 엔티티로 만들어줌.
    // 못생긴 생성자 대신 무엇을 넣을 지 직관적으로 알 수 있게 해주는 패턴임.
    public Article toEntity(String author){
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }



}
