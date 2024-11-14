package com.example.orm.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController // JSON을 리턴하는 controller
public class MemberRestController {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberRestController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/listToJson")
    public List<Member> list(){
        List<Member> member = memberRepository.findAll();
        return member;
    }

    @GetMapping("/detailToJson")
    public Optional<Member> memberInfo(@RequestParam("id") Long id){
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }
}
