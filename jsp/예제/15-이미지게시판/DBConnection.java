package jsp.imageboard;

import java.sql.*;

/**
 * 데이터베이스 연결 유틸리티 클래스
 */
public class DBConnection {
    // 데이터베이스 연결 정보
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jsp_image_board?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";  // 실제 비밀번호로 변경하세요
    
    /**
     * 데이터베이스 연결 가져오기
     * @return Connection 객체
     * @throws SQLException 데이터베이스 연결 오류
     * @throws ClassNotFoundException 드라이버 클래스를 찾을 수 없음
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /**
     * 리소스 해제 (Connection, PreparedStatement, ResultSet)
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 리소스 해제 (Connection, PreparedStatement)
     */
    public static void close(Connection conn, PreparedStatement pstmt) {
        close(conn, pstmt, null);
    }
    
    /**
     * 리소스 해제 (Connection만)
     */
    public static void close(Connection conn) {
        close(conn, null, null);
    }
    
    /**
     * 트랜잭션 롤백
     */
    public static void rollback(Connection conn) {
        try {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback();
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
