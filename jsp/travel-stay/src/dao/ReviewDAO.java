package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.ReviewDTO;
import util.DBConnection;

public class ReviewDAO {

    public List<ReviewDTO> selectByDestination(int destinationId) throws Exception {
        List<ReviewDTO> list = new ArrayList<>();
        String sql = "SELECT r.id, r.destination_id, r.member_id, r.rating, r.content, r.reg_date, m.name AS member_name "
                + "FROM review r JOIN member m ON r.member_id = m.id "
                + "WHERE r.destination_id = ? ORDER BY r.id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, destinationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReviewDTO r = new ReviewDTO();
                    r.setId(rs.getInt("id"));
                    r.setDestinationId(rs.getInt("destination_id"));
                    r.setMemberId(rs.getString("member_id"));
                    r.setMemberName(rs.getString("member_name"));
                    r.setRating(rs.getInt("rating"));
                    r.setContent(rs.getString("content"));
                    r.setRegDate(rs.getTimestamp("reg_date"));
                    list.add(r);
                }
            }
        }
        return list;
    }

    public int insert(ReviewDTO r) throws Exception {
        String sql = "INSERT INTO review (destination_id, member_id, rating, content) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, r.getDestinationId());
            ps.setString(2, r.getMemberId());
            ps.setInt(3, r.getRating());
            ps.setString(4, r.getContent());
            return ps.executeUpdate();
        }
    }

    public int delete(int id, String memberId) throws Exception {
        String sql = "DELETE FROM review WHERE id = ? AND member_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, memberId);
            return ps.executeUpdate();
        }
    }
}
