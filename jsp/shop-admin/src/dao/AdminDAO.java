package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.AdminDTO;
import util.DBConnection;

/**
 * 관리자 로그인 DAO
 */
public class AdminDAO {

    /**
     * 아이디·비밀번호가 일치하면 AdminDTO 반환, 아니면 null
     */
    public AdminDTO login(String id, String password) throws Exception {
        String sql = "SELECT id, password, name FROM admin WHERE id = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AdminDTO admin = new AdminDTO();
                    admin.setId(rs.getString("id"));
                    admin.setPassword(rs.getString("password"));
                    admin.setName(rs.getString("name"));
                    return admin;
                }
            }
        }
        return null;
    }
}
