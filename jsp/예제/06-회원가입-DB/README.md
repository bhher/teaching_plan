# 📝 JSP 회원가입 및 데이터베이스 연동 예제

## 📋 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [환경 설정](#환경-설정)
3. [데이터베이스 설정](#데이터베이스-설정)
4. [파일 구조](#파일-구조)
5. [주요 기능](#주요-기능)
6. [코드 설명](#코드-설명)
7. [실행 방법](#실행-방법)

---

## 🎯 프로젝트 개요

이 예제는 JSP를 사용하여 회원가입 폼을 만들고, MySQL 데이터베이스에 회원 정보를 저장하는 완전한 웹 애플리케이션입니다.

### 주요 기능
- ✅ 회원가입 폼
- ✅ 데이터 유효성 검증
- ✅ 아이디 중복 체크
- ✅ MySQL 데이터베이스 연동
- ✅ 회원 목록 조회
- ✅ 로그인/로그아웃 (DB 연동)
- ✅ 트랜잭션 처리
- ✅ **DTO/DAO 패턴 적용** (권장 방식)

---

## 🔧 환경 설정

### 필요 사항
1. **Java JDK** (1.8 이상)
2. **Apache Tomcat** (9.0 이상)
3. **MySQL** (5.7 이상 또는 8.0)
4. **MySQL JDBC Driver** (mysql-connector-java-8.0.x.jar)

### MySQL JDBC Driver 다운로드
1. https://dev.mysql.com/downloads/connector/j/
2. Platform Independent 선택
3. ZIP 파일 다운로드
4. 압축 해제 후 `mysql-connector-java-8.0.x.jar` 파일 추출

---

## 💾 데이터베이스 설정

### 1. MySQL 설치 및 실행

MySQL이 설치되어 있고 실행 중이어야 합니다.

### 2. 데이터베이스 및 테이블 생성

`db_setup.sql` 파일을 실행하세요:

```bash
mysql -u root -p < db_setup.sql
```

또는 MySQL 클라이언트에서:

```sql
source db_setup.sql;
```

### 3. 데이터베이스 연결 정보 확인

`register_process.jsp`와 `member_list.jsp` 파일에서 다음 정보를 수정하세요:

```jsp
String url = "jdbc:mysql://localhost:3306/jsp_member?...";
String dbUser = "root";
String dbPassword = "1234";  // 실제 비밀번호로 변경
```

---

## 📁 파일 구조

```
06-회원가입-DB/
├── db_setup.sql                    # 데이터베이스 생성 SQL
├── register.jsp                    # 회원가입 폼
├── register_process.jsp            # 회원가입 처리 (기존 방식)
├── register_process_dto.jsp        # 회원가입 처리 (DTO/DAO 방식)
├── member_list.jsp                 # 회원 목록 조회 (기존 방식)
├── member_list_dto.jsp             # 회원 목록 조회 (DTO/DAO 방식)
├── login_db.jsp                    # 로그인 폼
├── login_process_db.jsp            # 로그인 처리 (기존 방식)
├── login_process_dto.jsp           # 로그인 처리 (DTO/DAO 방식)
├── main_db.jsp                     # 메인 페이지 (로그인 후)
├── logout_db.jsp                   # 로그아웃 처리
├── MemberDTO.java                  # 회원 정보 DTO 클래스
├── MemberDAO.java                  # 회원 정보 DAO 클래스
├── DBConnection.java               # DB 연결 유틸리티 클래스
├── README.md                       # 이 파일
├── 06-회원가입-DB-상세설명.md      # 상세 설명 문서
└── DTO-DAO-패턴-설명.md            # DTO/DAO 패턴 설명
```

---

## 🎨 주요 기능

### 1. 회원가입 폼 (register.jsp)

**기능:**
- 아이디, 비밀번호, 이름 입력
- 이메일, 성별, 취미, 거주지 선택
- 클라이언트 측 유효성 검증 (JavaScript)
- 서버 측 유효성 검증 (JSP)

**입력 항목:**
- 아이디 (필수, 4-20자)
- 비밀번호 (필수, 8자 이상)
- 비밀번호 확인 (필수)
- 이름 (필수)
- 이메일 (선택)
- 성별 (선택)
- 취미 (다중 선택)
- 거주지 (선택)
- 자기소개 (선택)

---

### 2. 회원가입 처리 (register_process.jsp)

**처리 과정:**
1. 파라미터 받기
2. 필수 항목 검증
3. 비밀번호 확인
4. 데이터베이스 연결
5. 아이디 중복 체크
6. 회원 정보 INSERT
7. 취미 정보 INSERT
8. 트랜잭션 커밋
9. 성공 페이지로 리다이렉트

**에러 처리:**
- 필수 항목 누락
- 비밀번호 불일치
- 아이디 중복
- 데이터베이스 오류

---

### 3. 회원 목록 (member_list.jsp)

**기능:**
- 모든 회원 정보 조회
- 취미 정보 포함
- 가입일 순으로 정렬

---

## 📖 코드 설명

### 1. 데이터베이스 연결

```jsp
<%
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/jsp_member?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    String dbUser = "root";
    String dbPassword = "1234";
    
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
%>
```

**설명:**
- `Class.forName()`: JDBC 드라이버 로드
- `DriverManager.getConnection()`: 데이터베이스 연결
- URL 파라미터:
  - `useSSL=false`: SSL 비활성화 (개발 환경)
  - `serverTimezone=Asia/Seoul`: 시간대 설정
  - `characterEncoding=UTF-8`: 인코딩 설정

---

### 2. PreparedStatement 사용

```jsp
String sql = "INSERT INTO members (user_id, password, name) VALUES (?, ?, ?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId);
pstmt.setString(2, password);
pstmt.setString(3, name);
pstmt.executeUpdate();
```

**장점:**
- SQL Injection 방지
- 성능 향상 (컴파일된 SQL 재사용)
- 타입 안전성

---

### 3. 트랜잭션 처리

```jsp
conn.setAutoCommit(false);  // 트랜잭션 시작

try {
    // 회원 정보 INSERT
    // 취미 정보 INSERT
    
    conn.commit();  // 성공 시 커밋
} catch (SQLException e) {
    conn.rollback();  // 실패 시 롤백
} finally {
    conn.setAutoCommit(true);
}
```

**설명:**
- 여러 작업을 하나의 단위로 처리
- 하나라도 실패하면 전체 롤백
- 데이터 일관성 보장

---

### 4. 아이디 중복 체크

```jsp
String checkSql = "SELECT COUNT(*) FROM members WHERE user_id = ?";
pstmt = conn.prepareStatement(checkSql);
pstmt.setString(1, userId);
rs = pstmt.executeQuery();

if (rs.next() && rs.getInt(1) > 0) {
    // 중복됨
    response.sendRedirect("register.jsp?error=duplicate");
    return;
}
```

**설명:**
- `COUNT(*)`로 중복 여부 확인
- 0보다 크면 중복

---

### 5. 취미 다중 INSERT

```jsp
if (hobbies != null && hobbies.length > 0) {
    String hobbySql = "INSERT INTO member_hobbies (member_id, hobby) VALUES (?, ?)";
    pstmt = conn.prepareStatement(hobbySql);
    
    for (String hobby : hobbies) {
        pstmt.setInt(1, memberId);
        pstmt.setString(2, hobby);
        pstmt.addBatch();  // 배치에 추가
    }
    pstmt.executeBatch();  // 한 번에 실행
}
```

**설명:**
- `addBatch()`: 배치에 추가
- `executeBatch()`: 한 번에 실행
- 성능 향상

---

## 🚀 실행 방법

### 1. MySQL JDBC Driver 추가

1. `mysql-connector-java-8.0.x.jar` 파일 다운로드
2. 프로젝트의 `WEB-INF/lib/` 폴더에 복사
3. 프로젝트 재시작

### 2. 데이터베이스 생성

```bash
mysql -u root -p
source db_setup.sql;
```

### 3. 연결 정보 수정

`register_process.jsp`와 `member_list.jsp`에서:
```jsp
String dbPassword = "실제비밀번호";
```

### 4. 실행

1. Tomcat 서버 시작
2. 브라우저에서 접속:
   - 회원가입: `http://localhost:8080/프로젝트명/register.jsp`
   - 로그인: `http://localhost:8080/프로젝트명/login_db.jsp`
   - 회원목록: `http://localhost:8080/프로젝트명/member_list.jsp`

---

## 🔒 보안 고려사항

### 1. 비밀번호 암호화

**현재 코드:**
```jsp
pstmt.setString(2, password);  // 평문 저장 (위험!)
```

**개선 방법:**
```jsp
import java.security.MessageDigest;

// SHA-256 암호화
MessageDigest md = MessageDigest.getInstance("SHA-256");
byte[] hash = md.digest(password.getBytes("UTF-8"));
String encryptedPassword = new String(hash);
pstmt.setString(2, encryptedPassword);
```

또는 BCrypt 사용 권장

---

### 2. SQL Injection 방지

✅ **PreparedStatement 사용** (이미 구현됨)

❌ **절대 하지 말 것:**
```jsp
String sql = "SELECT * FROM members WHERE user_id = '" + userId + "'";
```

---

### 3. XSS 방지

```jsp
<!-- 위험 -->
<%= request.getParameter("name") %>

<!-- 안전 -->
<c:out value="${param.name}" />
```

---

## 🐛 문제 해결

### 오류 1: ClassNotFoundException

**원인:** MySQL JDBC Driver가 없음

**해결:**
- `mysql-connector-java-8.0.x.jar`를 `WEB-INF/lib/`에 추가

---

### 오류 2: SQLException: Access denied

**원인:** 데이터베이스 연결 정보 오류

**해결:**
- 사용자명, 비밀번호 확인
- 데이터베이스 이름 확인

---

### 오류 3: 한글 깨짐

**원인:** 인코딩 설정 누락

**해결:**
- URL에 `characterEncoding=UTF-8` 추가
- 데이터베이스 문자셋 확인: `SHOW VARIABLES LIKE 'character_set%';`

---

### 오류 4: Timezone 오류

**원인:** 서버 시간대 설정 누락

**해결:**
- URL에 `serverTimezone=Asia/Seoul` 추가

---

## 💡 추가 개선 사항

### 1. DAO 패턴 적용

```java
public class MemberDAO {
    public boolean insertMember(Member member) {
        // 회원 등록 로직
    }
    
    public Member selectMember(String userId) {
        // 회원 조회 로직
    }
}
```

### 2. Connection Pool 사용

```xml
<!-- context.xml -->
<Resource name="jdbc/memberDB"
          auth="Container"
          type="javax.sql.DataSource"
          driverClassName="com.mysql.cj.jdbc.Driver"
          url="jdbc:mysql://localhost:3306/jsp_member"
          username="root"
          password="1234"
          maxTotal="20"
          maxIdle="10"/>
```

### 3. 비밀번호 암호화

- BCrypt 라이브러리 사용 권장

---

## 📚 코드 방식 선택

### 기존 방식 (직접 SQL)
- `register_process.jsp`: 직접 SQL 작성
- `login_process_db.jsp`: 직접 SQL 작성
- `member_list.jsp`: 직접 SQL 작성

**장점:**
- 간단하고 직관적
- 학습 목적에 적합

**단점:**
- 코드 재사용 어려움
- 유지보수 어려움

### DTO/DAO 방식 (권장)
- `register_process_dto.jsp`: DTO/DAO 사용
- `login_process_dto.jsp`: DTO/DAO 사용
- `member_list_dto.jsp`: DTO/DAO 사용

**장점:**
- 코드 재사용 가능
- 유지보수 용이
- 테스트 가능
- 프로젝트 규모 확장 시 유리

**사용 파일:**
- `MemberDTO.java`: 데이터 전달 객체
- `MemberDAO.java`: 데이터 접근 객체
- `DBConnection.java`: DB 연결 유틸리티

**자세한 설명:** `DTO-DAO-패턴-설명.md` 참고

---

## ✅ 체크리스트

- [ ] MySQL 설치 및 실행
- [ ] 데이터베이스 및 테이블 생성
- [ ] MySQL JDBC Driver 추가
- [ ] 연결 정보 수정
- [ ] 회원가입 테스트
- [ ] 회원 목록 조회 테스트
- [ ] 로그인/로그아웃 테스트
- [ ] 에러 처리 확인
- [ ] DTO/DAO 패턴 이해 (선택사항)

---

**이 예제를 완료하면 JSP와 데이터베이스 연동의 기본을 마스터할 수 있습니다! 💪**
