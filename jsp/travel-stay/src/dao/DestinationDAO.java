package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.DestinationDTO;
import util.DBConnection;

public class DestinationDAO {

    /** 지역·키워드 검색 + 숙소 수·평점 JOIN */
    public List<DestinationDTO> search(String region, String keyword) throws Exception {
        List<DestinationDTO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d.id, d.name, d.region, d.description, d.image, d.reg_date, ");
        sql.append("       COUNT(DISTINCT s.id) AS stay_count, ");
        sql.append("       AVG(r.rating) AS avg_rating, ");
        sql.append("       COUNT(DISTINCT r.id) AS review_count ");
        sql.append("FROM destination d ");
        sql.append("LEFT JOIN stay s ON d.id = s.destination_id ");
        sql.append("LEFT JOIN review r ON d.id = r.destination_id ");
        sql.append("WHERE 1=1 ");

        List<Object> params = new ArrayList<>();
        if (region != null && !region.trim().isEmpty() && !"전체".equals(region)) {
            sql.append("AND d.region = ? ");
            params.add(region.trim());
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND (d.name LIKE ? OR d.description LIKE ?) ");
            String like = "%" + keyword.trim() + "%";
            params.add(like);
            params.add(like);
        }
        sql.append("GROUP BY d.id, d.name, d.region, d.description, d.image, d.reg_date ");
        sql.append("ORDER BY d.id DESC");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapList(rs));
                }
            }
        }
        return list;
    }

    public DestinationDTO selectById(int id) throws Exception {
        String sql = "SELECT d.id, d.name, d.region, d.description, d.image, d.reg_date, "
                + "COUNT(DISTINCT s.id) AS stay_count, AVG(r.rating) AS avg_rating, "
                + "COUNT(DISTINCT r.id) AS review_count "
                + "FROM destination d "
                + "LEFT JOIN stay s ON d.id = s.destination_id "
                + "LEFT JOIN review r ON d.id = r.destination_id "
                + "WHERE d.id = ? "
                + "GROUP BY d.id, d.name, d.region, d.description, d.image, d.reg_date";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapList(rs);
                }
            }
        }
        return null;
    }

    public List<String> selectRegions() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT region FROM destination ORDER BY region";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("region"));
            }
        }
        return list;
    }

    public int insert(DestinationDTO d) throws Exception {
        String sql = "INSERT INTO destination (name, region, description, image) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getRegion());
            ps.setString(3, d.getDescription());
            ps.setString(4, d.getImage());
            return ps.executeUpdate();
        }
    }

    public int update(DestinationDTO d) throws Exception {
        String sql = "UPDATE destination SET name=?, region=?, description=?, image=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getRegion());
            ps.setString(3, d.getDescription());
            ps.setString(4, d.getImage());
            ps.setInt(5, d.getId());
            return ps.executeUpdate();
        }
    }

    public int delete(int id) throws Exception {
        String sql = "DELETE FROM destination WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    private DestinationDTO mapList(ResultSet rs) throws Exception {
        DestinationDTO d = new DestinationDTO();
        d.setId(rs.getInt("id"));
        d.setName(rs.getString("name"));
        d.setRegion(rs.getString("region"));
        d.setDescription(rs.getString("description"));
        d.setImage(rs.getString("image"));
        d.setRegDate(rs.getTimestamp("reg_date"));
        d.setStayCount(rs.getInt("stay_count"));
        double avg = rs.getDouble("avg_rating");
        if (!rs.wasNull()) {
            d.setAvgRating(Math.round(avg * 10.0) / 10.0);
        }
        d.setReviewCount(rs.getInt("review_count"));
        return d;
    }
}
