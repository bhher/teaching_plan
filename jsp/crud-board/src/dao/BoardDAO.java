package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;
import util.DBConnection;

public class BoardDAO {

    /** 목록 (최신글 위) */
    public List<BoardDTO> selectAll() throws Exception {
        List<BoardDTO> list = new ArrayList<>();
        String sql = "SELECT id, title, writer, hit, reg_date FROM board ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BoardDTO b = new BoardDTO();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setWriter(rs.getString("writer"));
                b.setHit(rs.getInt("hit"));
                b.setRegDate(rs.getTimestamp("reg_date"));
                list.add(b);
            }
        }
        return list;
    }

    /** 상세 */
    public BoardDTO selectById(int id) throws Exception {
        String sql = "SELECT id, title, content, writer, password, hit, reg_date, mod_date "
                + "FROM board WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BoardDTO b = new BoardDTO();
                    b.setId(rs.getInt("id"));
                    b.setTitle(rs.getString("title"));
                    b.setContent(rs.getString("content"));
                    b.setWriter(rs.getString("writer"));
                    b.setPassword(rs.getString("password"));
                    b.setHit(rs.getInt("hit"));
                    b.setRegDate(rs.getTimestamp("reg_date"));
                    b.setModDate(rs.getTimestamp("mod_date"));
                    return b;
                }
            }
        }
        return null;
    }

    /** 등록 */
    public int insert(BoardDTO board) throws Exception {
        String sql = "INSERT INTO board (title, content, writer, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.setString(4, board.getPassword());
            return ps.executeUpdate();
        }
    }

    /** 수정 (비밀번호 일치 시) */
    public int update(BoardDTO board) throws Exception {
        String sql = "UPDATE board SET title = ?, content = ?, writer = ? "
                + "WHERE id = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.setInt(4, board.getId());
            ps.setString(5, board.getPassword());
            return ps.executeUpdate();
        }
    }

    /** 삭제 (비밀번호 일치 시) */
    public int delete(int id, String password) throws Exception {
        String sql = "DELETE FROM board WHERE id = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, password);
            return ps.executeUpdate();
        }
    }

    /** 조회수 +1 */
    public void increaseHit(int id) throws Exception {
        String sql = "UPDATE board SET hit = hit + 1 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
