package com.example.mapping.transaction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class TransferForm {
    private Long sender;
    private Long receiver;
    private BigInteger balance;
}
