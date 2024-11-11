package com.example.memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 스프링부트에서의 main문과 같은 기능. 어노테이션을 추가하여 기본 설정을 할 수 있음.
@SpringBootApplication // @configuration을 상속한 어노테이션.
@ComponentScan // @component라는 어노테이션을 가진 클래스를 찾아 빈으로 등록한다.
@EnableAutoConfiguration
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }

}
