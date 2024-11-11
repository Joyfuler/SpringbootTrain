package com.example.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Member {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Date regdate;

}
