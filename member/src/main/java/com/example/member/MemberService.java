package com.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> allList(){
        return memberRepository.allList();
    }

    public String insert(Member member){
        if (memberRepository.insertMember(member) != 0){
            return "잘 들어갔습니다";
        } else {
            return "안 들어갔습니다";
        }
    }

    public String update(Member member){
        if (memberRepository.updateMember(member) != 0){
            return "잘 수정되었습니다.";
        } else {
            return "안 수정되었습니다.";
        }
    }

    public String delete(int id){
        if (memberRepository.deleteMember(id) != 0){
            return "잘 삭제";
        } else {
            return "안 삭제";
        }

    }

    public Member getMemberInfo(int id){
        return memberRepository.getMemberInfo(id);
    }



}
