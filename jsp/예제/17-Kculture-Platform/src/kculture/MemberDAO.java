package kculture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public MemberDTO login(String email, String password) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM member WHERE email=? AND password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        MemberDTO dto = null;
        if (rs.next()) {
            dto = mapMember(rs);
        }
        DBConnection.close(conn, ps, rs);
        return dto;
    }

    public int join(MemberDTO dto) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO member (email, password, name, nationality, language) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dto.getEmail());
        ps.setString(2, dto.getPassword());
        ps.setString(3, dto.getName());
        ps.setString(4, dto.getNationality());
        ps.setString(5, dto.getLanguage() != null ? dto.getLanguage() : "en");
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int id = rs.next() ? rs.getInt(1) : 0;
        DBConnection.close(conn, ps, rs);
        return id;
    }

    public MemberDTO findById(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM member WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        MemberDTO dto = rs.next() ? mapMember(rs) : null;
        DBConnection.close(conn, ps, rs);
        return dto;
    }

    public boolean existsEmail(String email) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM member WHERE email=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next() && rs.getInt(1) > 0;
        DBConnection.close(conn, ps, rs);
        return exists;
    }

    private MemberDTO mapMember(ResultSet rs) throws SQLException {
        MemberDTO dto = new MemberDTO();
        dto.setId(rs.getInt("id"));
        dto.setEmail(rs.getString("email"));
        dto.setPassword(rs.getString("password"));
        dto.setName(rs.getString("name"));
        dto.setNationality(rs.getString("nationality"));
        dto.setLanguage(rs.getString("language"));
        return dto;
    }
}
