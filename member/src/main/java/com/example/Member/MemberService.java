package com.example.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public String saveMember(Member m)
	{
		boolean result = memberRepository.insert(m);
		String message = "저장안됐어용";
		if(result)
			message = "저장 됐어용";		
		return message;
	}
	
	public List<Member> getMembers()
	{
		return memberRepository.findAll();
	}
	
	public Member getMemberById(Long id)
	{
		return memberRepository.findById(id);
	}
	
	public String updateMember(Member m)
	{
		boolean result = memberRepository.update(m);
		String msg = "갱신안댐여";
		if(result)
			msg = "갱신함여";		
		return msg;
	}
	
	public String deleteMember(Long id)
	{
		boolean result = memberRepository.delete(id);
		String msg = "삭제안댐";
		if(result)
			msg = "삭제댐";
		return msg;		
	}
	
}
