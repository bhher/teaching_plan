package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.ProductDTO;
import util.DBConnection;

/** 상품 등록·목록만 (연습용) */
public class ProductDAO {

    public int insert(ProductDTO p) throws Exception {
        String sql = "INSERT INTO product (name, price, image, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getDescription());
            return ps.executeUpdate();
        }
    }

    public List<ProductDTO> selectAll() throws Exception {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, price, image, description, reg_date FROM product ORDER BY id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductDTO p = new ProductDTO();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setRegDate(rs.getTimestamp("reg_date"));
                list.add(p);
            }
        }
        return list;
    }
}
