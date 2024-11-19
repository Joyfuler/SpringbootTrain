package com.springboot3.blogMaking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot3.blogMaking.dto.AddArticleRequest;
import com.springboot3.blogMaking.dto.Article;
import com.springboot3.blogMaking.dto.UpdateArticleRequest;
import com.springboot3.blogMaking.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
    // Java 객체를 Json Object로 변환하는 메소드.

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void setMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest request = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(request);

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(MockMvcResultMatchers.status().isCreated());
        List<Article> articles = blogRepository.findAll(); // 전체 기사 가져옴.

        Assertions.assertThat(articles.size()).isEqualTo(1);
        Assertions.assertThat(articles.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(articles.get(0).getContent()).isEqualTo(content);
        // 현재 들어있는 것과 같은지를 테스트함.
    }

    @DisplayName("findAllArticles: 블로그 글 목록 조회 성공")
    @Test
    public void findAllArticles() throws Exception {
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(title));
    }


    @DisplayName("findArticle : 블로그 글 조회 성공")
    @Test
    public void findArticle() throws Exception{
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                        .title(title)
                        .content(content)
                        .build());

        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title));
    }

    @DisplayName("deleteArticle : 블로그 글 삭제에 성공한다")
    @Test
    public void deleteArticle() throws Exception {
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        mockMvc.perform(delete(url, savedArticle.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Article> articles = blogRepository.findAll();

        Assertions.assertThat(articles).isEmpty();
    }

    @DisplayName("updateArticle: 블로그 글 수정에 성공.")
    @Test
    public void updateArticle() throws Exception{
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                        .title(title)
                        .content(content)
                        .build());

        final String newTitle = "새로운타이틀";
        final String newContent = "내용을 변경해볼까?";

        UpdateArticleRequest requestObj = new UpdateArticleRequest(newTitle, newContent);

        // 수정이므로 put 메소드. 주소는 url + id가 들어가며 수정할 내용을 집어넣는다.
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestObj)));

        // 세팅이 완료되었으면 결과를 확인하고 assertThat테스트를 진행한다.

        result.andExpect(MockMvcResultMatchers.status().isOk());

        Article article = blogRepository.findById(savedArticle.getId()).get();
        // Optional<Article> 을 get으로 가져와 Article 타입으로 변환가능.

        Assertions.assertThat(article.getTitle()).isEqualTo(newTitle);
        Assertions.assertThat(article.getContent()).isEqualTo(newContent);

    }

}

