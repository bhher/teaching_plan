/**
 * JDBC 기본을 설명하는 예제
 * JDBC 연결 과정과 기본 사용법을 보여줍니다
 */
import java.sql.*;

public class JDBCBasics {
    public static void main(String[] args) {
        System.out.println("=== JDBC 기본 예제 ===\n");
        
        System.out.println("--- JDBC란? ---");
        System.out.println("JDBC(Java Database Connectivity)는");
        System.out.println("Java에서 데이터베이스에 접근하기 위한 API입니다.\n");
        
        System.out.println("--- JDBC 연결 과정 ---");
        System.out.println("1. 드라이버 로드");
        System.out.println("2. 데이터베이스 연결 (Connection)");
        System.out.println("3. SQL 문 준비 (PreparedStatement)");
        System.out.println("4. SQL 실행");
        System.out.println("5. 결과 처리 (ResultSet)");
        System.out.println("6. 리소스 해제 (close)\n");
        
        System.out.println("--- JDBC 아키텍처 ---");
        System.out.println("Java Application");
        System.out.println("    ↓");
        System.out.println("JDBC API (표준 인터페이스)");
        System.out.println("    ↓");
        System.out.println("JDBC Driver (DB별 구현)");
        System.out.println("    ↓");
        System.out.println("Database\n");
        
        System.out.println("--- JDBC 드라이버 종류 ---");
        System.out.println("MySQL: mysql-connector-java.jar");
        System.out.println("Oracle: ojdbc.jar");
        System.out.println("PostgreSQL: postgresql.jar");
        System.out.println("SQLite: sqlite-jdbc.jar\n");
        
        System.out.println("--- JDBC URL 형식 ---");
        System.out.println("MySQL: jdbc:mysql://hostname:port/database");
        System.out.println("Oracle: jdbc:oracle:thin:@hostname:port:database");
        System.out.println("PostgreSQL: jdbc:postgresql://hostname:port/database");
        System.out.println("SQLite: jdbc:sqlite:database.db\n");
        
        System.out.println("--- 기본 연결 코드 구조 ---");
        System.out.println("try {");
        System.out.println("    // 1. 드라이버 로드");
        System.out.println("    Class.forName(\"com.mysql.cj.jdbc.Driver\");");
        System.out.println("    ");
        System.out.println("    // 2. 연결 생성");
        System.out.println("    Connection conn = DriverManager.getConnection(");
        System.out.println("        \"jdbc:mysql://localhost:3306/mydb\",");
        System.out.println("        \"username\",");
        System.out.println("        \"password\");");
        System.out.println("    ");
        System.out.println("    // 3. SQL 실행");
        System.out.println("    Statement stmt = conn.createStatement();");
        System.out.println("    ResultSet rs = stmt.executeQuery(\"SELECT * FROM users\");");
        System.out.println("    ");
        System.out.println("    // 4. 결과 처리");
        System.out.println("    while (rs.next()) {");
        System.out.println("        // 결과 처리");
        System.out.println("    }");
        System.out.println("} catch (SQLException e) {");
        System.out.println("    e.printStackTrace();");
        System.out.println("} finally {");
        System.out.println("    // 5. 리소스 해제");
        System.out.println("    rs.close();");
        System.out.println("    stmt.close();");
        System.out.println("    conn.close();");
        System.out.println("}\n");
        
        System.out.println("--- try-with-resources 사용 (권장) ---");
        System.out.println("try (Connection conn = DriverManager.getConnection(...);");
        System.out.println("     PreparedStatement pstmt = conn.prepareStatement(...);");
        System.out.println("     ResultSet rs = pstmt.executeQuery()) {");
        System.out.println("    // 사용");
        System.out.println("}  // 자동으로 close() 호출\n");
        
        System.out.println("주의: 실제 데이터베이스가 필요합니다.");
        System.out.println("MySQL, PostgreSQL, SQLite 등을 설치하고");
        System.out.println("해당 JDBC 드라이버를 프로젝트에 추가해야 합니다.");
    }
}

