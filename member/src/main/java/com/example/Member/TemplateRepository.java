package com.example.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateRepository implements MemberRepository{

	//@Autowired
	private final JdbcTemplate jt;
		
	public TemplateRepository(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public boolean insert(Member m) {
		String sql = "INSERT INTO MEMBER(email, password, username) values(?,?,?)";
		
		return jt.update(sql, m.getEmail(),m.getPassword(),m.getUsername()) > 0;
	}

	@Override
	public List<Member> findAll() {
		
		String sql = "SELECT * FROM MEMBER ORDER BY ID ASC";

		return jt.query(sql, new BeanPropertyRowMapper<Member>(Member.class));
	}

	@Override
	public Member findById(Long id) {
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		return jt.queryForObject(sql, new BeanPropertyRowMapper<Member>(Member.class), id);
	}

	@Override
	public boolean update(Member m) {
		String sql = "UPDATE MEMBER SET PASSWORD = ?, USERNAME = ? WHERE ID = ?";
		
		return jt.update(sql,m.getPassword(),m.getUsername(), m.getId()) > 0;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		return jt.update(sql, id) > 0;
	}

}
