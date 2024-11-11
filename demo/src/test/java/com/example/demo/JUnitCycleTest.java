package com.example.demo;

import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll // 코드가 실행되기 전에 단 한번만 시행되는 테스트.
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }

    @BeforeEach // 각 테스트별로 한번씩 실행하는 코드.
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){ //테스트 1번
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }

    @AfterAll //모든 테스트가 끝나기 직전에 한 번 실행함.
    static void afterAll(){
        System.out.println("@AfterAll");
    }

    @AfterEach // 각 테스트가 끝날 때 1회씩 실행함.
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
