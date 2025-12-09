/**
 * 사용자 관리 시스템 예제
 * CRUD 기능을 모두 포함한 실전 예제
 */
import java.sql.*;

public class UserManager {
    private Connection conn;
    
    // 데이터베이스 연결 정보
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    /**
     * 생성자: 데이터베이스 연결
     */
    public UserManager() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("데이터베이스 연결 성공");
            
            // 테이블 생성
            createTable();
            
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 오류: " + e.getMessage());
            System.out.println("실제 데이터베이스를 설정해야 합니다.");
        }
    }
    
    /**
     * 테이블 생성
     */
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(50) NOT NULL, " +
                     "age INT, " +
                     "email VARCHAR(100)" +
                     ")";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("테이블 생성 오류: " + e.getMessage());
        }
    }
    
    /**
     * CREATE: 사용자 추가
     */
    public boolean addUser(String name, int age, String email) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.out.println("사용자 추가 오류: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * READ: 모든 사용자 조회
     */
    public void getAllUsers() {
        String sql = "SELECT * FROM users ORDER BY id";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n=== 사용자 목록 ===");
            System.out.println("ID\t이름\t나이\t이메일");
            System.out.println("----------------------------------------");
            
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                
                System.out.println(id + "\t" + name + "\t" + age + "\t" + email);
            }
            
            if (!hasData) {
                System.out.println("사용자가 없습니다.");
            }
            
        } catch (SQLException e) {
            System.out.println("조회 오류: " + e.getMessage());
        }
    }
    
    /**
     * READ: ID로 사용자 조회
     */
    public void getUserById(int id) {
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
    public boolean updateUser(int id, String name, int age, String email) {
        String sql = "UPDATE users SET name = ?, age = ?, email = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.out.println("수정 오류: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * DELETE: 사용자 삭제
     */
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.out.println("삭제 오류: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 사용자 수 조회
     */
    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM users";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("카운트 조회 오류: " + e.getMessage());
        }
        
        return 0;
    }
    
    /**
     * 연결 종료
     */
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("데이터베이스 연결 종료");
            }
        } catch (SQLException e) {
            System.out.println("연결 종료 오류: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 사용자 관리 시스템 ===\n");
        
        System.out.println("주의: 실제 데이터베이스 연결이 필요합니다.");
        System.out.println("아래 코드는 실제 DB가 있을 때 실행하세요.\n");
        
        // 실제 DB 연결 시 주석 해제
        /*
        UserManager manager = new UserManager();
        
        try {
            // CREATE
            System.out.println("--- 사용자 추가 ---");
            manager.addUser("홍길동", 25, "hong@example.com");
            manager.addUser("김철수", 30, "kim@example.com");
            manager.addUser("이영희", 28, "lee@example.com");
            
            // READ
            System.out.println("\n--- 전체 사용자 조회 ---");
            manager.getAllUsers();
            
            System.out.println("\n--- 특정 사용자 조회 ---");
            manager.getUserById(1);
            
            // UPDATE
            System.out.println("\n--- 사용자 정보 수정 ---");
            manager.updateUser(1, "홍길동", 26, "hong2@example.com");
            manager.getUserById(1);
            
            // DELETE
            System.out.println("\n--- 사용자 삭제 ---");
            manager.deleteUser(2);
            manager.getAllUsers();
            
            // 통계
            System.out.println("\n--- 통계 ---");
            System.out.println("총 사용자 수: " + manager.getUserCount());
            
        } finally {
            manager.close();
        }
        */
        
        System.out.println("=== 사용자 관리 시스템 기능 ===");
        System.out.println("1. 사용자 추가 (CREATE)");
        System.out.println("2. 사용자 조회 (READ)");
        System.out.println("3. 사용자 수정 (UPDATE)");
        System.out.println("4. 사용자 삭제 (DELETE)");
        System.out.println("5. 사용자 수 조회");
    }
}

