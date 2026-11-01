package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.AdminDTO;
import util.DBConnection;

public class AdminDAO {

    public AdminDTO login(String id, String password) throws Exception {
        String sql = "SELECT id, password, name FROM admin WHERE id = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AdminDTO a = new AdminDTO();
                    a.setId(rs.getString("id"));
                    a.setPassword(rs.getString("password"));
                    a.setName(rs.getString("name"));
                    return a;
                }
            }
        }
        return null;
    }
}
