package com.example.member;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository {
    List<Member> allList();
    int insertMember(Member member);
    int updateMember(Member member);
    int deleteMember(int id);
    Member getMemberInfo(int id);
}
