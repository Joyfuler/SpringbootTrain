package com.example.orm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrmApplicationTests {

	@Autowired
	private MemoRepository memoRepository;

	@Test
	void contextLoads() {
		// insert와 동일한 효과
		Memo memo = new Memo();
		memo.setContent("Hi!");
		memoRepository.save(memo);

		Memo memo2 = new Memo();
		memo2.setContent("안녕!");
		memoRepository.save(memo2);

		memo2.setContent("안녕2!");
		memoRepository.save(memo2); // update와 동일? 이미 있다면 update로 판단함.

		assertEquals("안녕2!", memo2.getContent());

		List<Memo> memos = memoRepository.findAll();
		assertEquals("안녕2!", memos.get(memos.size()-1).getContent());
		assertEquals(2, memos.size());

		// 일반적으로 Memo memo와 같은 식으로 받아왔으나, 이 경우에는 Optional<타입> 으로 받아야 함.
		// 테스팅할 때는 있는 값 / 없는 값 모두 대입해볼것.
		Optional<Memo> memo3 = memoRepository.findById(1L);

		assertEquals(true, memo3.isPresent());


		if (memo3.isPresent()){
			assertEquals("Hi!", memo3.get().getContent());
		}

		assertEquals(2, memoRepository.count());
		memoRepository.deleteById(1L);
		assertEquals(1, memoRepository.count());
		memoRepository.deleteAll();
		assertEquals(0, memoRepository.count());
	}

}
