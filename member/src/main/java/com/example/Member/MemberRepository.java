package com.example.Member;

import java.util.List;

public interface MemberRepository {
	boolean insert(Member m);
	List<Member> findAll();
	Member findById(Long id);
	boolean update(Member m);
	boolean delete(Long id);
}
