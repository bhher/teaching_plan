# 17-Kculture-Platform 상세 정리

**소스:** `jsp/예제/17-Kculture-Platform`  
**DB:** `kculture_platform`  
**기술:** JSP · Java Servlet · JDBC · MySQL · **Front Controller (`*.do`)**

한국 문화를 좋아하는 외국인 관광객이 **회원가입 → 한식·K-Pop·드라마·여행 등 카테고리별 게시글 → 댓글**을 할 수 있는 커뮤니티입니다.

React가 아니라 **Java 웹 MVC + 게시판 CRUD** 학습용 프로젝트입니다.  
관련: [README.md](./README.md)

코드를 한 줄씩 읽기보다 아래 순서가 이해하기 쉽습니다.

**DB → DAO/DTO → FrontController → JSP**

---

## 1. 사이트에서 구현한 기능

### ① 게시글 목록 · 카테고리

- 전체 게시글 조회  
- K-Food, K-Pop, K-Drama 등 **카테고리별** 조회  
- 제목 · 작성자 · 조회수 표시  
- **페이징** (`page`, pageSize=10)

### ② 게시글 상세 · 댓글

- 내용 · 작성자 확인  
- 조회수 증가  
- 댓글 목록  
- 로그인 사용자 댓글 작성  

### ③ 회원가입 · 로그인

- **이메일** 가입 · 이메일 중복 검사  
- 로그인 · 로그아웃  
- 세션 `loginMember`로 상태 유지  
- 국적 · 선호 언어 입력  

### ④ 게시글 관리

- 등록 · 수정 · 삭제  
- 작성자 본인 여부 확인 (`edit` / `delete` 시 `member_id`)  

---

## 2. 폴더 구조

Maven/Gradle이 아닌 **Eclipse Dynamic Web Project** 형태입니다.  
CSS/JS 별도 파일은 거의 없고, 디자인은 JSP 안 `<style>`로 작성되어 있습니다.

```text
17-Kculture-Platform/
├── schema.sql                 # DB·테이블·초기 카테고리·테스트 회원
├── schema_add_categories.sql  # 기존 DB에 카테고리만 추가할 때
├── README.md
├── 17-Kculture-Platform-상세정리.md  ← 이 문서
├── src/kculture/
│   ├── FrontController.java   # @WebServlet("*.do") 요청 분배
│   ├── DBConnection.java
│   ├── MemberDAO / MemberDTO
│   ├── PostDAO / PostDTO
│   ├── CommentDAO / CommentDTO
│   └── CategoryDAO / CategoryDTO
└── WebContent/
    ├── index.jsp              # 목록으로 이동
    ├── list.jsp · view.jsp
    ├── write.jsp · edit.jsp
    ├── login.jsp · join.jsp
    └── WEB-INF/web.xml
```

| 위치 | 역할 |
|------|------|
| `schema.sql` | DB 생성 · 초기 데이터 |
| `src/kculture/` | Controller · Model(DAO/DTO) |
| `WebContent/*.jsp` | View |

---

## 3. MVC 패턴 (가장 중요)

```text
View (JSP)
  list · view · login · write · edit · join
        ↑ forward / ↓ form submit
Controller — FrontController.java
  *.do 요청 → switch(action) → DAO 호출 → JSP 또는 redirect
        ↓
Model — DAO / DTO
  DAO = DB SQL · DTO = 데이터 상자
        ↓
MySQL (kculture_platform)
  member · category · post · comment
```

### 목록 요청 예시 (`/list.do`)

1. 브라우저가 `/list.do` 요청  
2. `FrontController`가 받음 (`action = list`)  
3. `PostDAO.list(...)`가 MySQL 조회  
4. 결과가 `List<PostDTO>`  
5. `request.setAttribute("list", list)`  
6. `list.jsp`가 HTML로 출력  

**한 줄:** Controller가 DAO를 부르고, JSP가 결과를 보여 준다.

---

## 4. Java 파일 역할

| 파일 | 역할 | 핵심 |
|------|------|------|
| `FrontController` | 모든 `*.do` 처리 | `process()` · `switch` |
| `DBConnection` | MySQL 연결 · close | `getConnection()`, `close()` |
| `MemberDAO` | 회원 | `login`, `join`, `existsEmail` |
| `PostDAO` | 게시글 | `list`, `count`, `insert`, `update`, `delete`, `increaseView` |
| `CommentDAO` | 댓글 | `listByPostId`, `insert` |
| `CategoryDAO` | 카테고리 | `list`, `findById` |
| `*DTO` | 전달 객체 | getter/setter |

### DTO vs DAO

```java
// DTO — 상자
PostDTO post = new PostDTO();
post.setTitle("한국 여행 후기");
post.setContent("서울 여행이 정말 즐거웠습니다.");

// DAO — DB 담당
// INSERT INTO post (category_id, member_id, title, content) VALUES (?, ?, ?, ?)
```

| 역할 | 비유 |
|------|------|
| DTO | 데이터를 담는 상자 |
| DAO | DB에 넣거나 꺼내는 담당자 |
| Controller | DAO 호출 + 화면 연결 |
| JSP | 사용자에게 보이는 화면 |

---

## 5. FrontController — URL 라우팅

```java
@WebServlet("*.do")
public class FrontController extends HttpServlet { ... }
```

`.do`로 끝나는 요청을 이 서블릿 하나가 처리합니다.

### action 추출

```java
String uri = request.getRequestURI();
String path = uri.substring(uri.lastIndexOf("/") + 1);
String action = path.replace(".do", "");
// /프로젝트/list.do → action = "list"
```

### URL 표

| URL | 기능 |
|-----|------|
| `/list.do` | 게시글 목록 (카테고리·페이징) |
| `/view.do?id=1` | 상세 + 조회수 + 댓글 |
| `/write.do` | 글쓰기 화면 (로그인) |
| `/writeProcess.do` | 게시글 저장 → 상세로 redirect |
| `/edit.do?id=1` | 수정 화면 (작성자만) |
| `/editProcess.do` | 수정 처리 |
| `/delete.do?id=1` | 삭제 (작성자 `member_id` 조건) |
| `/commentProcess.do` | 댓글 저장 |
| `/login.do` / `/loginProcess.do` | 로그인 |
| `/join.do` / `/joinProcess.do` | 회원가입 |
| `/logout.do` | 세션 무효화 |

### forward vs redirect

| 방식 | 언제 |
|------|------|
| `forward` | 화면 보여 주기 (`list`, `view`, `login` …) |
| `sendRedirect` | 등록·수정·삭제·로그아웃 후 **다른 URL로 이동** (새로고침 중복 방지) |

### 세션

```java
session.setAttribute("loginMember", m);  // 로그인 성공
MemberDTO login = (MemberDTO) session.getAttribute("loginMember");
```

글쓰기·수정·삭제·댓글은 `loginMember`가 없으면 `login.do`로 보냅니다.

---

## 6. MySQL 구조

DB 이름: **`kculture_platform`**

| 테이블 | 주요 컬럼 |
|--------|-----------|
| `member` | id, email(UNIQUE), password, name, nationality, language, created_at |
| `category` | id, code, name_en, name_ko, icon, sort_order |
| `post` | id, category_id, member_id, title, content, view_count, created_at, updated_at |
| `comment` | id, post_id, member_id, content, created_at |

### FK 관계

```text
member ──1:N──► post ◄──N:1── category
member ──1:N──► comment
post   ──1:N──► comment   (게시글 삭제 시 댓글 CASCADE)
```

- `post.member_id` → `member.id`  
- `post.category_id` → `category.id`  
- `comment.post_id` → `post.id` (`ON DELETE CASCADE`)  
- `comment.member_id` → `member.id`  

### SQL 실행

1. Workbench에서 `schema.sql` 전체 실행  
2. DB `kculture_platform` · 테이블 4개 확인  
3. `schema_add_categories.sql`은 **이미 있는 DB에 카테고리만 추가**할 때 — 신규면 `schema.sql`만으로 충분  

### 테스트 계정

| Email | Password |
|-------|----------|
| `tourist@test.com` | `1234` |
| `visitor@test.com` | `1234` |

학습용 **평문** 비밀번호입니다.

---

## 7. JSP 화면 역할

| 파일 | 역할 |
|------|------|
| `index.jsp` | 첫 접속 → 목록으로 |
| `list.jsp` | 카테고리 탭 · 글 목록 · 페이징 |
| `view.jsp` | 본문 · 댓글 · 수정/삭제(본인) |
| `write.jsp` | 등록 폼 → `writeProcess.do` |
| `edit.jsp` | 수정 폼 → `editProcess.do` |
| `login.jsp` | 로그인 |
| `join.jsp` | 회원가입 |

Controller가 넣은 attribute 예:

- `list`, `categories`, `categoryId`, `currentPage`, `totalPages`  
- `post`, `comments`  
- `error` (로그인·가입 실패)  

---

## 8. 실행 방법

1. **환경:** Eclipse · JDK · **Tomcat 10.1** (소스: `jakarta.servlet.*`) · MySQL  
2. **DB:** `schema.sql` 실행 → `DBConnection.java`의 URL·user·password 맞추기 (`root`/`1234` 예시)  
3. **프로젝트:** Dynamic Web Project · `WEB-INF/lib`에 **MySQL Connector/J**  
4. **접속:** `http://localhost:8080/프로젝트컨텍스트/list.do`  
5. **테스트 순서:** 목록 → 상세 → 가입 → 로그인 → 글쓰기 → 수정·삭제 → 댓글  

---

## 9. 학습용으로 알아 둘 개선 점

| 항목 | 현재 | 권장 |
|------|------|------|
| 비밀번호 | 평문 저장·비교 | BCrypt 등 해시 |
| DB 계정 | 소스에 하드코딩 | 설정 파일·환경 변수 |
| 수정 권한 | edit 화면 + DAO `WHERE id=? AND member_id=?` | Process에서도 동일하게 엄격히 |
| XSS | JSP 직접 출력 | HTML 이스케이프 |
| JDBC 자원 | `DBConnection.close` | try-with-resources 권장 |

---

## 10. 추천 학습 순서

| 단계 | 파일 | 볼 포인트 |
|------|------|-----------|
| 1 | `schema.sql` | 테이블 · FK · 카테고리 초기 데이터 |
| 2 | `DBConnection.java` | JDBC 연결 · close |
| 3 | `PostDTO` + `PostDAO` | list/count/insert/update/delete |
| 4 | `FrontController.java` | `*.do` · switch · forward/redirect · 세션 |
| 5 | `list.jsp` + `view.jsp` | attribute 출력 · 댓글 폼 |

### 추천 과제

1. **`/list.do` 흐름**을 완전히 추적 (파라미터 → DAO → setAttribute → JSP)  
2. 그다음 **`/writeProcess.do`** (로그인 검사 → insert → `view.do` redirect)  

이 두 갈래를 이해하면 프로젝트 전체 구조가 잡힙니다.

---

## 11. 꼭 기억할 핵심

| 개념 | 한 줄 |
|------|--------|
| MVC | View=JSP, Controller=`FrontController`, Model=DAO/DTO |
| `*.do` | 한 서블릿이 URL action으로 분기 |
| forward | 화면 표시 |
| redirect | 처리 후 다른 URL로 이동 |
| Session | `loginMember`로 로그인 유지 |
| category | 게시글을 K-컬처 주제로 분류 |
| comment | post에 1:N, 게시글 삭제 시 CASCADE |

`travel-stay`·`member-board`가 **JSP 안에서 DAO 호출**이라면,  
이 프로젝트는 **서블릿(FrontController)이 가운데서 요청을 모으는** 다음 단계 학습용입니다.
