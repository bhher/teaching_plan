# travel-stay 상세 정리

**소스:** `jsp/travel-stay`  
**DB:** `travel_stay` (MySQL / MariaDB 동일 JDBC)  
**범위:** 여행지 소개 · 주변 숙소 · 회원 · 찜 · 리뷰 · 관리자 CRUD(+이미지)

코드를 한 줄씩 읽기보다 아래 순서가 이해하기 쉽습니다.

**DB 설계 → Java(DTO/DAO) → JSP 화면 → 사용자 기능 → 관리자 기능**

관련: [README.md](./README.md) · [ERD 연관관계](./travel-stay-ERD-연관관계.md)

---

## 1. 프로젝트 전체 구조

정적 HTML 사이트가 아니라, **Java + JSP**로 서버에서 데이터를 처리하는 웹 애플리케이션입니다.

```text
사용자 화면 (JSP)
  여행지 목록·상세 · 숙소 · 가입·로그인 · 찜 · 리뷰 · 관리자
        ↓ 요청
Java DAO / DTO
  조회·저장·수정·삭제 · 데이터 전달
        ↓ SQL
MySQL (travel_stay)
  member · destination · stay · wishlist · review
```

### 쉬운 비유

| 역할 | 비유 |
|------|------|
| JSP | 접수 창구 (화면·입력·출력) |
| DAO | DB에 SQL을 실행하는 담당자 |
| DTO | 데이터를 담아 전달하는 상자 |
| DB | 실제 저장소 |
| Session | 로그인 출입증 |
| DBConnection | Java ↔ MySQL 통로 |

### 한 번의 요청 흐름 (여행지 목록)

1. 브라우저가 `destination/list.jsp` 요청  
2. JSP가 `DestinationDAO.search(region, keyword)` 호출  
3. DAO가 MySQL에 SQL 실행  
4. 결과가 `DestinationDTO` 목록에 담김  
5. JSP가 HTML로 출력  
6. 사용자가 목록을 확인  

**한 줄:** DAO = DB 작업, DTO = 전달, JSP = 화면.

### 폴더 구성

```text
travel-stay/
├── db_setup.sql
├── README.md
├── travel-stay-상세정리.md   ← 이 문서
├── src/
│   ├── util/DBConnection.java
│   ├── dto/ Destination · Stay · Member · Review
│   └── dao/ Destination · Stay · Member · Review · Wishlist
└── WebContent/
    ├── index.jsp
    ├── include/ header · footer · imgUtil.jspf
    ├── member/  가입·로그인·로그아웃
    ├── destination/ list · view
    ├── wishlist/ list · toggleProcess
    ├── review/ writeProcess
    ├── admin/  여행지·숙소 CRUD (+ Multipart)
    ├── uploads/
    └── WEB-INF/web.xml · lib/
```

---

## 2. 데이터베이스 설계

파일: `db_setup.sql` · DB 이름: **`travel_stay`**

### 5개 테이블

| 테이블 | 역할 | 주요 컬럼 |
|--------|------|-----------|
| `member` | 회원 | id, password, name, **role**(USER/ADMIN), reg_date |
| `destination` | 여행지 | name, region, description, image |
| `stay` | 숙소 | **destination_id**, name, address, price, description, image |
| `wishlist` | 찜 | **member_id + destination_id** (복합 PK) |
| `review` | 리뷰 | destination_id, member_id, rating(1~5), content |

### 관계 (ER 개념)

자세한 설명: **[travel-stay-ERD-연관관계.md](./travel-stay-ERD-연관관계.md)**

```text
member ──┬── review
         └── wishlist
                │
destination ────┼── stay
                └── review / wishlist
```

예: 경복궁 `id = 1`이면 주변 숙소는:

```sql
SELECT * FROM stay WHERE destination_id = 1;
```

핵심은 **`destination_id`** — 여행지 PK를 숙소가 **외래 키(FK)** 로 참조합니다.

### ON DELETE CASCADE

```sql
FOREIGN KEY (destination_id)
REFERENCES destination(id)
ON DELETE CASCADE
```

여행지를 삭제하면 **연결된 숙소·리뷰·찜도 함께 삭제**됩니다.  
(wishlist·review의 member/destination FK에도 CASCADE가 있습니다.)

### 주의

`DROP TABLE IF EXISTS`가 있으므로, **실습용 DB인지 확인**한 뒤 실행하세요.  
이미 쌓인 데이터가 있으면 지워집니다.

### 샘플 계정 (`db_setup.sql`)

| 아이디 | 비밀번호 | 역할 |
|--------|----------|------|
| `admin` | `1234` | ADMIN |
| `hong` | `1234` | USER |
| `kim` | `1234` | USER |

학습용 **평문** 비밀번호입니다. 실서비스에는 해시(BCrypt 등)가 필요합니다.

---

## 3. Java DAO · DTO · DBConnection

### 파일 역할

| 파일 | 역할 |
|------|------|
| `DestinationDAO` | 여행지 검색·상세·등록·수정·삭제 (+ 숙소수·평점 JOIN) |
| `StayDAO` | 여행지별 숙소 조회·관리 CRUD |
| `MemberDAO` | 가입·로그인·중복 등 |
| `ReviewDAO` | 리뷰 등록·목록 |
| `WishlistDAO` | 찜 추가·삭제·여부·내 목록 |
| `*DTO` | 각 테이블(또는 JOIN 결과) 전달 |
| `DBConnection` | `jdbc:mysql://localhost:3306/travel_stay` 연결 |

`DBConnection` 기본값 예: 사용자 `root`, 비밀번호 `1234` — **본인 MySQL에 맞게 수정**.

### PreparedStatement 핵심

```java
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, region);
ResultSet rs = ps.executeQuery();
```

| API | 용도 |
|-----|------|
| `PreparedStatement` | SQL 준비 + `?`에 값 안전 전달 |
| `setString` / `setInt` | 플레이스홀더에 값 |
| `executeQuery` | SELECT |
| `ResultSet` | 조회 행 읽기 |
| `executeUpdate` | INSERT / UPDATE / DELETE |

### DestinationDAO.search — 목록의 핵심

지역·키워드를 받아 목록을 반환합니다. 실제 SQL은:

- `LEFT JOIN stay` → `COUNT(DISTINCT s.id)` = **숙소 수**  
- `LEFT JOIN review` → `AVG(rating)`, `COUNT(review)` = **평점·리뷰 수**  
- `region` / `keyword`(LIKE) 조건은 있을 때만 붙임  
- `GROUP BY` 후 `ORDER BY id DESC`

DTO에는 기본 필드뿐 아니라 `stayCount`, `avgRating`, `reviewCount`도 담깁니다.

### StayDAO — 상세의 숙소

```java
selectByDestination(destinationId)
// WHERE destination_id = ? ORDER BY price
```

상세 화면에서 주변 숙소 표를 채울 때 사용합니다.

---

## 4. JSP 화면 실행 순서

### ① `index.jsp` — 메인

여행지 둘러보기 · 로그인 · (관리자면) 관리 메뉴로 이동.

### ② `destination/list.jsp` — 여행지 목록

```jsp
String region = request.getParameter("region");
String keyword = request.getParameter("keyword");
List<DestinationDTO> list = new DestinationDAO().search(region, keyword);
```

| 코드 | 의미 |
|------|------|
| `getParameter("region")` | 선택한 지역 |
| `getParameter("keyword")` | 검색어 |
| `dao.search(...)` | 조건에 맞는 여행지 |
| `List<DestinationDTO>` | 여러 건 |

출력:

```jsp
<% for (DestinationDTO d : list) { %>
  <%= d.getName() %> · <%= d.getRegion() %>
  숙소 <%= d.getStayCount() %>곳 · ★ <%= d.getAvgRating() %>
<% } %>
```

`d` = 여행지 **한 건**. 카드 클릭 → `view.jsp?id=...`

검색 폼은 **GET** (`method="get"`)이라 URL에 조건이 남습니다.

### ③ `destination/view.jsp` — 상세 (가장 연결이 많은 화면)

```text
id 검증 → DestinationDAO.selectById
       → StayDAO.selectByDestination
       → ReviewDAO.selectByDestination
       → (로그인 시) WishlistDAO.exists
       → 설명 · 숙소 표 · 찜 버튼 · 리뷰 폼·목록
```

로그인 전이면 「로그인 후 찜」「리뷰는 로그인 후」안내만 보입니다.

### ④ `include/imgUtil.jspf` — 이미지 URL 규칙

```text
http(s)로 시작 → 그대로 사용 (샘플 Unsplash URL)
그 외         → contextPath + "/uploads/" + 파일명
비어 있음     → placehold.co 대체 이미지
```

관리자가 **URL만** 넣어도, **파일 업로드**해도 화면에 맞게 보입니다.

---

## 5. 회원가입 · 로그인 · 세션

폴더: `WebContent/member/`

| 파일 | 기능 |
|------|------|
| `join.jsp` | 가입 폼 |
| `joinProcess.jsp` | 검증·저장 |
| `login.jsp` | 로그인 폼 |
| `loginProcess.jsp` | 인증·세션·이동 |
| `logout.jsp` | `session.invalidate()` |

### 로그인 성공 시 세션

```jsp
session.setAttribute("loginId", m.getId());
session.setAttribute("loginName", m.getName());
session.setAttribute("loginRole", m.getRole());
```

- 일반 사용자 → `destination/list.jsp`  
- **ADMIN** → `admin/index.jsp`  

`header.jsp`는 `loginId` 유무로 메뉴(로그인/로그아웃·찜·관리자)를 바꿉니다.

### 관리자 검사 `admin/adminCheck.jspf`

```jsp
if (!"ADMIN".equals(session.getAttribute("loginRole"))) {
    response.sendRedirect(... + "/member/login.jsp");
    return;
}
```

관리자 JSP·Process에 include하여 **URL 직접 접근**도 막습니다.  
화면뿐 아니라 **저장·삭제 Process에도 반드시** 넣어야 합니다. (현재 프로젝트 적용됨)

---

## 6. 찜하기 · 리뷰

### 찜 — `wishlist/`

| 파일 | 역할 |
|------|------|
| `toggleProcess.jsp` | 있으면 삭제, 없으면 추가 후 상세로 복귀 |
| `list.jsp` | 내 찜 목록 (로그인 필수) |

```text
로그인 확인 → exists(loginId, destinationId)
  → true 면 remove / false 면 add
  → view.jsp?id=...
```

복합 PK `(member_id, destination_id)` 덕분에 같은 여행지를 두 번 찜하지 않습니다.

### 리뷰 — `review/writeProcess.jsp`

```text
로그인 확인 → rating 1~5 · content 비어 있지 않음
  → ReviewDTO → ReviewDAO.insert → view.jsp
```

목록·평균 평점은 `DestinationDAO`의 JOIN / `ReviewDAO.selectByDestination`으로 상세에 표시됩니다.

---

## 7. 관리자 기능 · 이미지 업로드

폴더: `WebContent/admin/` · **role = ADMIN** 만

| 파일 | 역할 |
|------|------|
| `index.jsp` | 관리 메뉴 |
| `destList` / `destForm` / `destSaveProcess` / `destDeleteProcess` | 여행지 CRUD |
| `stayList` / `stayForm` / `staySaveProcess` / `stayDeleteProcess` | 숙소 CRUD |

### 이미지 — COS `MultipartRequest`

```jsp
String savePath = application.getRealPath("/uploads");
if (!dir.exists()) dir.mkdirs();

MultipartRequest multi = new MultipartRequest(
    request, savePath, 5 * 1024 * 1024, "utf-8",
    new DefaultFileRenamePolicy());
```

| 인수 | 의미 |
|------|------|
| `request` | 업로드 요청 |
| `savePath` | 저장 폴더 |
| `5*1024*1024` | 최대 5MB |
| `"utf-8"` | 인코딩 |
| `DefaultFileRenamePolicy` | 동일 파일명 자동 변경 |

`WEB-INF/lib`에 **`cos.jar`** + MySQL Connector 필요.

### destSaveProcess 이미지 우선순위

1. 파일 업로드됨 → **파일명** 저장  
2. 아니면 `imageUrl` 입력 → **URL** 저장  
3. 수정인데 둘 다 없음 → **기존 image 유지**  

`imgUtil`이 URL/파일명을 구분해 화면에 보여 줍니다.

> cos 없이 쓰려면 이미지 **URL만** 넣어도 샘플·등록이 동작합니다. (README 참고)

여행지 삭제 시 FK **CASCADE**로 숙소·리뷰·찜이 같이 사라집니다.

---

## 8. 실행 순서

1. MySQL 실행 → `db_setup.sql`을 **실습 DB**에 실행  
2. `DBConnection.java` 주소·계정·비밀번호 맞춤  
3. `WEB-INF/lib`: `mysql-connector-j`, **`cos.jar`**  
4. Eclipse Dynamic Web Project + Tomcat (web.xml은 Servlet 4.0 계열)  
5. 배포 후 `http://localhost:8080/travel-stay/`  
6. 확인 순서: **목록 → 상세 → 로그인 → 찜·리뷰 → 관리자 CRUD**  

하드코딩된 `root`/`1234`, 샘플 평문 비밀번호는 실습 전용입니다.

---

## 9. 학습 체크리스트 (추천 순서)

처음부터 전 파일을 읽을 필요는 없습니다.

| # | 파일 | 볼 포인트 |
|---|------|-----------|
| 1 | `db_setup.sql` | 테이블, PK/FK, CASCADE, 초기 데이터 |
| 2 | `DBConnection.java` | JDBC URL·계정 |
| 3 | `DestinationDTO.java` | 필드 + stayCount/avgRating |
| 4 | `DestinationDAO.java` | search JOIN, CRUD |
| 5 | `destination/list.jsp` | getParameter → search → 반복 출력 |
| 6 | `destination/view.jsp` | 숙소·찜·리뷰 연결 |
| 7 | `MemberDAO` + `loginProcess.jsp` | 세션 loginId/Name/Role |
| 8 | `admin/` + `destSaveProcess` | adminCheck, Multipart, 이미지 유지 |

---

## 10. 기능별 한눈 흐름

```text
[사용자]
목록 검색 → 상세(숙소 JOIN) → 로그인 → 찜 토글 / 리뷰 작성 → 내 찜 목록

[관리자]
ADMIN 로그인 → admin/
  여행지 등록·수정·삭제 (파일 또는 URL)
  숙소 등록·수정·삭제 (destination_id로 연결)
```

---

## 11. 꼭 기억할 핵심

| 개념 | 한 줄 |
|------|--------|
| DB | 데이터 저장 |
| DTO | 담아 전달 |
| DAO | SQL 실행 |
| JSP | 화면 + 요청 처리 |
| Session | 로그인·권한 유지 |
| JOIN | 숙소 수·평점을 목록에 한 번에 |
| FK + CASCADE | 여행지 삭제 시 연결 데이터 정리 |
| MultipartRequest | 관리자 이미지 업로드 |
| imgUtil | URL vs uploads 파일명 |

이 프로젝트로 **JDBC, DTO/DAO, JSP, 세션, 외래 키, JOIN, CRUD, 파일 업로드**를 한 번에 연습할 수 있습니다.

다만 학습용에 가깝습니다. 실서비스에는 **비밀번호 해시 · XSS 이스케이프 · 업로드 확장자/MIME 검사 · 입력값 검증 강화**가 필요합니다.

---

## 12. 다음에 연습하면 좋은 것

| 단계 | 내용 |
|------|------|
| 1 | 리뷰 수정·삭제 (작성자만) |
| 2 | 여행지/숙소 페이징 · 가격 필터 |
| 3 | 삭제 시 `uploads` 물리 파일도 삭제 |
| 4 | 처리 로직을 Servlet/Controller로 분리 |
| 5 | 비밀번호 해시 · CSRF · XSS 대비 |

등록·목록만 익힌 뒤라면, **상세(view)의 여러 DAO 호출**과 **admin Multipart**부터 다시 읽어 보는 것을 추천합니다.
