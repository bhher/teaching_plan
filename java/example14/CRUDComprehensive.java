/**
 * CRUD 종합 예제
 * 사용자 관리 시스템의 완전한 CRUD 구현
 */
import java.sql.*;
import java.util.ArrayList;

// 사용자 클래스
class User {
    private int id;
    private String name;
    private int age;
    private String email;
    
    public User(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

public class CRUDComprehensive {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    private Connection conn;
    
    public CRUDComprehensive() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            createTable();
        } catch (SQLException e) {
            System.out.println("연결 오류: " + e.getMessage());
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
    public int createUser(String name, int age, String email) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            
            int rows = pstmt.executeUpdate();
            
            // 생성된 ID 가져오기
            if (rows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            
            return -1;
            
        } catch (SQLException e) {
            System.out.println("사용자 추가 오류: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * READ: 모든 사용자 조회
     */
    public ArrayList<User> readAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                
                users.add(new User(id, name, age, email));
            }
            
        } catch (SQLException e) {
            System.out.println("조회 오류: " + e.getMessage());
        }
        
        return users;
    }
    
    /**
     * READ: ID로 사용자 조회
     */
    public User readUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.out.println("조회 오류: " + e.getMessage());
        }
        
        return null;
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
     * 테이블 초기화
     */
    public void clearTable() {
        String sql = "DELETE FROM users";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("테이블 초기화 완료");
        } catch (SQLException e) {
            System.out.println("초기화 오류: " + e.getMessage());
        }
    }
    
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("연결 종료 오류: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== CRUD 종합 예제 ===\n");
        
        System.out.println("주의: 실제 데이터베이스 연결이 필요합니다.");
        System.out.println("아래 코드는 실제 DB가 있을 때 실행하세요.\n");
        
        // 실제 DB 연결 시 주석 해제
        /*
        CRUDComprehensive crud = new CRUDComprehensive();
        
        try {
            // 테이블 초기화
            crud.clearTable();
            
            // CREATE
            System.out.println("--- CREATE (생성) ---");
            int id1 = crud.createUser("홍길동", 25, "hong@example.com");
            int id2 = crud.createUser("김철수", 30, "kim@example.com");
            int id3 = crud.createUser("이영희", 28, "lee@example.com");
            System.out.println("생성된 ID: " + id1 + ", " + id2 + ", " + id3);
            
            // READ
            System.out.println("\n--- READ (조회) - 전체 ---");
            ArrayList<User> users = crud.readAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
            
            System.out.println("\n--- READ (조회) - 특정 사용자 ---");
            User user = crud.readUserById(id1);
            if (user != null) {
                System.out.println(user);
            }
            
            // UPDATE
            System.out.println("\n--- UPDATE (수정) ---");
            boolean updated = crud.updateUser(id1, "홍길동", 26, "hong2@example.com");
            System.out.println("수정 결과: " + updated);
            System.out.println(crud.readUserById(id1));
            
            // DELETE
            System.out.println("\n--- DELETE (삭제) ---");
            boolean deleted = crud.deleteUser(id2);
            System.out.println("삭제 결과: " + deleted);
            
            System.out.println("\n--- 최종 사용자 목록 ---");
            users = crud.readAllUsers();
            for (User u : users) {
                System.out.println(u);
            }
            
        } finally {
            crud.close();
        }
        */
        
        System.out.println("=== CRUD 작업 요약 ===");
        System.out.println("CREATE: INSERT INTO ... VALUES (...)");
        System.out.println("READ: SELECT * FROM ... WHERE ...");
        System.out.println("UPDATE: UPDATE ... SET ... WHERE ...");
        System.out.println("DELETE: DELETE FROM ... WHERE ...");
        System.out.println("\n모든 작업은 PreparedStatement를 사용하여");
        System.out.println("안전하고 효율적으로 처리됩니다.");
    }
}

