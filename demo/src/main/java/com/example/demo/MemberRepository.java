package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> allList();
//    public int insertMember(Member member);
//    public int updateMember(Member member);
//    public int deleteMember(int id);
//    public Member getMemberInfo(int id);
}
