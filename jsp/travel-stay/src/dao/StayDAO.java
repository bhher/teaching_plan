package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.StayDTO;
import util.DBConnection;

public class StayDAO {

    public List<StayDTO> selectByDestination(int destinationId) throws Exception {
        List<StayDTO> list = new ArrayList<>();
        String sql = "SELECT id, destination_id, name, address, price, description, image, reg_date "
                + "FROM stay WHERE destination_id = ? ORDER BY price";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, destinationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    public StayDTO selectById(int id) throws Exception {
        String sql = "SELECT s.id, s.destination_id, s.name, s.address, s.price, s.description, s.image, s.reg_date, "
                + "d.name AS destination_name "
                + "FROM stay s JOIN destination d ON s.destination_id = d.id WHERE s.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StayDTO s = map(rs);
                    s.setDestinationName(rs.getString("destination_name"));
                    return s;
                }
            }
        }
        return null;
    }

    public List<StayDTO> selectAll() throws Exception {
        List<StayDTO> list = new ArrayList<>();
        String sql = "SELECT s.id, s.destination_id, s.name, s.address, s.price, s.description, s.image, s.reg_date, "
                + "d.name AS destination_name "
                + "FROM stay s JOIN destination d ON s.destination_id = d.id ORDER BY s.id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                StayDTO s = map(rs);
                s.setDestinationName(rs.getString("destination_name"));
                list.add(s);
            }
        }
        return list;
    }

    public int insert(StayDTO s) throws Exception {
        String sql = "INSERT INTO stay (destination_id, name, address, price, description, image) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getDestinationId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getAddress());
            ps.setInt(4, s.getPrice());
            ps.setString(5, s.getDescription());
            ps.setString(6, s.getImage());
            return ps.executeUpdate();
        }
    }

    public int update(StayDTO s) throws Exception {
        String sql = "UPDATE stay SET destination_id=?, name=?, address=?, price=?, description=?, image=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getDestinationId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getAddress());
            ps.setInt(4, s.getPrice());
            ps.setString(5, s.getDescription());
            ps.setString(6, s.getImage());
            ps.setInt(7, s.getId());
            return ps.executeUpdate();
        }
    }

    public int delete(int id) throws Exception {
        String sql = "DELETE FROM stay WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    private StayDTO map(ResultSet rs) throws Exception {
        StayDTO s = new StayDTO();
        s.setId(rs.getInt("id"));
        s.setDestinationId(rs.getInt("destination_id"));
        s.setName(rs.getString("name"));
        s.setAddress(rs.getString("address"));
        s.setPrice(rs.getInt("price"));
        s.setDescription(rs.getString("description"));
        s.setImage(rs.getString("image"));
        s.setRegDate(rs.getTimestamp("reg_date"));
        return s;
    }
}
