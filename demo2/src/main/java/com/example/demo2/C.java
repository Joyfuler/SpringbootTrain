package com.example.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class C {

    @Bean
    public D d(){
        return new D();
    }


    public void mC(){
        System.out.println("C의 mC() 호출함.");
    }
}
