package com.example.demo2;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration // Bean 생성시 필요. 만일 선언x시 컴파일은 되더라도 실제로 bean등록안됨
@RequiredArgsConstructor
@AllArgsConstructor
@Component
// Data 어노테이션 하나로 get / set / args / tostring을 한번에 해결가능. (noargs는 비포함)
public class A {
    private String str;
    final int max_count = 10;
    // RequiredArgsConstructor : 상수로만 이루어진 생성자를 만들때 사용.
    // 만일 수를 대입하지 않고 초기화하는 경우 기본적으로 에러 발생.
    // RequiredArgsConstructor 어노테이션을 선언하는 경우에는 에러가 발생하지 않음

    // public A(){}
    // 이미 어노테이션을 통해 매개변수 없는 생성자를 생성했으므로, 중복하여 만드는 경우 에러 발생.


    public void mA(){
        System.out.println("mA() 호출함");
    }

    // Bean은 아무곳에서나 생성 불가. 클래스 위에 @Configuration 어노테이션을 붙여야만 가능함.




}
