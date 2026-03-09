# JSP 게시판 프로젝트

## 📋 프로젝트 구조

```
JSPBoard
│
├─ src
│   ├─ util
│   │     DBConnection.java
│   │
│   ├─ dto
│   │     MemberDTO.java
│   │     BoardDTO.java
│   │
│   ├─ dao
│   │     MemberDAO.java
│   │     BoardDAO.java
│   │
│   └─ servlet
│         JoinServlet.java
│         LoginServlet.java
│         LogoutServlet.java
│         BoardWriteServlet.java
│         BoardListServlet.java
│         BoardViewServlet.java
│         BoardUpdateServlet.java
│         BoardDeleteServlet.java
│
└─ WebContent
     │ index.jsp
     │ join.jsp
     │ login.jsp
     │ boardList.jsp
     │ boardWrite.jsp
     │ boardView.jsp
     │ boardUpdate.jsp
     │ header.jsp
     │ footer.jsp
     │
     └─ WEB-INF
         │ web.xml
         └─ lib
            mysql-connector-j-8.x.jar
            jstl-1.2.jar
```

## 🗄️ MySQL 테이블

### DB 생성
```sql
CREATE DATABASE jspboard;
USE jspboard;
```

### 회원 테이블
```sql
CREATE TABLE member(
    id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    name VARCHAR(50),
    email VARCHAR(100),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 게시판 테이블
```sql
CREATE TABLE board(
    bno INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    writer VARCHAR(50),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 📦 필요한 JAR 파일

`WebContent/WEB-INF/lib` 폴더에 다음 JAR 파일을 추가하세요:

1. **mysql-connector-j-8.3.0.jar** (또는 최신 버전)
   - 다운로드: https://dev.mysql.com/downloads/connector/j/

2. **jstl-1.2.jar**
   - 다운로드: https://mvnrepository.com/artifact/javax.servlet/jstl/1.2

## 🔧 설정 방법

### 1. 데이터베이스 설정

`src/util/DBConnection.java` 파일에서 데이터베이스 연결 정보를 수정하세요:

```java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/jspboard",  // 데이터베이스명
    "root",                                  // 사용자명
    "1234"                                   // 비밀번호
);
```

### 2. JAR 파일 추가

1. `WebContent/WEB-INF/lib` 폴더 생성
2. 다음 JAR 파일 복사:
   - `mysql-connector-j-8.3.0.jar`
   - `jstl-1.2.jar`

## 🚀 Tomcat 실행 방법

### Eclipse에서 실행

1. **프로젝트 Import**
   - `File` → `Import` → `Existing Projects into Workspace`
   - 프로젝트 폴더 선택

2. **Tomcat 서버 설정**
   - `Window` → `Preferences` → `Server` → `Runtime Environments`
   - `Add...` → `Apache Tomcat v9.0` (또는 v8.5) 선택
   - Tomcat 설치 경로 지정

3. **프로젝트를 서버에 추가**
   - `Servers` 뷰에서 서버 우클릭 → `Add and Remove...`
   - 프로젝트를 `Configured`로 이동

4. **서버 실행**
   - 서버 우클릭 → `Start`
   - 브라우저에서 `http://localhost:8080/JSPBoard/index.jsp` 접속

### IntelliJ에서 실행

1. **프로젝트 열기**
   - `File` → `Open` → 프로젝트 폴더 선택

2. **Tomcat 설정**
   - `Run` → `Edit Configurations...`
   - `+` → `Tomcat Server` → `Local`
   - `Deployment` 탭에서 `+` → `Artifact` → `JSPBoard:war exploded` 추가

3. **서버 실행**
   - `Run` → `Run 'Tomcat Server'`
   - 브라우저에서 `http://localhost:8080/JSPBoard/index.jsp` 접속

## 📝 주요 기능

### 회원 관리
- 회원가입 (`/join`)
- 로그인 (`/login`)
- 로그아웃 (`/logout`)

### 게시판 기능
- 게시글 작성 (`/write`)
- 게시판 목록 (`/list`)
- 게시글 보기 (`/view`)
- 게시글 수정 (`/update`)
- 게시글 삭제 (`/delete`)

## 🔐 세션 관리

로그인 성공 시 세션에 `loginId`가 저장됩니다:

```java
HttpSession session = request.getSession();
session.setAttribute("loginId", id);
```

게시글 작성 시 세션에서 작성자 정보를 가져옵니다:

```java
String writer = (String)session.getAttribute("loginId");
dto.setWriter(writer);
```

## ⚠️ 주의사항

1. **데이터베이스 연결 정보 확인**
   - `DBConnection.java`에서 데이터베이스명, 사용자명, 비밀번호 확인

2. **JAR 파일 확인**
   - `WebContent/WEB-INF/lib` 폴더에 필요한 JAR 파일이 있는지 확인

3. **Tomcat 버전**
   - Java EE 3.1을 지원하는 Tomcat 8.5 이상 사용

4. **인코딩**
   - 모든 파일은 UTF-8 인코딩 사용

---

**작성일**: 2024년  
**프로젝트명**: JSP Board  
**용도**: JSP/Servlet 학습용 예제
