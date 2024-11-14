package com.example.mapping;

import com.example.mapping.onetoone.Locker;
import com.example.mapping.onetoone.LockerRepository;
import com.example.mapping.onetoone.Member;
import com.example.mapping.onetoone.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MappingApplicationTests {

	@Autowired
	LockerRepository lockerRepository;
	@Autowired
	MemberRepository memberRepository;

	@Test
	void contextLoads() {

		Member member = new Member();
		member.setName("m1");
		memberRepository.save(member);

		Locker locker = new Locker();
		locker.setLoc("1층");
		locker.setMember(member);
		lockerRepository.save(locker);

		// 2번째

		Member member2 = new Member();
		member2.setName("m2");
		memberRepository.save(member2);

		Locker locker2 = new Locker();
		locker2.setLoc("2층");
		locker2.setMember(member2);
		lockerRepository.save(locker2);

//		Assertions.assertThat(lockerRepository.findById(1L).get().getLoc()).isEqualTo("1층");
//		Assertions.assertThat(lockerRepository.findById(2L).get().getLoc()).isEqualTo("2층");
//		Assertions.assertThat(lockerRepository.findById(1L).get().getMember().getName()).isEqualTo("m1");
	}


//	@Test
//	void memberMapping(){
//		Member member = new Member();
//		member.setId(1L);
//		member.setName("홍길동");
//		memberRepository.save(member);
//
//		Locker locker = new Locker();
//		locker.setId(1L);
//		locker.setLoc("5층");
//		locker.setMember(member);
//		lockerRepository.save(locker);
//
//		// Assertions.assertThat(lockerRepository.findById(1L).get().getLoc()).isEqualTo("5층");
//
//
//	}

}
