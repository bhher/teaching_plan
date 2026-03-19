package kculture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<CategoryDTO> list() throws Exception {
        List<CategoryDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM category ORDER BY sort_order";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapCategory(rs));
        }
        DBConnection.close(conn, ps, rs);
        return list;
    }

    public CategoryDTO findById(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM category WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        CategoryDTO dto = rs.next() ? mapCategory(rs) : null;
        DBConnection.close(conn, ps, rs);
        return dto;
    }

    public CategoryDTO findByCode(String code) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM category WHERE code=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        CategoryDTO dto = rs.next() ? mapCategory(rs) : null;
        DBConnection.close(conn, ps, rs);
        return dto;
    }

    private CategoryDTO mapCategory(ResultSet rs) throws SQLException {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(rs.getInt("id"));
        dto.setCode(rs.getString("code"));
        dto.setNameEn(rs.getString("name_en"));
        dto.setNameKo(rs.getString("name_ko"));
        dto.setIcon(rs.getString("icon"));
        dto.setSortOrder(rs.getInt("sort_order"));
        return dto;
    }
}
