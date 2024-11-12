package com.example.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;



//@Repository
public class JdbcMemberRepository implements MemberRepository {

	//@Autowired
	private DataSource dataSource;
	
	
	
	public JdbcMemberRepository(DataSource dataSource) {
	
		this.dataSource = dataSource;
	}

	@Override
	public boolean insert(Member m) {
		boolean result = false;
		String sql = "INSERT INTO MEMBER(email, password, username) values(?,?,?)";
		
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql))
		{
			pstmt.setString(1, m.getEmail());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUsername());
			
			if (pstmt.executeUpdate() != 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;		
	}

	@Override
	public List<Member> findAll() {
		List<Member> mLst = new ArrayList();
		String sql = "SELECT * FROM MEMBER ORDER BY ID ASC";
		
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql))
		{
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Member m = new Member(rs.getLong("id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("username"),
						rs.getDate("regDate"));
				mLst.add(m);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mLst;
	}

	@Override
	public Member findById(Long id) {
		Member result = null;
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql))
		{
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				result = new Member(rs.getLong("id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("username"), 
						rs.getDate("regDate"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean update(Member m) {
		boolean result = false;
		String sql = "UPDATE MEMBER SET  PASSWORD = ?, USERNAME = ? WHERE ID = ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql))		
		{
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getUsername());
			pstmt.setLong(3, m.getId());
			
			if(pstmt.executeUpdate() != 0)
				result = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean delete(Long id) {
		boolean result = false;
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql))		
		{
			pstmt.setLong(1, id);
			if(pstmt.executeUpdate() != 0)
			{
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
