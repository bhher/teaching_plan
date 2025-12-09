/**
 * Java + DB 흐름 이해 예제
 * JDBC 사용의 전체 흐름을 단계별로 보여줍니다
 */
import java.sql.*;

public class JDBCFlow {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static void main(String[] args) {
        System.out.println("=== Java + DB 흐름 이해 ===\n");
        
        System.out.println("--- 전체 흐름도 ---");
        System.out.println("1. 드라이버 로드");
        System.out.println("   ↓");
        System.out.println("2. 데이터베이스 연결 (Connection)");
        System.out.println("   ↓");
        System.out.println("3. SQL 문 준비 (PreparedStatement)");
        System.out.println("   ↓");
        System.out.println("4. SQL 실행");
        System.out.println("   ↓");
        System.out.println("5. 결과 처리 (ResultSet)");
        System.out.println("   ↓");
        System.out.println("6. 리소스 해제 (close)\n");
        
        // 실제 DB 연결 시 주석 해제
        /*
        demonstrateJDBCFlow();
        */
        
        printDetailedFlow();
    }
    
    /**
     * JDBC 흐름 시연
     */
    public static void demonstrateJDBCFlow() {
        System.out.println("--- JDBC 흐름 시연 ---\n");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // 1단계: 드라이버 로드
            System.out.println("1단계: 드라이버 로드");
            // Class.forName("com.mysql.cj.jdbc.Driver");  // Java 6+에서는 자동
            System.out.println("   → 드라이버 로드 완료\n");
            
            // 2단계: 데이터베이스 연결
            System.out.println("2단계: 데이터베이스 연결");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("   → Connection 객체 생성 완료");
            System.out.println("   → 연결 상태: " + (conn != null ? "연결됨" : "연결 안 됨") + "\n");
            
            // 3단계: SQL 문 준비
            System.out.println("3단계: SQL 문 준비");
            String sql = "SELECT * FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
            System.out.println("   → PreparedStatement 생성 완료");
            System.out.println("   → SQL: " + sql);
            System.out.println("   → 파라미터: id = 1\n");
            
            // 4단계: SQL 실행
            System.out.println("4단계: SQL 실행");
            rs = pstmt.executeQuery();
            System.out.println("   → executeQuery() 실행 완료");
            System.out.println("   → ResultSet 객체 생성\n");
            
            // 5단계: 결과 처리
            System.out.println("5단계: 결과 처리");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("   → 결과: id=" + id + ", name=" + name);
            }
            System.out.println("   → 결과 처리 완료\n");
            
        } catch (SQLException e) {
            System.out.println("오류 발생: " + e.getMessage());
        } finally {
            // 6단계: 리소스 해제
            System.out.println("6단계: 리소스 해제");
            try {
                if (rs != null) {
                    rs.close();
                    System.out.println("   → ResultSet 닫기 완료");
                }
                if (pstmt != null) {
                    pstmt.close();
                    System.out.println("   → PreparedStatement 닫기 완료");
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("   → Connection 닫기 완료");
                }
            } catch (SQLException e) {
                System.out.println("리소스 해제 오류: " + e.getMessage());
            }
        }
    }
    
    /**
     * 상세 흐름 설명
     */
    public static void printDetailedFlow() {
        System.out.println("--- 단계별 상세 설명 ---\n");
        
        System.out.println("1단계: 드라이버 로드");
        System.out.println("  - JDBC 드라이버 클래스를 메모리에 로드");
        System.out.println("  - Class.forName(\"com.mysql.cj.jdbc.Driver\")");
        System.out.println("  - Java 6+에서는 자동으로 드라이버를 찾음");
        System.out.println();
        
        System.out.println("2단계: 데이터베이스 연결");
        System.out.println("  - DriverManager.getConnection() 호출");
        System.out.println("  - URL, 사용자명, 비밀번호로 연결");
        System.out.println("  - Connection 객체 반환");
        System.out.println("  - 예: Connection conn = DriverManager.getConnection(url, user, password);");
        System.out.println();
        
        System.out.println("3단계: SQL 문 준비");
        System.out.println("  - PreparedStatement 생성");
        System.out.println("  - SQL 문에 플레이스홀더(?) 사용");
        System.out.println("  - 파라미터 설정");
        System.out.println("  - 예: PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println("  - 예: pstmt.setInt(1, id);");
        System.out.println();
        
        System.out.println("4단계: SQL 실행");
        System.out.println("  - SELECT: executeQuery() → ResultSet 반환");
        System.out.println("  - INSERT/UPDATE/DELETE: executeUpdate() → 영향받은 행 수 반환");
        System.out.println("  - 예: ResultSet rs = pstmt.executeQuery();");
        System.out.println();
        
        System.out.println("5단계: 결과 처리");
        System.out.println("  - ResultSet.next()로 다음 행 이동");
        System.out.println("  - getInt(), getString() 등으로 값 가져오기");
        System.out.println("  - 예: while (rs.next()) { String name = rs.getString(\"name\"); }");
        System.out.println();
        
        System.out.println("6단계: 리소스 해제");
        System.out.println("  - ResultSet.close()");
        System.out.println("  - PreparedStatement.close()");
        System.out.println("  - Connection.close()");
        System.out.println("  - try-with-resources 사용 권장");
        System.out.println();
        
        System.out.println("--- try-with-resources 예제 ---");
        System.out.println("try (Connection conn = DriverManager.getConnection(...);");
        System.out.println("     PreparedStatement pstmt = conn.prepareStatement(...);");
        System.out.println("     ResultSet rs = pstmt.executeQuery()) {");
        System.out.println("    ");
        System.out.println("    // SQL 실행 및 결과 처리");
        System.out.println("    while (rs.next()) {");
        System.out.println("        // 결과 처리");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("} catch (SQLException e) {");
        System.out.println("    e.printStackTrace();");
        System.out.println("}");
        System.out.println("// 자동으로 close() 호출됨\n");
        
        System.out.println("=== JDBC 사용 시 주의사항 ===");
        System.out.println("1. 리소스 관리: 항상 close() 호출");
        System.out.println("2. 예외 처리: SQLException 반드시 처리");
        System.out.println("3. SQL 인젝션 방지: PreparedStatement 사용");
        System.out.println("4. 트랜잭션 관리: 여러 작업 시 트랜잭션 사용");
        System.out.println("5. 연결 풀: 실제 운영 환경에서는 연결 풀 사용 권장");
    }
}

