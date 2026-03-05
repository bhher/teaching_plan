# 📝 JSP 게시판 및 데이터베이스 연동 예제

## 📋 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [환경 설정](#환경-설정)
3. [데이터베이스 설정](#데이터베이스-설정)
4. [파일 구조](#파일-구조)
5. [주요 기능](#주요-기능)
6. [실행 방법](#실행-방법)

---

## 🎯 프로젝트 개요

이 예제는 JSP를 사용하여 완전한 게시판을 구현하고, MySQL 데이터베이스와 연동하는 웹 애플리케이션입니다.

### 주요 기능
- ✅ 게시글 목록 조회 (페이징)
- ✅ 게시글 작성
- ✅ 게시글 상세보기 (조회수 증가)
- ✅ 게시글 수정
- ✅ 게시글 삭제
- ✅ 검색 기능 (제목, 작성자, 내용)
- ✅ MySQL 데이터베이스 연동
- ✅ DTO/DAO 패턴 적용

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

`DBConnection.java` 파일에서 다음 정보를 수정하세요:

```java
private static final String PASSWORD = "1234";  // 실제 비밀번호로 변경
```

---

## 📁 파일 구조

```
07-게시판-DB/
├── db_setup.sql                    # 데이터베이스 생성 SQL
├── BoardDTO.java                   # 게시글 정보 DTO 클래스
├── BoardDAO.java                   # 게시글 정보 DAO 클래스
├── DBConnection.java               # DB 연결 유틸리티 클래스
├── board_list.jsp                  # 게시글 목록
├── board_write.jsp                 # 게시글 작성 폼
├── board_write_process.jsp         # 게시글 작성 처리
├── board_view.jsp                  # 게시글 상세보기
├── board_modify.jsp                # 게시글 수정 폼
├── board_modify_process.jsp        # 게시글 수정 처리
├── board_delete.jsp                # 게시글 삭제 확인
├── board_delete_process.jsp        # 게시글 삭제 처리
└── README.md                       # 이 파일
```

---

## 🎨 주요 기능

### 1. 게시글 목록 (board_list.jsp)

**기능:**
- 전체 게시글 목록 표시
- 페이징 처리 (한 페이지에 10개)
- 검색 기능 (제목, 작성자, 내용)
- 게시글 번호, 제목, 작성자, 조회수, 등록일 표시

**페이징:**
- URL 파라미터: `?page=1`
- 검색과 함께 사용: `?page=1&searchType=title&searchKeyword=검색어`

---

### 2. 게시글 작성 (board_write.jsp)

**기능:**
- 제목, 작성자, 비밀번호, 내용 입력
- 필수 항목 검증
- 작성 후 상세보기 페이지로 이동

---

### 3. 게시글 상세보기 (board_view.jsp)

**기능:**
- 게시글 전체 내용 표시
- 조회수 자동 증가
- 수정/삭제 링크 제공
- 줄바꿈 처리 (`<br>` 태그)

---

### 4. 게시글 수정 (board_modify.jsp)

**기능:**
- 비밀번호 확인 후 수정
- 제목, 내용 수정 가능
- 작성자는 수정 불가

---

### 5. 게시글 삭제 (board_delete.jsp)

**기능:**
- 비밀번호 확인 후 삭제
- 삭제 확인 메시지 표시
- 삭제 후 목록으로 이동

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

`DBConnection.java`에서:
```java
private static final String PASSWORD = "실제비밀번호";
```

### 4. 실행

1. Tomcat 서버 시작
2. 브라우저에서 접속:
   - 게시판 목록: `http://localhost:8080/프로젝트명/board_list.jsp`

---

## 📊 데이터베이스 구조

### board 테이블

| 컬럼명 | 타입 | 제약조건 | 설명 |
|--------|------|----------|------|
| id | INT | PK, AUTO_INCREMENT | 게시글 번호 |
| title | VARCHAR(200) | NOT NULL | 제목 |
| content | TEXT | NULL | 내용 |
| writer | VARCHAR(50) | NOT NULL | 작성자 |
| password | VARCHAR(255) | NULL | 비밀번호 |
| hit | INT | DEFAULT 0 | 조회수 |
| reg_date | DATETIME | DEFAULT CURRENT_TIMESTAMP | 등록일시 |
| mod_date | DATETIME | DEFAULT CURRENT_TIMESTAMP | 수정일시 |

---

## 🔍 주요 코드 설명

### 1. 페이징 처리

```jsp
<%
    int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
    int pageSize = 10;
    int start = (currentPage - 1) * pageSize;
    
    List<BoardDTO> boardList = boardDAO.selectAllBoards(start, pageSize);
    int totalCount = boardDAO.getTotalCount();
    int totalPages = (int) Math.ceil((double) totalCount / pageSize);
%>
```

---

### 2. 검색 기능

```jsp
<%
    String searchType = request.getParameter("searchType");
    String searchKeyword = request.getParameter("searchKeyword");
    
    if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
        boardList = boardDAO.searchBoards(searchType, searchKeyword, start, pageSize);
    }
%>
```

---

### 3. 조회수 증가

```jsp
<%
    // 상세보기 페이지 진입 시
    boardDAO.increaseHit(id);
    board = boardDAO.selectBoardById(id);
%>
```

---

## 🐛 문제 해결

### 오류 1: ClassNotFoundException

**원인:** MySQL JDBC Driver가 없음

**해결:**
- `mysql-connector-java-8.0.x.jar`를 `WEB-INF/lib/`에 추가

---

### 오류 2: SQLException

**원인:** 데이터베이스 연결 정보 오류

**해결:**
- 사용자명, 비밀번호 확인
- 데이터베이스 이름 확인 (`jsp_board`)

---

### 오류 3: 한글 깨짐

**원인:** 인코딩 설정 누락

**해결:**
- URL에 `characterEncoding=UTF-8` 추가 확인
- 데이터베이스 문자셋 확인

---

## 💡 추가 개선 사항

### 1. 댓글 기능 추가

- `board_reply` 테이블 활용
- 댓글 작성/수정/삭제 기능

### 2. 파일 업로드

- 게시글에 파일 첨부 기능
- 이미지 미리보기

### 3. 관리자 기능

- 관리자 권한으로 모든 게시글 삭제 가능
- 공지사항 기능

---

## ✅ 체크리스트

- [ ] MySQL 설치 및 실행
- [ ] 데이터베이스 및 테이블 생성
- [ ] MySQL JDBC Driver 추가
- [ ] 연결 정보 수정
- [ ] 게시글 작성 테스트
- [ ] 게시글 목록 조회 테스트
- [ ] 게시글 수정/삭제 테스트
- [ ] 검색 기능 테스트
- [ ] 페이징 테스트

---

**이 예제를 완료하면 JSP 게시판의 기본을 마스터할 수 있습니다! 💪**
