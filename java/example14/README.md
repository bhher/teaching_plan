# 14장 예제 파일 실행 방법

## 주의사항

**이 예제들은 실제 데이터베이스가 필요합니다!**

실행하기 전에 다음을 준비해야 합니다:
1. 데이터베이스 설치 (MySQL, PostgreSQL, SQLite 등)
2. 데이터베이스 생성
3. JDBC 드라이버를 프로젝트에 추가
4. 각 예제 파일의 URL, USER, PASSWORD를 실제 값으로 수정

## JDBC 드라이버 추가 방법

### Maven 사용 시
`pom.xml`에 추가:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Gradle 사용 시
`build.gradle`에 추가:
```gradle
dependencies {
    implementation 'mysql:mysql-connector-java:8.0.33'
}
```

### 수동 추가
1. JDBC 드라이버 JAR 파일 다운로드
2. 프로젝트의 lib 폴더에 추가
3. 클래스패스에 추가

## 명령줄에서 실행하기

### 1. JDBCBasics.java
```bash
javac JDBCBasics.java
java JDBCBasics
```

### 2. DBConnection.java
```bash
javac DBConnection.java
java DBConnection
```

### 3. CRUDExample.java
```bash
javac CRUDExample.java
java CRUDExample
```

### 4. UserManager.java
```bash
javac UserManager.java
java UserManager
```

### 5. PreparedStatementExample.java
```bash
javac PreparedStatementExample.java
java PreparedStatementExample
```

### 6. TransactionExample.java
```bash
javac TransactionExample.java
java TransactionExample
```

### 7. JDBCFlow.java
```bash
javac JDBCFlow.java
java JDBCFlow
```

### 8. CRUDComprehensive.java
```bash
javac CRUDComprehensive.java
java CRUDComprehensive
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 데이터베이스 설정 예제

### MySQL 설정 예제

```sql
-- 데이터베이스 생성
CREATE DATABASE mydb;

-- 사용자 생성 및 권한 부여
CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON mydb.* TO 'myuser'@'localhost';
FLUSH PRIVILEGES;

-- 테이블 생성
USE mydb;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100)
);
```

### SQLite 설정 예제

SQLite는 파일 기반이므로 별도 서버 설치가 필요 없습니다.

```java
// SQLite 연결
String url = "jdbc:sqlite:mydb.db";
Connection conn = DriverManager.getConnection(url);
```

## 학습 포인트

- **JDBC**: Java Database Connectivity, Java와 DB를 연결하는 API
- **Connection**: 데이터베이스 연결 객체
- **Statement/PreparedStatement**: SQL 문 실행 객체
- **ResultSet**: 쿼리 결과를 담는 객체
- **CRUD**: Create, Read, Update, Delete 작업
- **트랜잭션**: 여러 SQL 문을 하나의 작업 단위로 묶기

## JDBC 연결 과정

1. 드라이버 로드
2. Connection 생성
3. PreparedStatement 생성
4. SQL 실행
5. 결과 처리
6. 리소스 해제

## 주요 메서드

### Connection
- `createStatement()`: Statement 생성
- `prepareStatement(sql)`: PreparedStatement 생성
- `setAutoCommit(boolean)`: 자동 커밋 설정
- `commit()`: 커밋
- `rollback()`: 롤백
- `close()`: 연결 종료

### PreparedStatement
- `setInt(index, value)`: 정수 파라미터 설정
- `setString(index, value)`: 문자열 파라미터 설정
- `executeQuery()`: SELECT 실행
- `executeUpdate()`: INSERT/UPDATE/DELETE 실행
- `close()`: Statement 종료

### ResultSet
- `next()`: 다음 행으로 이동
- `getInt(column)`: 정수 값 가져오기
- `getString(column)`: 문자열 값 가져오기
- `close()`: ResultSet 종료

## 주의사항

- 실제 데이터베이스 연결이 필요합니다
- JDBC 드라이버를 프로젝트에 추가해야 합니다
- URL, USER, PASSWORD를 실제 값으로 수정해야 합니다
- 리소스는 반드시 close()해야 합니다 (try-with-resources 권장)
- SQLException을 반드시 처리해야 합니다
- PreparedStatement를 사용하여 SQL 인젝션을 방지하세요

