package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.ProductDTO;
import util.DBConnection;

/**
 * 상품 CRUD DAO (PreparedStatement 사용)
 */
public class ProductDAO {

    /** 전체 목록 (최신순) */
    public List<ProductDTO> selectAll() throws Exception {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, price, stock, category, description, reg_date "
                + "FROM product ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    /** 상품명 검색 (LIKE) */
    public List<ProductDTO> searchByName(String keyword) throws Exception {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, price, stock, category, description, reg_date "
                + "FROM product WHERE name LIKE ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    /** 상품 번호로 한 건 조회 */
    public ProductDTO selectById(int id) throws Exception {
        String sql = "SELECT id, name, price, stock, category, description, reg_date "
                + "FROM product WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    /** 상품 등록 */
    public int insert(ProductDTO p) throws Exception {
        String sql = "INSERT INTO product (name, price, stock, category, description) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCategory());
            ps.setString(5, p.getDescription());
            return ps.executeUpdate();
        }
    }

    /** 상품 수정 (id 기준) */
    public int update(ProductDTO p) throws Exception {
        String sql = "UPDATE product SET name = ?, price = ?, stock = ?, category = ?, description = ? "
                + "WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCategory());
            ps.setString(5, p.getDescription());
            ps.setInt(6, p.getId());
            return ps.executeUpdate();
        }
    }

    /** 상품 삭제 (id 기준) */
    public int delete(int id) throws Exception {
        String sql = "DELETE FROM product WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    private ProductDTO map(ResultSet rs) throws Exception {
        ProductDTO p = new ProductDTO();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getInt("price"));
        p.setStock(rs.getInt("stock"));
        p.setCategory(rs.getString("category"));
        p.setDescription(rs.getString("description"));
        p.setRegDate(rs.getTimestamp("reg_date"));
        return p;
    }
}
