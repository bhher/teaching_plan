/**
 * CRUD 기본 실습 예제
 * CREATE, READ, UPDATE, DELETE 작업을 보여줍니다
 */
import java.sql.*;

public class CRUDExample {
    // 데이터베이스 연결 정보 (실제 사용 시 변경 필요)
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    /**
     * 테이블 생성 (초기 설정)
     */
    public static void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(50) NOT NULL, " +
                     "age INT, " +
                     "email VARCHAR(100)" +
                     ")";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("테이블 생성 완료 (또는 이미 존재)");
        } catch (SQLException e) {
            System.out.println("테이블 생성 오류: " + e.getMessage());
        }
    }
    
    /**
     * CREATE: 데이터 삽입
     */
    public static void insertUser(Connection conn, String name, int age, String email) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            
            int rows = pstmt.executeUpdate();
            System.out.println("삽입 완료: " + rows + "개의 행이 추가되었습니다.");
            
        } catch (SQLException e) {
            System.out.println("삽입 오류: " + e.getMessage());
        }
    }
    
    /**
     * READ: 모든 사용자 조회
     */
    public static void selectAllUsers(Connection conn) {
        String sql = "SELECT * FROM users";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n=== 사용자 목록 ===");
            System.out.println("ID\t이름\t나이\t이메일");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                
                System.out.println(id + "\t" + name + "\t" + age + "\t" + email);
            }
            
        } catch (SQLException e) {
            System.out.println("조회 오류: " + e.getMessage());
        }
    }
    
    /**
     * READ: 특정 사용자 조회
     */
    public static void selectUserById(Connection conn, int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n=== 사용자 정보 ===");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("이름: " + rs.getString("name"));
                    System.out.println("나이: " + rs.getInt("age"));
                    System.out.println("이메일: " + rs.getString("email"));
                } else {
                    System.out.println("ID " + id + "에 해당하는 사용자가 없습니다.");
                }
            }
            
        } catch (SQLException e) {
            System.out.println("조회 오류: " + e.getMessage());
        }
    }
    
    /**
     * UPDATE: 사용자 정보 수정
     */
    public static void updateUser(Connection conn, int id, String name, int age, String email) {
        String sql = "UPDATE users SET name = ?, age = ?, email = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("수정 완료: " + rows + "개의 행이 수정되었습니다.");
            } else {
                System.out.println("수정할 사용자가 없습니다.");
            }
            
        } catch (SQLException e) {
            System.out.println("수정 오류: " + e.getMessage());
        }
    }
    
    /**
     * DELETE: 사용자 삭제
     */
    public static void deleteUser(Connection conn, int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("삭제 완료: " + rows + "개의 행이 삭제되었습니다.");
            } else {
                System.out.println("삭제할 사용자가 없습니다.");
            }
            
        } catch (SQLException e) {
            System.out.println("삭제 오류: " + e.getMessage());
        }
    }
    
    /**
     * 테이블 초기화 (모든 데이터 삭제)
     */
    public static void clearTable(Connection conn) {
        String sql = "DELETE FROM users";
        
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("테이블 초기화: " + rows + "개의 행이 삭제되었습니다.");
        } catch (SQLException e) {
            System.out.println("초기화 오류: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== CRUD 기본 실습 예제 ===\n");
        
        System.out.println("주의: 실제 데이터베이스 연결이 필요합니다.");
        System.out.println("MySQL, PostgreSQL 등을 설치하고");
        System.out.println("JDBC 드라이버를 프로젝트에 추가해야 합니다.\n");
        
        // 실제 DB 연결 시 주석 해제
        /*
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("데이터베이스 연결 성공!\n");
            
            // 테이블 생성
            createTable(conn);
            
            // 테이블 초기화
            clearTable(conn);
            
            // CREATE: 데이터 삽입
            System.out.println("\n--- CREATE (삽입) ---");
            insertUser(conn, "홍길동", 25, "hong@example.com");
            insertUser(conn, "김철수", 30, "kim@example.com");
            insertUser(conn, "이영희", 28, "lee@example.com");
            
            // READ: 모든 사용자 조회
            System.out.println("\n--- READ (조회) - 전체 ---");
            selectAllUsers(conn);
            
            // READ: 특정 사용자 조회
            System.out.println("\n--- READ (조회) - 특정 사용자 ---");
            selectUserById(conn, 1);
            
            // UPDATE: 사용자 정보 수정
            System.out.println("\n--- UPDATE (수정) ---");
            updateUser(conn, 1, "홍길동", 26, "hong2@example.com");
            selectUserById(conn, 1);
            
            // DELETE: 사용자 삭제
            System.out.println("\n--- DELETE (삭제) ---");
            deleteUser(conn, 2);
            selectAllUsers(conn);
            
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 오류: " + e.getMessage());
            System.out.println("\n실제 데이터베이스를 설정해야 합니다:");
            System.out.println("1. MySQL 또는 다른 DB 설치");
            System.out.println("2. 데이터베이스 생성");
            System.out.println("3. JDBC 드라이버 추가");
            System.out.println("4. URL, USER, PASSWORD 수정");
        }
        */
        
        System.out.println("=== CRUD 작업 요약 ===");
        System.out.println("CREATE: INSERT INTO ... VALUES (...)");
        System.out.println("READ: SELECT * FROM ... WHERE ...");
        System.out.println("UPDATE: UPDATE ... SET ... WHERE ...");
        System.out.println("DELETE: DELETE FROM ... WHERE ...");
    }
}

