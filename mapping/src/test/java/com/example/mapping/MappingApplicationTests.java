package com.example.mapping;

import com.example.mapping.onetoone.Locker;
import com.example.mapping.onetoone.LockerRepository;
import com.example.mapping.onetoone.Member;
import com.example.mapping.onetoone.MemberRepository;
import com.example.mapping.transaction.Account;
import com.example.mapping.transaction.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

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

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void accountTest(){
		accountRepository.save(new Account(new BigInteger("43094236098346")));
		accountRepository.save(new Account(new BigInteger("1")));


	}

}
