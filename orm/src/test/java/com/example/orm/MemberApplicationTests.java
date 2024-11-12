package com.example.orm;

import com.example.orm.member.Member;
import com.example.orm.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class MemberApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test(){
        Member member = new Member();
        //Optional<Member> member = memberRepository.findById(11L);
        //member.get().setEmail("example7@example.com");
        //member.get().setPassword("password1");
        //member.get().setUsername("홍길동");
        //member.get().setRegDate(Date.valueOf("2020-11-10"));
        //member.get().setUpdateDate(Date.valueOf(LocalDate.now()));
        //memberRepository.save(member.get());

        member.setEmail("example10@example.com");
        member.setPassword("password1");
        member.setUsername("홍길동");
        memberRepository.save(member);

        // 중복된이메일되는지 확인
//        Member member2 = new Member();
//        member2.setEmail("example5@example.com");
//        member2.setPassword("password2");
//        member2.setUsername("홍길동");
//        member2.setRegDate(Date.valueOf("2020-11-10"));
//        member2.setUpdateDate(Date.valueOf("2024-11-12"));
//        memberRepository.save(member2);

        // 회원정보수정
        member.setEmail("example9@example.com");
        member.setPassword("password3");
        member.setUsername("장길산");
        member.setUpdateDate(Date.valueOf(LocalDate.now()));
        memberRepository.save(member);

        // memberRepository.deleteById(memberRepository.count()-1);

    }

}
