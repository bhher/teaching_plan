package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.MemberDTO;
import util.DBConnection;

public class MemberDAO {

    public boolean existsId(String id) throws Exception {
        String sql = "SELECT id FROM member WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public int insert(MemberDTO m) throws Exception {
        String sql = "INSERT INTO member (id, password, name, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getId());
            ps.setString(2, m.getPassword());
            ps.setString(3, m.getName());
            ps.setString(4, m.getRole() != null ? m.getRole() : "USER");
            return ps.executeUpdate();
        }
    }

    public MemberDTO login(String id, String password) throws Exception {
        String sql = "SELECT id, password, name, role FROM member WHERE id = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MemberDTO m = new MemberDTO();
                    m.setId(rs.getString("id"));
                    m.setPassword(rs.getString("password"));
                    m.setName(rs.getString("name"));
                    m.setRole(rs.getString("role"));
                    return m;
                }
            }
        }
        return null;
    }
}
