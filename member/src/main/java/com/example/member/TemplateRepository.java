package com.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TemplateRepository implements MemberRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Member> allList() {
        return jdbcTemplate.query("SELECT * FROM MEMBER ORDER BY ID",
                new BeanPropertyRowMapper<Member>(Member.class));

    }

    @Override
    public int insertMember(Member member) {
        return jdbcTemplate.update("INSERT INTO member (email, password, username) VALUES (?, ?, ?)", member.getEmail(), member.getPassword(), member.getUsername());
    }

    @Override
    public int updateMember(Member member) {
        return jdbcTemplate.update("UPDATE member SET email = ?, password = ?, username = ? WHERE id = ?", member.getEmail(), member.getPassword(), member.getUsername(), member.getId());
    }

    @Override
    public int deleteMember(int id) {
        return jdbcTemplate.update("DELETE FROM member WHERE id = ?", id);
    }

    @Override
    public Member getMemberInfo(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM member WHERE id = ?", new BeanPropertyRowMapper<Member>(Member.class), id);
    }
}
