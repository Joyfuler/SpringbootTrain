package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JunitTest {
    @Test
    public void junitTest(){
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        assertThat(number1).isGreaterThan(0);
        assertThat(number2).isEqualTo(0);
        assertThat(number3).isLessThan(0);

        assertThat(number1).isGreaterThan(number2);
        assertThat(number3).isLessThan(number2);
    }


}
