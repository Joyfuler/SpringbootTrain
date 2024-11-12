package com.example.orm.memo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//타입과 pk의 타입을 가진 jpaRepository 선언
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // 아무 코드도 존재하지 않지만 항상 사용하는 전체목록 / 입력 / 수정 등이 구현되어 있음.
    // 따라서 평소 사용하지 않는 코드를 추가하면 된다.
    // 그 외 검색 방법 중 jpa에 포함되어 있는 것이 있다면, 해당하는 이름만 맞춰 메소드를 추가하면 된다.
    // 만일 쿼리에 들어가는 와일드카드 패러미터가 여러개라면 괄호 안에 해당 패러미터 갯수만큼 입력해주면 된다.

    List<Memo> findByContentContaining(String content);
    Page<Memo> findAll(Pageable pageable);
    // 내용이 완전히 같은 content만 검색해서 가져온다.
    // import 주의! --> ~~domain

}
