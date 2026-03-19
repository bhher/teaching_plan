package kculture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public List<CommentDTO> listByPostId(int postId) throws Exception {
        List<CommentDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT c.*, m.name as member_name, m.nationality FROM comment c JOIN member m ON c.member_id=m.id WHERE c.post_id=? ORDER BY c.created_at";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapComment(rs));
        }
        DBConnection.close(conn, ps, rs);
        return list;
    }

    public int insert(CommentDTO dto) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO comment (post_id, member_id, content) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, dto.getPostId());
        ps.setInt(2, dto.getMemberId());
        ps.setString(3, dto.getContent());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int id = rs.next() ? rs.getInt(1) : 0;
        DBConnection.close(conn, ps, rs);
        return id;
    }

    private CommentDTO mapComment(ResultSet rs) throws SQLException {
        CommentDTO dto = new CommentDTO();
        dto.setId(rs.getInt("id"));
        dto.setPostId(rs.getInt("post_id"));
        dto.setMemberId(rs.getInt("member_id"));
        dto.setContent(rs.getString("content"));
        dto.setCreatedAt(rs.getTimestamp("created_at"));
        try { dto.setMemberName(rs.getString("member_name")); } catch (Exception e) {}
        try { dto.setNationality(rs.getString("nationality")); } catch (Exception e) {}
        return dto;
    }
}
