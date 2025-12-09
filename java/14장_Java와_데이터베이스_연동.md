# 14장. Java와 데이터베이스 연동

## JDBC 개요

### JDBC란?

**JDBC(Java Database Connectivity)**는 Java에서 데이터베이스에 접근하기 위한 API입니다. Java 프로그램과 데이터베이스를 연결하는 표준 인터페이스를 제공합니다.

### JDBC의 역할

1. **데이터베이스 연결**: Java 프로그램과 DB를 연결
2. **SQL 실행**: SQL 문을 실행하고 결과를 받음
3. **결과 처리**: 쿼리 결과를 Java 객체로 변환
4. **트랜잭션 관리**: 데이터 일관성 보장

### JDBC 아키텍처

```
┌─────────────────┐
│  Java Application│
├─────────────────┤
│   JDBC API      │  ← Java 표준 인터페이스
├─────────────────┤
│  JDBC Driver    │  ← DB별 드라이버
├─────────────────┤
│   Database      │  ← 실제 데이터베이스
└─────────────────┘
```

### JDBC 드라이버

JDBC 드라이버는 데이터베이스별로 제공되는 구현체입니다.

- **MySQL**: `mysql-connector-java.jar`
- **Oracle**: `ojdbc.jar`
- **PostgreSQL**: `postgresql.jar`
- **SQLite**: `sqlite-jdbc.jar`

---

## DB 연결 구조

### JDBC 연결 과정

1. **드라이버 로드**: JDBC 드라이버 클래스 로드
2. **연결 생성**: `Connection` 객체 생성
3. **SQL 실행**: `Statement` 또는 `PreparedStatement` 사용
4. **결과 처리**: `ResultSet`으로 결과 처리
5. **리소스 해제**: 연결, Statement, ResultSet 닫기

### 기본 연결 코드 구조

```java
import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // 1. 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. 연결 생성
            String url = "jdbc:mysql://localhost:3306/mydb";
            String user = "root";
            String password = "password";
            conn = DriverManager.getConnection(url, user, password);
            
            // 3. SQL 실행
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");
            
            // 4. 결과 처리
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 리소스 해제
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Connection (연결)

데이터베이스와의 연결을 나타내는 인터페이스입니다.

```java
// 연결 생성
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", 
    "username", 
    "password"
);

// 연결 확인
boolean isValid = conn.isValid(5);  // 5초 내 유효한지 확인

// 자동 커밋 설정
conn.setAutoCommit(false);  // 트랜잭션 수동 관리

// 연결 닫기
conn.close();
```

### Statement

SQL 문을 실행하는 인터페이스입니다.

#### Statement

```java
Statement stmt = conn.createStatement();

// SELECT 쿼리
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// INSERT, UPDATE, DELETE
int rows = stmt.executeUpdate("INSERT INTO users VALUES (1, '홍길동')");
```

#### PreparedStatement (권장)

미리 컴파일된 SQL 문을 사용합니다. SQL 인젝션 공격을 방지하고 성능이 좋습니다.

```java
String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
PreparedStatement pstmt = conn.prepareStatement(sql);

pstmt.setString(1, "홍길동");  // 첫 번째 ?에 값 설정
pstmt.setInt(2, 25);           // 두 번째 ?에 값 설정

int rows = pstmt.executeUpdate();
```

### ResultSet

쿼리 결과를 담는 인터페이스입니다.

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

while (rs.next()) {
    // 컬럼명으로 접근
    int id = rs.getInt("id");
    String name = rs.getString("name");
    
    // 인덱스로 접근 (1부터 시작)
    int age = rs.getInt(2);
    
    System.out.println(id + ", " + name + ", " + age);
}
```

---

## CRUD 기본 실습

### CREATE (생성)

데이터를 삽입하는 작업입니다.

```java
public void insertUser(String name, int age) {
    String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        
        int rows = pstmt.executeUpdate();
        System.out.println(rows + "개의 행이 삽입되었습니다.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

### READ (읽기)

데이터를 조회하는 작업입니다.

```java
public void selectAllUsers() {
    String sql = "SELECT * FROM users";
    
    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            
            System.out.println(id + ", " + name + ", " + age);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

### UPDATE (수정)

데이터를 수정하는 작업입니다.

```java
public void updateUser(int id, String name, int age) {
    String sql = "UPDATE users SET name = ?, age = ? WHERE id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setInt(3, id);
        
        int rows = pstmt.executeUpdate();
        System.out.println(rows + "개의 행이 수정되었습니다.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

### DELETE (삭제)

데이터를 삭제하는 작업입니다.

```java
public void deleteUser(int id) {
    String sql = "DELETE FROM users WHERE id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        
        int rows = pstmt.executeUpdate();
        System.out.println(rows + "개의 행이 삭제되었습니다.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

---

## Java + DB 흐름 이해

### 전체 흐름도

```
1. 드라이버 로드
   ↓
2. 데이터베이스 연결 (Connection)
   ↓
3. SQL 문 준비 (PreparedStatement)
   ↓
4. SQL 실행
   ↓
5. 결과 처리 (ResultSet)
   ↓
6. 리소스 해제 (close)
```

### 단계별 상세 설명

#### 1단계: 드라이버 로드

```java
Class.forName("com.mysql.cj.jdbc.Driver");
// 또는 (Java 6+)
// DriverManager가 자동으로 드라이버를 찾음
```

#### 2단계: 데이터베이스 연결

```java
String url = "jdbc:mysql://localhost:3306/mydb";
String user = "root";
String password = "password";

Connection conn = DriverManager.getConnection(url, user, password);
```

#### 3단계: SQL 문 준비

```java
// PreparedStatement 사용 (권장)
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, 1);
```

#### 4단계: SQL 실행

```java
// SELECT 쿼리
ResultSet rs = pstmt.executeQuery();

// INSERT, UPDATE, DELETE
int rows = pstmt.executeUpdate();
```

#### 5단계: 결과 처리

```java
while (rs.next()) {
    // 결과 처리
    String name = rs.getString("name");
}
```

#### 6단계: 리소스 해제

```java
rs.close();
pstmt.close();
conn.close();
```

### try-with-resources 사용 (권장)

Java 7부터는 try-with-resources를 사용하여 자동으로 리소스를 해제할 수 있습니다.

```java
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql);
     ResultSet rs = pstmt.executeQuery()) {
    
    // SQL 실행 및 결과 처리
    while (rs.next()) {
        // 결과 처리
    }
    
} catch (SQLException e) {
    e.printStackTrace();
}
// 자동으로 close() 호출됨
```

---

## 트랜잭션 관리

### 트랜잭션이란?

**트랜잭션**은 여러 SQL 문을 하나의 작업 단위로 묶는 것입니다. 모두 성공하거나 모두 실패해야 합니다.

### 트랜잭션 처리

```java
try {
    conn.setAutoCommit(false);  // 자동 커밋 비활성화
    
    // 여러 SQL 실행
    pstmt1.executeUpdate();
    pstmt2.executeUpdate();
    pstmt3.executeUpdate();
    
    conn.commit();  // 모든 작업 성공 시 커밋
    
} catch (SQLException e) {
    conn.rollback();  // 오류 발생 시 롤백
    e.printStackTrace();
} finally {
    conn.setAutoCommit(true);  // 자동 커밋 재활성화
}
```

---

## JDBC 주요 클래스와 인터페이스

### DriverManager

데이터베이스 드라이버를 관리하고 연결을 생성합니다.

```java
Connection conn = DriverManager.getConnection(url, user, password);
```

### Connection

데이터베이스와의 연결을 나타냅니다.

```java
// Statement 생성
Statement stmt = conn.createStatement();
PreparedStatement pstmt = conn.prepareStatement(sql);

// 트랜잭션 관리
conn.setAutoCommit(false);
conn.commit();
conn.rollback();

// 연결 닫기
conn.close();
```

### Statement / PreparedStatement

SQL 문을 실행합니다.

```java
// Statement
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
int rows = stmt.executeUpdate("INSERT INTO users VALUES (...)");

// PreparedStatement (권장)
PreparedStatement pstmt = conn.prepareStatement(
    "SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, 1);
ResultSet rs = pstmt.executeQuery();
```

### ResultSet

쿼리 결과를 담습니다.

```java
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    Date date = rs.getDate("created_at");
}

// 특정 타입으로 가져오기
rs.getInt(columnIndex);
rs.getString(columnName);
rs.getDouble(columnIndex);
rs.getDate(columnName);
rs.getTimestamp(columnName);
```

---

## 실습 예제

### 예제 1: 사용자 관리 시스템

```java
import java.sql.*;

public class UserManager {
    private Connection conn;
    
    public UserManager() {
        // 데이터베이스 연결
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "password";
        
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // CREATE
    public void insertUser(String name, int age) {
        String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // READ
    public void selectAllUsers() {
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + 
                    rs.getString("name") + ", " + 
                    rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // UPDATE
    public void updateUser(int id, String name, int age) {
        String sql = "UPDATE users SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // DELETE
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

---

## JDBC URL 형식

### MySQL

```
jdbc:mysql://hostname:port/database?useSSL=false&serverTimezone=UTC
```

예: `jdbc:mysql://localhost:3306/mydb`

### Oracle

```
jdbc:oracle:thin:@hostname:port:database
```

예: `jdbc:oracle:thin:@localhost:1521:xe`

### PostgreSQL

```
jdbc:postgresql://hostname:port/database
```

예: `jdbc:postgresql://localhost:5432/mydb`

### SQLite

```
jdbc:sqlite:database.db
```

예: `jdbc:sqlite:mydb.db`

---

## JDBC 사용 시 주의사항

### 1. 리소스 관리

항상 리소스를 닫아야 합니다.

```java
// 나쁜 예
Connection conn = DriverManager.getConnection(url, user, password);
// close() 호출 안 함

// 좋은 예
try (Connection conn = DriverManager.getConnection(url, user, password)) {
    // 사용
}  // 자동으로 close()
```

### 2. SQL 인젝션 방지

`PreparedStatement`를 사용하여 SQL 인젝션을 방지합니다.

```java
// 나쁜 예: SQL 인젝션 취약
String sql = "SELECT * FROM users WHERE name = '" + name + "'";

// 좋은 예: PreparedStatement 사용
String sql = "SELECT * FROM users WHERE name = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, name);
```

### 3. 예외 처리

`SQLException`을 반드시 처리해야 합니다.

```java
try {
    // JDBC 코드
} catch (SQLException e) {
    e.printStackTrace();
    // 또는 로깅
}
```

### 4. 트랜잭션 관리

여러 작업을 하나의 트랜잭션으로 묶어야 할 때는 자동 커밋을 비활성화합니다.

```java
conn.setAutoCommit(false);
try {
    // 여러 SQL 실행
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
}
```

---

## 연습 문제

1. **DB 연결**
   - 데이터베이스에 연결하는 프로그램을 작성하세요.

2. **CREATE**
   - 사용자 정보를 데이터베이스에 삽입하는 프로그램을 작성하세요.

3. **READ**
   - 데이터베이스에서 모든 사용자 정보를 조회하는 프로그램을 작성하세요.

4. **UPDATE**
   - 특정 사용자의 정보를 수정하는 프로그램을 작성하세요.

5. **DELETE**
   - 특정 사용자를 삭제하는 프로그램을 작성하세요.

6. **CRUD 종합**
   - 사용자 관리 시스템을 완성하세요 (생성, 조회, 수정, 삭제).

---

## 다음 장 예고

다음 장에서는 실전 프로젝트를 통해 지금까지 배운 내용을 종합적으로 활용하는 방법을 학습합니다.

