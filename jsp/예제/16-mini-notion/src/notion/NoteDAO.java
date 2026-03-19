package notion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 계층형(Re-level, Re-step) 노트 DAO
 */
public class NoteDAO {

    /** 전체 노트 목록 (휴지통 제외, 계층 정렬) */
    public List<NoteDTO> list() throws Exception {
        List<NoteDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM note WHERE deleted=0 ORDER BY ref DESC, re_step ASC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapResultSet(rs));
        }
        DBConnection.close(conn, ps, rs);
        return list;
    }

    /** ID로 노트 조회 */
    public NoteDTO findById(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM note WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        NoteDTO dto = rs.next() ? mapResultSet(rs) : null;
        DBConnection.close(conn, ps, rs);
        return dto;
    }

    /** 새 원글 작성 */
    public int insertRoot(NoteDTO dto) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO note (title, content, writer, ref, re_level, re_step) VALUES (?, ?, ?, 0, 0, 0)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dto.getTitle());
        ps.setString(2, dto.getContent());
        ps.setString(3, dto.getWriter() != null ? dto.getWriter() : "익명");
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int id = rs.next() ? rs.getInt(1) : 0;
        DBConnection.close(conn, ps, rs);

        // 원글의 ref를 자기 id로 업데이트
        if (id > 0) {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("UPDATE note SET ref=? WHERE id=?");
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.executeUpdate();
            DBConnection.close(conn, ps);
        }
        return id;
    }

    /** 답글 작성 (계층형 Re-level, Re-step) */
    public int insertReply(int parentId, NoteDTO dto) throws Exception {
        NoteDTO parent = findById(parentId);
        if (parent == null) return 0;

        int ref = parent.getRef();
        int reLevel = parent.getReLevel() + 1;
        int reStep = parent.getReStep() + 1;

        // 같은 ref에서 re_step >= reStep 인 것들 +1
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE note SET re_step=re_step+1 WHERE ref=? AND re_step>=?");
        ps.setInt(1, ref);
        ps.setInt(2, reStep);
        ps.executeUpdate();
        DBConnection.close(conn, ps);

        // 답글 삽입
        conn = DBConnection.getConnection();
        String sql = "INSERT INTO note (title, content, writer, ref, re_level, re_step) VALUES (?, ?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dto.getTitle());
        ps.setString(2, dto.getContent());
        ps.setString(3, dto.getWriter() != null ? dto.getWriter() : "익명");
        ps.setInt(4, ref);
        ps.setInt(5, reLevel);
        ps.setInt(6, reStep);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int id = rs.next() ? rs.getInt(1) : 0;
        DBConnection.close(conn, ps, rs);
        return id;
    }

    /** 내용 수정 (실시간 업데이트) */
    public void update(NoteDTO dto) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE note SET title=?, content=?, writer=? WHERE id=? AND deleted=0";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dto.getTitle());
        ps.setString(2, dto.getContent());
        ps.setString(3, dto.getWriter());
        ps.setInt(4, dto.getId());
        ps.executeUpdate();
        DBConnection.close(conn, ps);
    }

    /** 휴지통 이동 (Soft Delete) */
    public void moveToTrash(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE note SET deleted=1, deleted_at=NOW() WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        DBConnection.close(conn, ps);
    }

    /** 휴지통 목록 */
    public List<NoteDTO> listTrash() throws Exception {
        List<NoteDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM note WHERE deleted=1 ORDER BY deleted_at DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapResultSet(rs));
        }
        DBConnection.close(conn, ps, rs);
        return list;
    }

    private NoteDTO mapResultSet(ResultSet rs) throws SQLException {
        NoteDTO dto = new NoteDTO();
        dto.setId(rs.getInt("id"));
        dto.setTitle(rs.getString("title"));
        dto.setContent(rs.getString("content"));
        dto.setWriter(rs.getString("writer"));
        dto.setRef(rs.getInt("ref"));
        dto.setReLevel(rs.getInt("re_level"));
        dto.setReStep(rs.getInt("re_step"));
        dto.setDeleted(rs.getInt("deleted"));
        dto.setDeletedAt(rs.getTimestamp("deleted_at"));
        dto.setCreatedAt(rs.getTimestamp("created_at"));
        dto.setUpdatedAt(rs.getTimestamp("updated_at"));
        return dto;
    }
}
