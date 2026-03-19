package kculture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public List<PostDTO> list(int categoryId, int start, int size) throws Exception {
        List<PostDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql;
        PreparedStatement ps;
        if (categoryId > 0) {
            sql = "SELECT p.*, m.name as member_name, c.name_en as category_name, c.icon as category_icon " +
                  "FROM post p JOIN member m ON p.member_id=m.id JOIN category c ON p.category_id=c.id " +
                  "WHERE p.category_id=? ORDER BY p.created_at DESC LIMIT ?, ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, start);
            ps.setInt(3, size);
        } else {
            sql = "SELECT p.*, m.name as member_name, c.name_en as category_name, c.icon as category_icon " +
                  "FROM post p JOIN member m ON p.member_id=m.id JOIN category c ON p.category_id=c.id " +
                  "ORDER BY p.created_at DESC LIMIT ?, ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, size);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapPost(rs));
        }
        DBConnection.close(conn, ps, rs);
        return list;
    }

    public int count(int categoryId) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = categoryId > 0 ? "SELECT COUNT(*) FROM post WHERE category_id=?" : "SELECT COUNT(*) FROM post";
        PreparedStatement ps = conn.prepareStatement(sql);
        if (categoryId > 0) ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        int cnt = rs.next() ? rs.getInt(1) : 0;
        DBConnection.close(conn, ps, rs);
        return cnt;
    }

    public PostDTO findById(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT p.*, m.name as member_name, m.nationality, c.name_en as category_name, c.icon as category_icon " +
                     "FROM post p JOIN member m ON p.member_id=m.id JOIN category c ON p.category_id=c.id WHERE p.id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        PostDTO dto = rs.next() ? mapPost(rs) : null;
        DBConnection.close(conn, ps, rs);
        return dto;
    }

    public void increaseView(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE post SET view_count=view_count+1 WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        DBConnection.close(conn, ps);
    }

    public int insert(PostDTO dto) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO post (category_id, member_id, title, content) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, dto.getCategoryId());
        ps.setInt(2, dto.getMemberId());
        ps.setString(3, dto.getTitle());
        ps.setString(4, dto.getContent());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int id = rs.next() ? rs.getInt(1) : 0;
        DBConnection.close(conn, ps, rs);
        return id;
    }

    public void update(PostDTO dto) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE post SET title=?, content=? WHERE id=? AND member_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dto.getTitle());
        ps.setString(2, dto.getContent());
        ps.setInt(3, dto.getId());
        ps.setInt(4, dto.getMemberId());
        ps.executeUpdate();
        DBConnection.close(conn, ps);
    }

    public void delete(int id, int memberId) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM post WHERE id=? AND member_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, memberId);
        ps.executeUpdate();
        DBConnection.close(conn, ps);
    }

    private PostDTO mapPost(ResultSet rs) throws SQLException {
        PostDTO dto = new PostDTO();
        dto.setId(rs.getInt("id"));
        dto.setCategoryId(rs.getInt("category_id"));
        dto.setMemberId(rs.getInt("member_id"));
        dto.setTitle(rs.getString("title"));
        dto.setContent(rs.getString("content"));
        dto.setViewCount(rs.getInt("view_count"));
        dto.setCreatedAt(rs.getTimestamp("created_at"));
        dto.setUpdatedAt(rs.getTimestamp("updated_at"));
        try { dto.setMemberName(rs.getString("member_name")); } catch (Exception e) {}
        try { dto.setCategoryName(rs.getString("category_name")); } catch (Exception e) {}
        try { dto.setCategoryIcon(rs.getString("category_icon")); } catch (Exception e) {}
        return dto;
    }
}
