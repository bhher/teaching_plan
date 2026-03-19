package jsp.imageboard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 이미지 게시판 데이터베이스 접근을 담당하는 DAO (Data Access Object)
 */
public class ImageBoardDAO {
    private Connection conn;
    
    public ImageBoardDAO(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * 게시글 등록
     * @param board 게시글 정보 DTO
     * @return 생성된 게시글 ID (실패 시 0)
     */
    public int insertBoard(ImageBoardDTO board) throws SQLException {
        String sql = "INSERT INTO image_board (title, content, writer, password, image_file, image_original) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getWriter());
            pstmt.setString(4, board.getPassword());
            pstmt.setString(5, board.getImageFile());
            pstmt.setString(6, board.getImageOriginal());
            
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
    public ImageBoardDTO selectBoardById(int id) throws SQLException {
        String sql = "SELECT id, title, content, writer, password, image_file, image_original, hit, reg_date, mod_date " +
                     "FROM image_board WHERE id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                ImageBoardDTO board = new ImageBoardDTO();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setPassword(rs.getString("password"));
                board.setImageFile(rs.getString("image_file"));
                board.setImageOriginal(rs.getString("image_original"));
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
        String sql = "UPDATE image_board SET hit = hit + 1 WHERE id = ?";
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
    public List<ImageBoardDTO> selectAllBoards(int start, int pageSize) throws SQLException {
        String sql = "SELECT id, title, content, writer, password, image_file, image_original, hit, reg_date, mod_date " +
                     "FROM image_board ORDER BY reg_date DESC LIMIT ?, ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ImageBoardDTO> boardList = new ArrayList<>();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ImageBoardDTO board = new ImageBoardDTO();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setPassword(rs.getString("password"));
                board.setImageFile(rs.getString("image_file"));
                board.setImageOriginal(rs.getString("image_original"));
                board.setHit(rs.getInt("hit"));
                board.setRegDate(rs.getTimestamp("reg_date"));
                board.setModDate(rs.getTimestamp("mod_date"));
                boardList.add(board);
            }
            return boardList;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 전체 게시글 수 조회
     * @return 전체 게시글 수
     */
    public int getTotalCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM image_board";
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
     * 게시글 수정
     * @param board 수정할 게시글 정보
     * @return 수정된 행 수
     */
    public int updateBoard(ImageBoardDTO board) throws SQLException {
        String sql = "UPDATE image_board SET title = ?, content = ?, image_file = ?, image_original = ? WHERE id = ?";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getImageFile());
            pstmt.setString(4, board.getImageOriginal());
            pstmt.setInt(5, board.getId());
            return pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 비밀번호 확인
     * @param id 게시글 ID
     * @param password 비밀번호
     * @return 비밀번호 일치 여부
     */
    public boolean checkPassword(int id, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM image_board WHERE id = ? AND password = ?";
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
    
    /**
     * 게시글 삭제
     * @param id 게시글 ID
     * @return 삭제된 행 수
     */
    public int deleteBoard(int id) throws SQLException {
        String sql = "DELETE FROM image_board WHERE id = ?";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 검색 (제목 또는 내용)
     * @param searchType 검색 타입 (title, content, writer)
     * @param searchKeyword 검색 키워드
     * @param start 시작 인덱스
     * @param pageSize 페이지 크기
     * @return 검색 결과 목록
     */
    public List<ImageBoardDTO> searchBoards(String searchType, String searchKeyword, int start, int pageSize) throws SQLException {
        String sql = "";
        if ("title".equals(searchType)) {
            sql = "SELECT id, title, content, writer, password, image_file, image_original, hit, reg_date, mod_date " +
                  "FROM image_board WHERE title LIKE ? ORDER BY reg_date DESC LIMIT ?, ?";
        } else if ("content".equals(searchType)) {
            sql = "SELECT id, title, content, writer, password, image_file, image_original, hit, reg_date, mod_date " +
                  "FROM image_board WHERE content LIKE ? ORDER BY reg_date DESC LIMIT ?, ?";
        } else if ("writer".equals(searchType)) {
            sql = "SELECT id, title, content, writer, password, image_file, image_original, hit, reg_date, mod_date " +
                  "FROM image_board WHERE writer LIKE ? ORDER BY reg_date DESC LIMIT ?, ?";
        } else {
            sql = "SELECT id, title, content, writer, password, image_file, image_original, hit, reg_date, mod_date " +
                  "FROM image_board WHERE title LIKE ? OR content LIKE ? ORDER BY reg_date DESC LIMIT ?, ?";
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ImageBoardDTO> boardList = new ArrayList<>();
        
        try {
            pstmt = conn.prepareStatement(sql);
            if ("title".equals(searchType) || "content".equals(searchType) || "writer".equals(searchType)) {
                pstmt.setString(1, "%" + searchKeyword + "%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, pageSize);
            } else {
                pstmt.setString(1, "%" + searchKeyword + "%");
                pstmt.setString(2, "%" + searchKeyword + "%");
                pstmt.setInt(3, start);
                pstmt.setInt(4, pageSize);
            }
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ImageBoardDTO board = new ImageBoardDTO();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setPassword(rs.getString("password"));
                board.setImageFile(rs.getString("image_file"));
                board.setImageOriginal(rs.getString("image_original"));
                board.setHit(rs.getInt("hit"));
                board.setRegDate(rs.getTimestamp("reg_date"));
                board.setModDate(rs.getTimestamp("mod_date"));
                boardList.add(board);
            }
            return boardList;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
    
    /**
     * 검색 결과 수 조회
     * @param searchType 검색 타입
     * @param searchKeyword 검색 키워드
     * @return 검색 결과 수
     */
    public int getSearchCount(String searchType, String searchKeyword) throws SQLException {
        String sql = "";
        if ("title".equals(searchType)) {
            sql = "SELECT COUNT(*) FROM image_board WHERE title LIKE ?";
        } else if ("content".equals(searchType)) {
            sql = "SELECT COUNT(*) FROM image_board WHERE content LIKE ?";
        } else if ("writer".equals(searchType)) {
            sql = "SELECT COUNT(*) FROM image_board WHERE writer LIKE ?";
        } else {
            sql = "SELECT COUNT(*) FROM image_board WHERE title LIKE ? OR content LIKE ?";
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            if ("title".equals(searchType) || "content".equals(searchType) || "writer".equals(searchType)) {
                pstmt.setString(1, "%" + searchKeyword + "%");
            } else {
                pstmt.setString(1, "%" + searchKeyword + "%");
                pstmt.setString(2, "%" + searchKeyword + "%");
            }
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
}
