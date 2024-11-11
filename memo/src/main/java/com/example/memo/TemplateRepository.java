package com.example.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateRepository implements MemoRepository{

    //@Autowired
    private final JdbcTemplate jdbcTemplate;
    // 상수이므로 반드시 값 대입 필요. 생성자로 객체변수의 값을 해결하므로
    // @Autowired 불필요.

    public TemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Memo> allList() {
        return jdbcTemplate.query("SELECT * FROM MEMO ORDER BY ID DESC", new BeanPropertyRowMapper<Memo>(Memo.class));
    }

    @Override
    public int insertMemo(Memo memo) {
        return jdbcTemplate.update("INSERT INTO memo (content) values (?)",
                memo.getContent());
        // JDBC를 간소화. jdbcTemplate를 사용하여 sql과 패러미터에 들어갈 값만 입력하면 됨.
    }

    @Override
    public int updateMemo(Memo memo) {
        return jdbcTemplate.update("UPDATE MEMO SET CONTENT = ? WHERE ID = ?", memo.getContent(), memo.getId());
    }

    @Override
    public int deleteMemo(int idx) {
        return jdbcTemplate.update("DELETE FROM MEMO WHERE ID = ?", idx);
    }

    @Override
    public Memo findByIdx(int idx) {
        return jdbcTemplate.queryForObject("SELECT * FROM MEMO WHERE ID = ?", new BeanPropertyRowMapper<Memo>(Memo.class), idx);
        // Select인 경우에는 매개변수는 (Memo.class) 뒤에 위치.
    }
}
