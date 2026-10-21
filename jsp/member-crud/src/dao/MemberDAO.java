package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDTO;
import util.DBConnection;

public class MemberDAO {

    /** 아이디 중복 확인 (있으면 true) */
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

    /** 회원가입 */
    public int insert(MemberDTO m) throws Exception {
        String sql = "INSERT INTO member (id, password, name, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getId());
            ps.setString(2, m.getPassword());
            ps.setString(3, m.getName());
            ps.setString(4, m.getEmail());
            return ps.executeUpdate();
        }
    }

    /** 로그인 (id+password 일치 시 회원 반환) */
    public MemberDTO login(String id, String password) throws Exception {
        String sql = "SELECT id, password, name, email, reg_date FROM member WHERE id = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    /** 목록 */
    public List<MemberDTO> selectAll() throws Exception {
        List<MemberDTO> list = new ArrayList<>();
        String sql = "SELECT id, password, name, email, reg_date FROM member ORDER BY reg_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    /** 한 명 조회 */
    public MemberDTO selectById(String id) throws Exception {
        String sql = "SELECT id, password, name, email, reg_date FROM member WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    /** 수정 (이름·이메일·비밀번호) */
    public int update(MemberDTO m) throws Exception {
        String sql = "UPDATE member SET password = ?, name = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getPassword());
            ps.setString(2, m.getName());
            ps.setString(3, m.getEmail());
            ps.setString(4, m.getId());
            return ps.executeUpdate();
        }
    }

    /** 삭제 */
    public int delete(String id) throws Exception {
        String sql = "DELETE FROM member WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        }
    }

    private MemberDTO map(ResultSet rs) throws Exception {
        MemberDTO m = new MemberDTO();
        m.setId(rs.getString("id"));
        m.setPassword(rs.getString("password"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setRegDate(rs.getTimestamp("reg_date"));
        return m;
    }
}
