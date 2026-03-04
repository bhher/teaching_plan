# 📝 DTO/DAO 패턴 설명

## 📋 목차

1. [DTO/DAO 패턴이란?](#dto-dao-패턴이란)
2. [DTO 클래스 설명](#dto-클래스-설명)
3. [DAO 클래스 설명](#dao-클래스-설명)
4. [사용 방법](#사용-방법)
5. [기존 코드와 비교](#기존-코드와-비교)
6. [장점](#장점)

---

## 🎯 DTO/DAO 패턴이란?

### DTO (Data Transfer Object)
- **역할**: 데이터를 담는 객체
- **용도**: 계층 간 데이터 전달
- **특징**: Getter/Setter 메서드만 가짐

### DAO (Data Access Object)
- **역할**: 데이터베이스 접근 로직을 담당
- **용도**: CRUD 작업 수행
- **특징**: 비즈니스 로직과 분리

---

## 📦 DTO 클래스 설명

### MemberDTO.java

```java
public class MemberDTO {
    private int id;
    private String userId;
    private String password;
    private String name;
    // ... 기타 필드들
    
    // Getter와 Setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    // 유틸리티 메서드
    public String getHobbiesAsString() { ... }
    public boolean hasEmail() { ... }
}
```

**주요 특징:**
- 모든 필드는 `private`
- `public` Getter/Setter 메서드 제공
- 유틸리티 메서드 포함

---

## 🔧 DAO 클래스 설명

### MemberDAO.java

```java
public class MemberDAO {
    private Connection conn;
    
    public MemberDAO(Connection conn) {
        this.conn = conn;
    }
    
    // 회원 등록
    public int insertMember(MemberDTO member) throws SQLException {
        // INSERT 로직
    }
    
    // 회원 조회
    public MemberDTO selectMemberByUserId(String userId) throws SQLException {
        // SELECT 로직
    }
    
    // 아이디 중복 체크
    public boolean isUserIdExists(String userId) throws SQLException {
        // 중복 체크 로직
    }
}
```

**주요 특징:**
- Connection을 생성자에서 받음
- 모든 메서드는 SQLException을 던짐
- DTO 객체를 사용하여 데이터 전달

---

## 💻 사용 방법

### 1. 회원가입 처리 (DTO/DAO 사용)

**기존 코드:**
```jsp
<%
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    // ... 파라미터 받기
    
    // 직접 SQL 실행
    String sql = "INSERT INTO members ...";
    pstmt = conn.prepareStatement(sql);
    // ...
%>
```

**DTO/DAO 사용:**
```jsp
<%
    // 파라미터 받기
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    
    // DTO 객체 생성
    MemberDTO member = new MemberDTO();
    member.setUserId(userId);
    member.setPassword(password);
    // ...
    
    // DAO를 통한 저장
    MemberDAO memberDAO = new MemberDAO(conn);
    int memberId = memberDAO.insertMember(member);
%>
```

---

### 2. 회원 조회 (DTO/DAO 사용)

**기존 코드:**
```jsp
<%
    String sql = "SELECT * FROM members WHERE user_id = ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, userId);
    rs = pstmt.executeQuery();
    
    if (rs.next()) {
        String name = rs.getString("name");
        String email = rs.getString("email");
        // ...
    }
%>
```

**DTO/DAO 사용:**
```jsp
<%
    MemberDAO memberDAO = new MemberDAO(conn);
    MemberDTO member = memberDAO.selectMemberByUserId(userId);
    
    if (member != null) {
        String name = member.getName();
        String email = member.getEmail();
        // ...
    }
%>
```

---

## 📊 기존 코드와 비교

### 비교표

| 구분 | 기존 방식 | DTO/DAO 방식 |
|------|----------|-------------|
| 데이터 전달 | 개별 변수 | DTO 객체 |
| SQL 실행 | JSP에 직접 작성 | DAO 메서드 호출 |
| 코드 재사용 | 어려움 | 쉬움 |
| 유지보수 | 어려움 | 쉬움 |
| 테스트 | 어려움 | 쉬움 |

---

### 예시 비교

#### 회원가입 처리

**기존 방식 (register_process.jsp):**
```jsp
<%
    // 파라미터 받기
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    
    // SQL 직접 작성
    String sql = "INSERT INTO members (user_id, password, name) VALUES (?, ?, ?)";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, userId);
    pstmt.setString(2, password);
    pstmt.setString(3, name);
    pstmt.executeUpdate();
    
    // 취미 저장
    String hobbySql = "INSERT INTO member_hobbies ...";
    // ...
%>
```

**DTO/DAO 방식 (register_process_dto.jsp):**
```jsp
<%
    // 파라미터 받기
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    
    // DTO 객체 생성
    MemberDTO member = new MemberDTO();
    member.setUserId(userId);
    member.setPassword(password);
    member.setName(name);
    member.setHobbies(hobbyList);
    
    // DAO를 통한 저장
    MemberDAO memberDAO = new MemberDAO(conn);
    int memberId = memberDAO.insertMember(member);
%>
```

**차이점:**
- ✅ SQL 코드가 JSP에서 분리됨
- ✅ 코드가 더 읽기 쉬움
- ✅ 재사용 가능
- ✅ 유지보수 용이

---

## ✨ 장점

### 1. 코드 재사용성

**문제:**
```jsp
<!-- 여러 JSP 파일에서 같은 SQL 반복 -->
<%
    String sql = "SELECT * FROM members WHERE user_id = ?";
    // ... 반복되는 코드
%>
```

**해결:**
```java
// 한 번만 작성하고 재사용
MemberDTO member = memberDAO.selectMemberByUserId(userId);
```

---

### 2. 유지보수성

**문제:**
- 테이블 구조 변경 시 여러 파일 수정 필요

**해결:**
- DAO 클래스만 수정하면 됨

---

### 3. 테스트 용이성

**문제:**
- JSP는 테스트하기 어려움

**해결:**
- DAO 클래스는 단위 테스트 가능

---

### 4. 코드 가독성

**기존:**
```jsp
<%
    String sql = "INSERT INTO members (user_id, password, name, email, gender, city) VALUES (?, ?, ?, ?, ?, ?)";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, userId);
    pstmt.setString(2, password);
    pstmt.setString(3, name);
    pstmt.setString(4, email);
    pstmt.setString(5, gender);
    pstmt.setString(6, city);
    pstmt.executeUpdate();
%>
```

**DTO/DAO:**
```jsp
<%
    MemberDTO member = new MemberDTO();
    member.setUserId(userId);
    member.setPassword(password);
    member.setName(name);
    member.setEmail(email);
    member.setGender(gender);
    member.setCity(city);
    
    MemberDAO memberDAO = new MemberDAO(conn);
    memberDAO.insertMember(member);
%>
```

---

## 🔍 파일 구조

```
06-회원가입-DB/
├── MemberDTO.java              # 데이터 전달 객체
├── MemberDAO.java              # 데이터 접근 객체
├── DBConnection.java           # DB 연결 유틸리티
├── register_process.jsp        # 기존 방식
├── register_process_dto.jsp    # DTO/DAO 방식
├── login_process_db.jsp        # 기존 방식
├── login_process_dto.jsp       # DTO/DAO 방식
├── member_list.jsp             # 기존 방식
└── member_list_dto.jsp         # DTO/DAO 방식
```

---

## 💡 사용 권장사항

### 언제 DTO/DAO를 사용해야 하나?

✅ **사용 권장:**
- 프로젝트가 복잡할 때
- 여러 곳에서 같은 데이터를 사용할 때
- 코드 재사용이 중요할 때
- 유지보수가 중요할 때

❌ **사용 안 해도 됨:**
- 간단한 프로젝트
- 한 번만 사용하는 코드
- 학습 목적의 작은 예제

---

## 📝 실습 과제

### 과제 1: 회원 정보 수정 기능 추가

1. `MemberDTO`에 수정용 메서드 추가
2. `MemberDAO`에 `updateMember()` 메서드 추가
3. 수정 페이지 JSP 작성

### 과제 2: 회원 삭제 기능 추가

1. `MemberDAO`에 `deleteMember()` 메서드 추가
2. 삭제 확인 페이지 작성

### 과제 3: 페이징 기능 추가

1. `MemberDAO`에 `selectMembersWithPaging()` 메서드 추가
2. 페이지 번호를 받아서 해당 페이지의 회원만 조회

---

## ✅ 체크리스트

- [ ] DTO 클래스 이해
- [ ] DAO 클래스 이해
- [ ] DTO 객체 생성 가능
- [ ] DAO 메서드 호출 가능
- [ ] 기존 코드와 비교 이해
- [ ] 장점 이해

---

**DTO/DAO 패턴을 사용하면 더 깔끔하고 유지보수하기 쉬운 코드를 작성할 수 있습니다! 💪**
