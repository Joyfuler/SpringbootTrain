package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class D {

    @Autowired
    private C c;

    @Bean
    public void mD(){
        c.mC();
    }


}
