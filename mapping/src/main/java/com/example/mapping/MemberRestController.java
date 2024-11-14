package com.example.mapping;

import com.example.mapping.onetoone.Member;
import com.example.mapping.onetoone.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberRestController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/members")
    public List<Member> memberList(){
        return memberRepository.findAll();
    }
}
