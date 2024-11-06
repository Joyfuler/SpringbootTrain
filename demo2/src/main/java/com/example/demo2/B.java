package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class B {

    @Autowired
    private A a;
    // A a = new A();
    // a.mA(); 와 같이 사용시 강한 결합도가 됨. (B 클래스는 A 클래스 없이 실행불가)


    public B(A a){
        this.a = a;
    }

    public void mB(){
        a.mA(); // 약한결합 예시. 매개변수로만 들어와서 사용함.
        System.out.println("mB() 호출함");
    }
}
