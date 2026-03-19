# BookMarkert 도서 쇼핑몰 프로젝트 교안

## 1. 프로젝트 개요

### 1.1 프로젝트 소개
**BookMarkert**는 JSP/Servlet 기반의 웹 도서 쇼핑몰 애플리케이션입니다. 도서 목록 조회, 장바구니, 주문, 회원 관리, 게시판 기능을 포함합니다.

### 1.2 학습 목표
- JSP/Servlet 기반 웹 애플리케이션 개발 이해
- MVC 패턴 적용 (게시판 모듈)
- JDBC를 이용한 MySQL 데이터베이스 연동
- 세션을 활용한 장바구니 구현
- 필터(Filter)를 이용한 로깅
- 보안 설정 (Form 기반 로그인, 역할 기반 접근 제어)

---

## 2. 개발 환경

| 구분 | 기술 스택 |
|------|-----------|
| **언어** | Java 21 |
| **웹** | Jakarta EE 6.0 (Servlet 5.0) |
| **서버** | Apache Tomcat 10.1 |
| **DB** | MySQL (BookMarketDB) |
| **UI** | Bootstrap 5 |
| **빌드** | Eclipse WTP (Dynamic Web Project) |

### 2.1 사전 준비
- Eclipse IDE (Java EE 개발자용)
- JDK 21
- Apache Tomcat 10.1
- MySQL 8.x
- MySQL Connector/J (JDBC 드라이버) → `WEB-INF/lib`에 배치

---

## 3. 프로젝트 구조

```
BookMarkert/
├── src/main/
│   ├── java/
│   │   ├── dao/              # 데이터 접근
│   │   │   └── BookRepository.java    # 도서 메모리 저장소 (장바구니용)
│   │   ├── dto/
│   │   │   └── Book.java              # 도서 DTO
│   │   ├── filter/
│   │   │   ├── LogFilter.java        # 콘솔 로깅 필터
│   │   │   └── LogFileFilter.java    # 파일 로깅 필터
│   │   ├── mvc/
│   │   │   ├── controller/
│   │   │   │   └── BoardController.java   # 게시판 서블릿
│   │   │   ├── database/
│   │   │   │   └── DBConnection.java     # DB 연결 유틸
│   │   │   └── model/
│   │   │       ├── BoardDAO.java         # 게시판 DAO
│   │   │       └── BoardDTO.java         # 게시판 DTO
│   │   └── bundle/
│   │       └── message*.properties       # 다국어 메시지
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                  # 웹 설정 (필터, 서블릿, 보안)
│       ├── META-INF/
│       ├── resources/
│       │   ├── css/                     # Bootstrap CSS
│       │   ├── js/                      # validation.js
│       │   ├── images/                  # 도서 이미지
│       │   └── sql/                     # SQL 스크립트
│       ├── member/                      # 회원 관련 JSP
│       ├── board/                       # 게시판 관련 JSP
│       └── *.jsp                        # 루트 JSP 페이지
├── build/classes/                       # 컴파일 출력
├── .classpath
└── .project
```

---

## 4. 데이터베이스 설계

### 4.1 데이터베이스 생성
```sql
CREATE DATABASE BookMarketDB;
USE BookMarketDB;
```

### 4.2 book 테이블 (도서)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| b_id | VARCHAR(10) PK | 도서 ID (예: ISBN1234) |
| b_name | VARCHAR(20) | 도서명 |
| b_unitPrice | INTEGER | 가격 |
| b_author | VARCHAR(20) | 저자 |
| b_description | TEXT | 설명 |
| b_publisher | VARCHAR(20) | 출판사 |
| b_category | VARCHAR(20) | 분류 |
| b_unitsInStock | LONG | 재고 수 |
| b_releaseDate | VARCHAR(20) | 출판일 |
| b_condition | VARCHAR(20) | 상태 (new 등) |
| b_fileName | VARCHAR(20) | 이미지 파일명 |

### 4.3 member 테이블 (회원)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | VARCHAR(10) PK | 회원 ID |
| password | VARCHAR(10) | 비밀번호 |
| name | VARCHAR(10) | 이름 |
| gender | VARCHAR(4) | 성별 |
| birth | VARCHAR(10) | 생년월일 |
| mail | VARCHAR(30) | 이메일 |
| phone | VARCHAR(20) | 전화번호 |
| address | VARCHAR(90) | 주소 |
| regist_day | VARCHAR(50) | 가입일 |

### 4.4 board 테이블 (게시판)
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| num | INT PK AUTO_INCREMENT | 글 번호 |
| id | VARCHAR(10) | 작성자 ID |
| name | VARCHAR(10) | 작성자명 |
| subject | VARCHAR(100) | 제목 |
| content | TEXT | 내용 |
| regist_day | VARCHAR(30) | 등록일 |
| hit | INT | 조회수 |
| ip | VARCHAR(20) | IP 주소 |

### 4.5 초기 데이터 (insert.sql)
- `resources/sql/insert.sql` 실행하여 기본 도서 3권(ISBN1234, ISBN1235, ISBN1236) 삽입

---

## 5. 주요 기능 설명

### 5.1 도서 쇼핑 기능

| 페이지 | 경로 | 설명 |
|--------|------|------|
| 메인 | welcome.jsp | 환영 메시지, 현재 시각 (5초마다 갱신) |
| 도서 목록 | books.jsp | DB에서 도서 목록 조회, 상세보기 링크 |
| 도서 상세 | book.jsp?id=ISBN1234 | DB에서 도서 조회, 장바구니 담기 |
| 장바구니 | cart.jsp | 세션 기반 장바구니, 삭제/주문하기 |
| 장바구니 담기 | addCart.jsp | BookRepository에서 도서 조회 후 세션에 추가 |
| 장바구니 삭제 | removeCart.jsp | 특정 도서 제거 |
| 장바구니 비우기 | deleteCart.jsp | 전체 비우기 |
| 배송 정보 | shippingInfo.jsp | 주문자 정보 입력 |
| 주문 처리 | processShippingInfo.jsp | 배송 정보 처리 |
| 주문 확인 | orderConfirmation.jsp | 주문 내용 확인 |
| 주문 완료 | thankCustomer.jsp | 감사 페이지 |

### 5.2 도서 관리 기능 (관리자)

| 페이지 | 경로 | 설명 |
|--------|------|------|
| 도서 등록 | addBook.jsp | 새 도서 등록 폼 (이미지 업로드) |
| 등록 처리 | processAddBook.jsp | DB에 INSERT |
| 도서 수정 | editBook.jsp | 수정/삭제 선택 |
| 수정 처리 | processUpdateBook.jsp | DB UPDATE |
| 도서 삭제 | deleteBook.jsp | DB DELETE |

**보안**: addBook.jsp, editBook.jsp는 `admin` 역할 필요 (web.xml)

### 5.3 회원 기능

| 페이지 | 경로 | 설명 |
|--------|------|------|
| 로그인 | member/loginMember.jsp | 회원 로그인 |
| 로그인 처리 | member/processLoginMember.jsp | 세션에 sessionId 저장 |
| 로그아웃 | member/logoutMember.jsp | 세션 무효화 |
| 회원가입 | member/addMember.jsp | 가입 폼 |
| 가입 처리 | member/processAddMember.jsp | DB INSERT |
| 회원 수정 | member/updateMember.jsp | 정보 수정 |
| 회원 삭제 | member/deleteMember.jsp | 탈퇴 |

### 5.4 게시판 기능 (MVC 패턴)

| URL | 설명 |
|-----|------|
| BoardListAction.do | 목록 조회 (페이징, 검색) |
| BoardWriteForm.do | 글쓰기 폼 |
| BoardWriteAction.do | 글 등록 |
| BoardViewAction.do | 글 상세 조회 |
| BoardView.do | 상세 페이지 표시 |
| BoardUpdateAction.do | 글 수정 |
| BoardDeleteAction.do | 글 삭제 |

**흐름**: BoardController (서블릿) → BoardDAO → DB → JSP (list.jsp, view.jsp, writeForm.jsp)

### 5.5 필터 (Filter)

| 필터 | 클래스 | 역할 |
|------|--------|------|
| LogFilter | filter.LogFilter | 모든 요청에 대해 콘솔에 IP, URL, 처리 시간 로깅 |
| LogFileFilter | filter.LogFileFilter | 동일 정보를 `c:\logs\bookmarket.log`에 기록 |

**매핑**: `/*` (모든 요청)

---

## 6. 설정 파일

### 6.1 web.xml 주요 설정

```xml
<!-- 보안: admin 역할 필요 URL -->
<security-constraint>
  <url-pattern>/addBook.jsp</url-pattern>
  <url-pattern>/editBook.jsp</url-pattern>
  <auth-constraint><role-name>admin</role-name></auth-constraint>
</security-constraint>

<!-- Form 로그인 -->
<login-config>
  <auth-method>FORM</auth-method>
  <form-login-page>/login.jsp</form-login-page>
  <form-error-page>/login_failed.jsp</form-error-page>
</login-config>

<!-- 404 에러 페이지 -->
<error-page>
  <error-code>404</error-code>
  <location>/exceptionNoPage.jsp</location>
</error-page>

<!-- 필터, 서블릿 매핑 -->
```

### 6.2 DB 연결 (dbconn.jsp)

```jsp
String url = "jdbc:mysql://localhost:3306/BookMarketDB?serverTimezone=Asia/Seoul&useSSL=false";
String user = "root";
String password = "1234";
Class.forName("com.mysql.cj.jdbc.Driver");
conn = DriverManager.getConnection(url, user, password);
```

- MySQL 사용자/비밀번호는 환경에 맞게 수정 필요

---

## 7. 실행 방법

### 7.1 데이터베이스 준비
1. MySQL 실행
2. `resources/sql/book.sql` 실행 → book 테이블 생성
3. `resources/sql/member.sql` 실행 → member 테이블 생성
4. `resources/sql/board.sql` 실행 → board 테이블 생성
5. `resources/sql/insert.sql` 실행 → 초기 도서 데이터 삽입

### 7.2 Tomcat 사용자 설정 (관리자 로그인용)
- `conf/tomcat-users.xml`에 admin 역할 사용자 추가:
```xml
<user username="admin" password="admin" roles="admin"/>
```

### 7.3 Eclipse에서 실행
1. 프로젝트 Import (Existing Projects)
2. Tomcat 10.1 서버 추가 및 프로젝트 배포
3. 서버 시작
4. 브라우저에서 `http://localhost:8080/BookMarkert/welcome.jsp` 접속

### 7.4 LogFileFilter 로그 경로
- 기본: `c:\logs\bookmarket.log`
- 해당 폴더가 없으면 생성 필요 (또는 web.xml init-param 수정)

---

## 8. 학습 순서 (단계별)

### 1단계: 환경 구축 및 기본 페이지
- Eclipse, Tomcat, MySQL 설치
- 프로젝트 Import 및 서버 실행
- welcome.jsp 동작 확인

### 2단계: 도서 목록/상세
- book, member, board 테이블 생성 및 데이터 삽입
- books.jsp, book.jsp 동작 확인
- DB 조회 흐름 이해 (dbconn.jsp, PreparedStatement)

### 3단계: 장바구니
- addCart.jsp, cart.jsp, removeCart.jsp 분석
- 세션(session) 활용 이해
- BookRepository와 DB 데이터의 역할 구분

### 4단계: 주문 프로세스
- shippingInfo.jsp → processShippingInfo.jsp → orderConfirmation.jsp → thankCustomer.jsp 흐름 파악

### 5단계: 도서 관리 (CRUD)
- addBook.jsp, processAddBook.jsp (파일 업로드: MultipartRequest)
- editBook.jsp, processUpdateBook.jsp, deleteBook.jsp
- admin 보안 설정 이해

### 6단계: 회원 기능
- 로그인/로그아웃, 회원가입, 수정, 삭제
- 세션 기반 로그인 상태 유지

### 7단계: 게시판 (MVC)
- BoardController 서블릿 분석
- BoardDAO, BoardDTO 구조
- *.do URL 매핑과 forward 흐름

### 8단계: 필터와 보안
- LogFilter, LogFileFilter 동작
- web.xml 보안 설정
- Form 기반 로그인

---

## 9. 참고 사항

### 9.1 addCart.jsp와 BookRepository
- addCart.jsp는 `BookRepository.getBookById()`를 사용 (메모리 리스트)
- BookRepository에는 기본 3권만 하드코딩
- **새로 DB에 추가한 도서**를 장바구니에 담으려면, addCart.jsp를 DB 조회 방식으로 수정하거나 BookRepository를 DB 연동으로 변경해야 함

### 9.2 processAddBook.jsp 이미지 경로
- `realFolder`가 Eclipse 서버 tmp 경로로 하드코딩되어 있음
- 서버/환경 변경 시 경로 수정 필요

### 9.3 MySQL 드라이버
- `com.mysql.cj.jdbc.Driver` 사용 (MySQL 8.x)
- `WEB-INF/lib`에 mysql-connector-j-*.jar 배치

---

## 10. 연습 과제 제안

1. **도서 검색**: books.jsp에 제목/저자 검색 기능 추가
2. **장바구니 DB 연동**: addCart.jsp를 DB 조회로 변경
3. **주문 저장**: orderConfirmation.jsp에서 주문 정보를 DB에 저장
4. **페이징**: books.jsp에 페이지네이션 추가
5. **이미지 경로 개선**: 상대 경로 또는 설정 파일로 이미지 저장 경로 관리

---

*본 교안은 BookMarkert 프로젝트 분석을 바탕으로 작성되었습니다.*
