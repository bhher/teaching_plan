package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.DestinationDTO;
import util.DBConnection;

public class WishlistDAO {

    public boolean exists(String memberId, int destinationId) throws Exception {
        String sql = "SELECT member_id FROM wishlist WHERE member_id = ? AND destination_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ps.setInt(2, destinationId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public int add(String memberId, int destinationId) throws Exception {
        String sql = "INSERT INTO wishlist (member_id, destination_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ps.setInt(2, destinationId);
            return ps.executeUpdate();
        }
    }

    public int remove(String memberId, int destinationId) throws Exception {
        String sql = "DELETE FROM wishlist WHERE member_id = ? AND destination_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ps.setInt(2, destinationId);
            return ps.executeUpdate();
        }
    }

    public List<DestinationDTO> selectByMember(String memberId) throws Exception {
        List<DestinationDTO> list = new ArrayList<>();
        String sql = "SELECT d.id, d.name, d.region, d.description, d.image, d.reg_date "
                + "FROM wishlist w JOIN destination d ON w.destination_id = d.id "
                + "WHERE w.member_id = ? ORDER BY w.reg_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DestinationDTO d = new DestinationDTO();
                    d.setId(rs.getInt("id"));
                    d.setName(rs.getString("name"));
                    d.setRegion(rs.getString("region"));
                    d.setDescription(rs.getString("description"));
                    d.setImage(rs.getString("image"));
                    d.setRegDate(rs.getTimestamp("reg_date"));
                    list.add(d);
                }
            }
        }
        return list;
    }
}
