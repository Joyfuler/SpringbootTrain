package com.example.mapping.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo; // 계좌번호
    private BigInteger balance; // 문자열 형식이므로 무한대로 들어감.

    public Account(Long accountNo, BigInteger balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public Account(BigInteger balance) {
        this.balance = balance;
    }

    public Account() {}
}
