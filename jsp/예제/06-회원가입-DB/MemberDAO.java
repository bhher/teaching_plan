package jsp.member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원 정보를 데이터베이스에 저장하고 조회하는 DAO (Data Access Object)
 */
public class MemberDAO {
    private Connection conn;
    
    public MemberDAO(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * 회원 등록
     * @param member 회원 정보 DTO
     * @return 생성된 회원 ID (실패 시 0)
     */
    public int insertMember(MemberDTO member) throws SQLException {
        String sql = "INSERT INTO members (user_id, password, name, email, gender, city) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.hasEmail() ? member.getEmail() : null);
            pstmt.setString(5, member.getGender() != null && !member.getGender().isEmpty() ? member.getGender() : null);
            pstmt.setString(6, member.getCity() != null && !member.getCity().isEmpty() ? member.getCity() : null);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                // 생성된 회원 ID 가져오기
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int memberId = rs.getInt(1);
                    
                    // 취미 저장
                    if (member.hasHobbies()) {
                        insertHobbies(memberId, member.getHobbies());
                    }
                    
                    return memberId;
                }
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 취미 정보 저장
     */
    private void insertHobbies(int memberId, List<String> hobbies) throws SQLException {
        String sql = "INSERT INTO member_hobbies (member_id, hobby) VALUES (?, ?)";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            for (String hobby : hobbies) {
                pstmt.setInt(1, memberId);
                pstmt.setString(2, hobby);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 아이디 중복 체크
     * @param userId 확인할 아이디
     * @return 중복이면 true, 아니면 false
     */
    public boolean isUserIdExists(String userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM members WHERE user_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 아이디로 회원 정보 조회
     * @param userId 조회할 아이디
     * @return 회원 정보 DTO (없으면 null)
     */
    public MemberDTO selectMemberByUserId(String userId) throws SQLException {
        String sql = "SELECT id, user_id, password, name, email, gender, city, reg_date " +
                     "FROM members WHERE user_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setId(rs.getInt("id"));
                member.setUserId(rs.getString("user_id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setCity(rs.getString("city"));
                member.setRegDate(rs.getTimestamp("reg_date"));
                
                // 취미 정보 조회
                List<String> hobbies = selectHobbiesByMemberId(member.getId());
                member.setHobbies(hobbies);
                
                return member;
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 회원 ID로 취미 목록 조회
     */
    private List<String> selectHobbiesByMemberId(int memberId) throws SQLException {
        String sql = "SELECT hobby FROM member_hobbies WHERE member_id = ? ORDER BY hobby";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> hobbies = new ArrayList<>();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                hobbies.add(rs.getString("hobby"));
            }
            return hobbies;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 모든 회원 목록 조회
     * @return 회원 목록
     */
    public List<MemberDTO> selectAllMembers() throws SQLException {
        String sql = "SELECT m.id, m.user_id, m.name, m.email, m.gender, m.city, m.reg_date, " +
                     "GROUP_CONCAT(mh.hobby ORDER BY mh.hobby SEPARATOR ', ') as hobbies " +
                     "FROM members m " +
                     "LEFT JOIN member_hobbies mh ON m.id = mh.member_id " +
                     "GROUP BY m.id " +
                     "ORDER BY m.reg_date DESC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberDTO> memberList = new ArrayList<>();
        
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setId(rs.getInt("id"));
                member.setUserId(rs.getString("user_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setCity(rs.getString("city"));
                member.setRegDate(rs.getTimestamp("reg_date"));
                
                // 취미 정보 파싱
                String hobbiesStr = rs.getString("hobbies");
                if (hobbiesStr != null && !hobbiesStr.isEmpty()) {
                    List<String> hobbies = new ArrayList<>();
                    String[] hobbyArray = hobbiesStr.split(", ");
                    for (String hobby : hobbyArray) {
                        hobbies.add(hobby);
                    }
                    member.setHobbies(hobbies);
                }
                
                memberList.add(member);
            }
            return memberList;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 비밀번호 확인
     * @param userId 아이디
     * @param password 비밀번호
     * @return 일치하면 true, 아니면 false
     */
    public boolean checkPassword(String userId, String password) throws SQLException {
        MemberDTO member = selectMemberByUserId(userId);
        if (member != null) {
            // 실제로는 암호화된 비밀번호와 비교해야 함
            return member.getPassword().equals(password);
        }
        return false;
    }
}
