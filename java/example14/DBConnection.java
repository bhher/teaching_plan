/**
 * 데이터베이스 연결 예제
 * DB 연결 구조와 연결 방법을 보여줍니다
 */
import java.sql.*;

public class DBConnection {
    // 데이터베이스 연결 정보 (실제 사용 시 변경 필요)
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    // MySQL 드라이버 클래스명
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
     * 데이터베이스 연결 생성
     */
    public static Connection getConnection() {
        Connection conn = null;
        
        try {
            // 방법 1: 드라이버 명시적 로드 (Java 6 이전)
            // Class.forName(DRIVER);
            
            // 방법 2: 자동 드라이버 로드 (Java 6+)
            // DriverManager가 자동으로 드라이버를 찾음
            
            // 연결 생성
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (conn != null) {
                System.out.println("데이터베이스 연결 성공!");
            } else {
                System.out.println("데이터베이스 연결 실패!");
            }
            
        } catch (SQLException e) {
            System.out.println("연결 오류: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
        }
        
        return conn;
    }
    
    /**
     * 연결 정보 확인
     */
    public static void printConnectionInfo(Connection conn) {
        if (conn == null) {
            System.out.println("연결이 없습니다.");
            return;
        }
        
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            
            System.out.println("\n=== 데이터베이스 정보 ===");
            System.out.println("데이터베이스 제품명: " + metaData.getDatabaseProductName());
            System.out.println("데이터베이스 버전: " + metaData.getDatabaseProductVersion());
            System.out.println("드라이버 이름: " + metaData.getDriverName());
            System.out.println("드라이버 버전: " + metaData.getDriverVersion());
            System.out.println("URL: " + metaData.getURL());
            System.out.println("사용자명: " + metaData.getUserName());
            
            // 연결 유효성 확인
            boolean isValid = conn.isValid(5);
            System.out.println("연결 유효성: " + isValid);
            
            // 자동 커밋 상태
            boolean autoCommit = conn.getAutoCommit();
            System.out.println("자동 커밋: " + autoCommit);
            
        } catch (SQLException e) {
            System.out.println("정보 조회 오류: " + e.getMessage());
        }
    }
    
    /**
     * 연결 테스트
     */
    public static void testConnection() {
        System.out.println("=== 데이터베이스 연결 테스트 ===\n");
        
        Connection conn = null;
        
        try {
            // 연결 생성
            conn = getConnection();
            
            if (conn != null) {
                // 연결 정보 출력
                printConnectionInfo(conn);
                
                // 간단한 쿼리 실행 테스트
                System.out.println("\n=== 쿼리 실행 테스트 ===");
                try (Statement stmt = conn.createStatement()) {
                    // SELECT 1은 대부분의 DB에서 작동하는 간단한 쿼리
                    ResultSet rs = stmt.executeQuery("SELECT 1 AS test");
                    if (rs.next()) {
                        System.out.println("쿼리 실행 성공: " + rs.getInt("test"));
                    }
                }
            }
            
        } catch (SQLException e) {
            System.out.println("오류 발생: " + e.getMessage());
        } finally {
            // 리소스 해제
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("\n연결 종료");
                } catch (SQLException e) {
                    System.out.println("연결 종료 오류: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * try-with-resources를 사용한 연결
     */
    public static void testConnectionWithResources() {
        System.out.println("\n=== try-with-resources를 사용한 연결 ===\n");
        
        // try-with-resources: 자동으로 close() 호출
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            System.out.println("연결 성공!");
            printConnectionInfo(conn);
            
            // 간단한 쿼리 실행
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1 AS test")) {
                
                if (rs.next()) {
                    System.out.println("\n쿼리 결과: " + rs.getInt("test"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("연결 오류: " + e.getMessage());
            System.out.println("\n주의: 실제 데이터베이스가 필요합니다.");
            System.out.println("1. MySQL, PostgreSQL 등을 설치");
            System.out.println("2. 데이터베이스 생성");
            System.out.println("3. JDBC 드라이버를 프로젝트에 추가");
            System.out.println("4. URL, USER, PASSWORD를 실제 값으로 변경");
        }
        
        // 자동으로 conn.close() 호출됨
    }
    
    public static void main(String[] args) {
        System.out.println("=== DB 연결 구조 예제 ===\n");
        
        System.out.println("--- 연결 과정 ---");
        System.out.println("1. 드라이버 로드 (자동 또는 수동)");
        System.out.println("2. DriverManager.getConnection() 호출");
        System.out.println("3. Connection 객체 반환");
        System.out.println("4. SQL 실행 및 결과 처리");
        System.out.println("5. Connection.close()로 연결 종료\n");
        
        // 연결 테스트 (실제 DB가 없으면 오류 발생)
        // testConnection();
        
        // try-with-resources 테스트
        testConnectionWithResources();
        
        System.out.println("\n=== 연결 정보 설정 방법 ===");
        System.out.println("URL: jdbc:mysql://hostname:port/database");
        System.out.println("USER: 데이터베이스 사용자명");
        System.out.println("PASSWORD: 데이터베이스 비밀번호");
    }
}

