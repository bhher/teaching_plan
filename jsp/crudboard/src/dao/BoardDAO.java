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
        String sql = "SELECT id, title, content FROM board ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BoardDTO b = new BoardDTO();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setContent(rs.getString("content"));
                list.add(b);
            }
        }
        return list;
    }

    public BoardDTO selectById(int id) throws Exception {
        String sql = "SELECT id, title, content FROM board WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BoardDTO b = new BoardDTO();
                    b.setId(rs.getInt("id"));
                    b.setTitle(rs.getString("title"));
                    b.setContent(rs.getString("content"));
                    return b;
                }
            }
        }
        return null;
    }

    public int insert(BoardDTO board) throws Exception {
        String sql = "INSERT INTO board (title, content) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            return ps.executeUpdate();
        }
    }

    public int update(BoardDTO board) throws Exception {
        String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setInt(3, board.getId());
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
}
