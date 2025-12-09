/**
 * 트랜잭션 관리 예제
 * 트랜잭션의 개념과 사용법을 보여줍니다
 */
import java.sql.*;

public class TransactionExample {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static void main(String[] args) {
        System.out.println("=== 트랜잭션 관리 예제 ===\n");
        
        System.out.println("--- 트랜잭션이란? ---");
        System.out.println("트랜잭션은 여러 SQL 문을 하나의 작업 단위로 묶는 것입니다.");
        System.out.println("모두 성공하거나 모두 실패해야 합니다 (원자성).\n");
        
        System.out.println("--- 트랜잭션의 특징 (ACID) ---");
        System.out.println("1. 원자성 (Atomicity): 모두 성공하거나 모두 실패");
        System.out.println("2. 일관성 (Consistency): 데이터 일관성 유지");
        System.out.println("3. 격리성 (Isolation): 동시 실행 시 서로 영향 없음");
        System.out.println("4. 지속성 (Durability): 커밋 후 영구 저장\n");
        
        System.out.println("--- 트랜잭션 처리 과정 ---");
        System.out.println("1. 자동 커밋 비활성화: conn.setAutoCommit(false)");
        System.out.println("2. 여러 SQL 실행");
        System.out.println("3. 성공 시 커밋: conn.commit()");
        System.out.println("4. 실패 시 롤백: conn.rollback()");
        System.out.println("5. 자동 커밋 재활성화: conn.setAutoCommit(true)\n");
        
        System.out.println("--- 트랜잭션 예제 코드 ---");
        System.out.println("try {");
        System.out.println("    conn.setAutoCommit(false);  // 자동 커밋 비활성화");
        System.out.println("    ");
        System.out.println("    // 여러 SQL 실행");
        System.out.println("    pstmt1.executeUpdate();");
        System.out.println("    pstmt2.executeUpdate();");
        System.out.println("    pstmt3.executeUpdate();");
        System.out.println("    ");
        System.out.println("    conn.commit();  // 모든 작업 성공 시 커밋");
        System.out.println("    ");
        System.out.println("} catch (SQLException e) {");
        System.out.println("    conn.rollback();  // 오류 발생 시 롤백");
        System.out.println("    e.printStackTrace();");
        System.out.println("} finally {");
        System.out.println("    conn.setAutoCommit(true);  // 자동 커밋 재활성화");
        System.out.println("}\n");
        
        System.out.println("--- 트랜잭션 사용 예제: 계좌 이체 ---");
        System.out.println("계좌 A에서 계좌 B로 1000원 이체:");
        System.out.println("1. 계좌 A에서 1000원 차감");
        System.out.println("2. 계좌 B에 1000원 추가");
        System.out.println("→ 두 작업이 모두 성공해야 함\n");
        
        System.out.println("--- 자동 커밋 vs 수동 커밋 ---");
        System.out.println("자동 커밋 (기본값):");
        System.out.println("  - 각 SQL 문이 자동으로 커밋됨");
        System.out.println("  - conn.setAutoCommit(true)");
        System.out.println();
        System.out.println("수동 커밋:");
        System.out.println("  - 여러 SQL 문을 하나의 트랜잭션으로 처리");
        System.out.println("  - conn.setAutoCommit(false)");
        System.out.println("  - conn.commit() 또는 conn.rollback() 호출\n");
        
        System.out.println("=== 트랜잭션의 중요성 ===");
        System.out.println("1. 데이터 일관성 보장");
        System.out.println("2. 부분 실패 방지");
        System.out.println("3. 동시성 제어");
        System.out.println("4. 데이터 무결성 유지");
    }
    
    /**
     * 트랜잭션 예제: 계좌 이체 시뮬레이션
     */
    public static void transferMoney(Connection conn, int fromAccount, 
                                     int toAccount, double amount) {
        try {
            conn.setAutoCommit(false);  // 자동 커밋 비활성화
            
            // 계좌 A에서 차감
            String sql1 = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
                pstmt1.setDouble(1, amount);
                pstmt1.setInt(2, fromAccount);
                int rows1 = pstmt1.executeUpdate();
                
                if (rows1 == 0) {
                    throw new SQLException("출금 계좌를 찾을 수 없습니다.");
                }
            }
            
            // 계좌 B에 추가
            String sql2 = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                pstmt2.setDouble(1, amount);
                pstmt2.setInt(2, toAccount);
                int rows2 = pstmt2.executeUpdate();
                
                if (rows2 == 0) {
                    throw new SQLException("입금 계좌를 찾을 수 없습니다.");
                }
            }
            
            // 모든 작업 성공 시 커밋
            conn.commit();
            System.out.println("이체 성공: " + amount + "원");
            
        } catch (SQLException e) {
            try {
                conn.rollback();  // 오류 발생 시 롤백
                System.out.println("이체 실패: 롤백됨 - " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("롤백 오류: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);  // 자동 커밋 재활성화
            } catch (SQLException e) {
                System.out.println("자동 커밋 설정 오류: " + e.getMessage());
            }
        }
    }
}

