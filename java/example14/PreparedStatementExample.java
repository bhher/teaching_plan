/**
 * PreparedStatement 예제
 * PreparedStatement의 장점과 사용법을 보여줍니다
 */
import java.sql.*;

public class PreparedStatementExample {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static void main(String[] args) {
        System.out.println("=== PreparedStatement 예제 ===\n");
        
        System.out.println("--- PreparedStatement란? ---");
        System.out.println("PreparedStatement는 미리 컴파일된 SQL 문을 사용하는 인터페이스입니다.");
        System.out.println("Statement보다 성능이 좋고 SQL 인젝션 공격을 방지합니다.\n");
        
        System.out.println("--- Statement vs PreparedStatement ---");
        System.out.println("Statement:");
        System.out.println("  - 매번 SQL 문을 컴파일");
        System.out.println("  - SQL 인젝션 취약");
        System.out.println("  - 예: stmt.executeQuery(\"SELECT * FROM users WHERE id = \" + id);");
        System.out.println();
        System.out.println("PreparedStatement:");
        System.out.println("  - 한 번 컴파일 후 재사용");
        System.out.println("  - SQL 인젝션 방지");
        System.out.println("  - 예: pstmt.setInt(1, id);");
        System.out.println();
        
        System.out.println("--- PreparedStatement 사용법 ---");
        System.out.println("1. SQL 문 준비 (플레이스홀더 ? 사용)");
        System.out.println("   String sql = \"SELECT * FROM users WHERE id = ?\";");
        System.out.println();
        System.out.println("2. PreparedStatement 생성");
        System.out.println("   PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println();
        System.out.println("3. 파라미터 설정");
        System.out.println("   pstmt.setInt(1, 1);  // 첫 번째 ?에 값 설정");
        System.out.println("   pstmt.setString(2, \"홍길동\");  // 두 번째 ?에 값 설정");
        System.out.println();
        System.out.println("4. SQL 실행");
        System.out.println("   ResultSet rs = pstmt.executeQuery();");
        System.out.println();
        
        System.out.println("--- 파라미터 설정 메서드 ---");
        System.out.println("setInt(int parameterIndex, int x)");
        System.out.println("setString(int parameterIndex, String x)");
        System.out.println("setDouble(int parameterIndex, double x)");
        System.out.println("setBoolean(int parameterIndex, boolean x)");
        System.out.println("setDate(int parameterIndex, Date x)");
        System.out.println("setNull(int parameterIndex, int sqlType)");
        System.out.println();
        
        System.out.println("--- SQL 인젝션 방지 예제 ---");
        System.out.println("나쁜 예 (Statement):");
        System.out.println("  String sql = \"SELECT * FROM users WHERE name = '\" + name + \"'\";");
        System.out.println("  // name에 \"' OR '1'='1\" 입력 시 모든 데이터 조회됨");
        System.out.println();
        System.out.println("좋은 예 (PreparedStatement):");
        System.out.println("  String sql = \"SELECT * FROM users WHERE name = ?\";");
        System.out.println("  PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println("  pstmt.setString(1, name);");
        System.out.println("  // SQL 인젝션 공격 방지");
        System.out.println();
        
        System.out.println("--- PreparedStatement 재사용 예제 ---");
        System.out.println("같은 SQL 문을 여러 번 실행할 때 효율적:");
        System.out.println();
        System.out.println("String sql = \"INSERT INTO users (name, age) VALUES (?, ?)\";");
        System.out.println("PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println();
        System.out.println("// 첫 번째 사용자");
        System.out.println("pstmt.setString(1, \"홍길동\");");
        System.out.println("pstmt.setInt(2, 25);");
        System.out.println("pstmt.executeUpdate();");
        System.out.println();
        System.out.println("// 두 번째 사용자 (같은 PreparedStatement 재사용)");
        System.out.println("pstmt.setString(1, \"김철수\");");
        System.out.println("pstmt.setInt(2, 30);");
        System.out.println("pstmt.executeUpdate();");
        System.out.println();
        
        System.out.println("=== PreparedStatement의 장점 ===");
        System.out.println("1. 성능 향상: 한 번 컴파일 후 재사용");
        System.out.println("2. 보안: SQL 인젝션 공격 방지");
        System.out.println("3. 가독성: 파라미터를 명확하게 설정");
        System.out.println("4. 타입 안정성: 타입 체크 가능");
    }
}

