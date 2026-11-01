package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;
import util.DBConnection;

public class BoardDAO {

    public List<BoardDTO> selectAll() throws Exception {
        List<BoardDTO> list = new ArrayList<>();
        String sql = "SELECT id, title, content, writer_id, writer_name, reg_date FROM board ORDER BY id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public BoardDTO selectById(int id) throws Exception {
        String sql = "SELECT id, title, content, writer_id, writer_name, reg_date FROM board WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    public int insert(BoardDTO b) throws Exception {
        String sql = "INSERT INTO board (title, content, writer_id, writer_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getContent());
            ps.setString(3, b.getWriterId());
            ps.setString(4, b.getWriterName());
            return ps.executeUpdate();
        }
    }

    public int update(BoardDTO b) throws Exception {
        String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getContent());
            ps.setInt(3, b.getId());
            return ps.executeUpdate();
        }
    }

    public int delete(int id) throws Exception {
        String sql = "DELETE FROM board WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    private BoardDTO map(ResultSet rs) throws Exception {
        BoardDTO b = new BoardDTO();
        b.setId(rs.getInt("id"));
        b.setTitle(rs.getString("title"));
        b.setContent(rs.getString("content"));
        b.setWriterId(rs.getString("writer_id"));
        b.setWriterName(rs.getString("writer_name"));
        b.setRegDate(rs.getTimestamp("reg_date"));
        return b;
    }
}
