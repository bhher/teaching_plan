package jsp.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시판 데이터베이스 접근을 담당하는 DAO (Data Access Object)
 */
public class BoardDAO {
    private Connection conn;
    
    public BoardDAO(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * 게시글 등록
     * @param board 게시글 정보 DTO
     * @return 생성된 게시글 ID (실패 시 0)
     */
    public int insertBoard(BoardDTO board) throws SQLException {
        String sql = "INSERT INTO board (title, content, writer, password) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getWriter());
            pstmt.setString(4, board.getPassword());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 게시글 ID로 조회
     * @param id 게시글 ID
     * @return 게시글 정보 DTO (없으면 null)
     */
    public BoardDTO selectBoardById(int id) throws SQLException {
        String sql = "SELECT id, title, content, writer, password, hit, reg_date, mod_date " +
                     "FROM board WHERE id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setPassword(rs.getString("password"));
                board.setHit(rs.getInt("hit"));
                board.setRegDate(rs.getTimestamp("reg_date"));
                board.setModDate(rs.getTimestamp("mod_date"));
                return board;
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 조회수 증가
     * @param id 게시글 ID
     */
    public void increaseHit(int id) throws SQLException {
        String sql = "UPDATE board SET hit = hit + 1 WHERE id = ?";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 전체 게시글 목록 조회 (페이징)
     * @param start 시작 인덱스
     * @param pageSize 페이지 크기
     * @return 게시글 목록
     */
    public List<BoardDTO> selectAllBoards(int start, int pageSize) throws SQLException {
        String sql = "SELECT id, title, content, writer, hit, reg_date " +
                     "FROM board " +
                     "ORDER BY reg_date DESC " +
                     "LIMIT ?, ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardDTO> boardList = new ArrayList<>();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setHit(rs.getInt("hit"));
                board.setRegDate(rs.getTimestamp("reg_date"));
                boardList.add(board);
            }
            return boardList;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 전체 게시글 개수 조회
     * @return 게시글 개수
     */
    public int getTotalCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM board";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 검색된 게시글 개수 조회
     * @param searchType 검색 타입 (title, writer, content)
     * @param searchKeyword 검색 키워드
     * @return 게시글 개수
     */
    public int getSearchCount(String searchType, String searchKeyword) throws SQLException {
        String sql = "SELECT COUNT(*) FROM board WHERE " + searchType + " LIKE ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + searchKeyword + "%");
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 검색된 게시글 목록 조회
     * @param searchType 검색 타입
     * @param searchKeyword 검색 키워드
     * @param start 시작 인덱스
     * @param pageSize 페이지 크기
     * @return 게시글 목록
     */
    public List<BoardDTO> searchBoards(String searchType, String searchKeyword, 
                                        int start, int pageSize) throws SQLException {
        String sql = "SELECT id, title, content, writer, hit, reg_date " +
                     "FROM board " +
                     "WHERE " + searchType + " LIKE ? " +
                     "ORDER BY reg_date DESC " +
                     "LIMIT ?, ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardDTO> boardList = new ArrayList<>();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + searchKeyword + "%");
            pstmt.setInt(2, start);
            pstmt.setInt(3, pageSize);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setHit(rs.getInt("hit"));
                board.setRegDate(rs.getTimestamp("reg_date"));
                boardList.add(board);
            }
            return boardList;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 게시글 수정
     * @param board 수정할 게시글 정보
     * @return 수정된 행 수
     */
    public int updateBoard(BoardDTO board) throws SQLException {
        String sql = "UPDATE board SET title = ?, content = ?, mod_date = CURRENT_TIMESTAMP " +
                     "WHERE id = ? AND password = ?";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getId());
            pstmt.setString(4, board.getPassword());
            
            return pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 게시글 삭제
     * @param id 게시글 ID
     * @param password 비밀번호
     * @return 삭제된 행 수
     */
    public int deleteBoard(int id, String password) throws SQLException {
        String sql = "DELETE FROM board WHERE id = ? AND password = ?";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, password);
            
            return pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 비밀번호 확인
     * @param id 게시글 ID
     * @param password 비밀번호
     * @return 일치하면 true
     */
    public boolean checkPassword(int id, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM board WHERE id = ? AND password = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, password);
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
}
