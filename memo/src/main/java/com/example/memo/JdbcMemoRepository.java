package com.example.memo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class JdbcMemoRepository implements MemoRepository{

    @Autowired // 타입이 같으므로 MemoApplication에서 생성한 Bean이 이 곳에 붙는다.
    private DataSource dataSource;

    @Override
    public List<Memo> allList() {
        List<Memo> memos = new ArrayList<>();
        String sql = "SELECT * FROM memo";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                memos.add(new Memo(id, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memos;
    }

    @Override
    public int insertMemo(Memo memo) {
        int result = 0;
        String sql = "INSERT INTO memo (content) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, memo.getContent());
            result = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateMemo(Memo memo) {
        String sql = "UPDATE memo SET content = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, memo.getContent());
            ps.setLong(2, memo.getId());
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteMemo(int idx) {
        String sql = "DELETE FROM memo WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idx);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Memo findByIdx(int idx) {
        String sql = "SELECT * FROM memo WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idx);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String content = rs.getString("content");
                    return new Memo(idx, content);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
