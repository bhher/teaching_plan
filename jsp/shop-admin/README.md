# JSP 관리자 상품 등록 미니 프로젝트 (shop-admin)

`jsp/shop-admin` — Eclipse + **Tomcat 10.1** + MySQL + DAO/DTO + Bootstrap 5

---

## 1. 프로젝트란?

관리자만 로그인하여 **상품을 등록·조회·수정·삭제·검색**하는 쇼핑몰 관리 미니 사이트입니다.

| 기능 | 파일 |
|------|------|
| 관리자 로그인 | `login.jsp` → `loginProcess.jsp` |
| 상품 목록 · 검색 | `productList.jsp` |
| 상품 등록 | `productWrite.jsp` → `productWriteProcess.jsp` |
| 상품 상세 | `productView.jsp` |
| 상품 수정 | `productUpdate.jsp` → `productUpdateProcess.jsp` |
| 상품 삭제 | `productDeleteProcess.jsp` |

---

## 2. 폴더 구조

```text
shop-admin/
├── db_setup.sql
├── README.md
├── src/
│   ├── util/DBConnection.java
│   ├── dto/ProductDTO.java
│   ├── dto/AdminDTO.java
│   ├── dao/ProductDAO.java
│   └── dao/AdminDAO.java
└── WebContent/
    ├── index.jsp
    ├── include/
    │   ├── header.jsp
    │   ├── footer.jsp
    │   └── adminCheck.jspf
    ├── admin/
    │   ├── login.jsp
    │   ├── loginProcess.jsp
    │   ├── logout.jsp
    │   ├── productList.jsp
    │   ├── productWrite.jsp
    │   ├── productWriteProcess.jsp
    │   ├── productView.jsp
    │   ├── productUpdate.jsp
    │   ├── productUpdateProcess.jsp
    │   └── productDeleteProcess.jsp
    └── WEB-INF/
        ├── web.xml          ← Jakarta EE 5 (Tomcat 10)
        └── lib/             ← mysql-connector-j JAR
```

---

## 3. 데이터베이스

### 생성 SQL

`db_setup.sql` 실행 → DB 이름 **`shop_db`**

| 테이블 | 역할 |
|--------|------|
| `admin` | 관리자 계정 (`id`, `password`, `name`) |
| `product` | 상품 (`id`, `name`, `price`, `stock`, `category`, `description`, `reg_date`) |

샘플 관리자: **`admin` / `1234`**

---

## 4. 파일별 역할

| 파일 | 역할 |
|------|------|
| `DBConnection.java` | MySQL 연결 (`shop_db`) |
| `AdminDTO` / `AdminDAO` | 관리자 로그인 검증 |
| `ProductDTO` / `ProductDAO` | 상품 CRUD · 상품명 LIKE 검색 |
| `header.jsp` / `footer.jsp` | 공통 메뉴 · Bootstrap |
| `adminCheck.jspf` | 세션 `adminId` 없으면 로그인으로 보냄 |
| `loginProcess.jsp` | 세션에 `adminId`, `adminName` 저장 |
| `productWriteProcess.jsp` | 검증 후 INSERT → **목록으로 redirect** |
| `productUpdateProcess.jsp` | **id 기준** UPDATE |
| `productDeleteProcess.jsp` | **id 기준** DELETE |

모든 SQL은 **PreparedStatement** + `?` 사용.

---

## 5. 실행 흐름 (초보자용)

```text
① 로그인
   login.jsp → loginProcess.jsp → AdminDAO.login()
   → session(adminId) → productList.jsp

② 목록 · 검색
   productList.jsp → ProductDAO.selectAll() 또는 searchByName()

③ 등록
   productWrite.jsp → productWriteProcess.jsp → insert()
   → productList.jsp

④ 상세
   productView.jsp?id=번호 → selectById()

⑤ 수정
   productUpdate.jsp?id=번호 → productUpdateProcess.jsp → update()
   → productView.jsp?id=번호

⑥ 삭제
   productView의 삭제 폼 → productDeleteProcess.jsp → delete()
   → productList.jsp

⑦ 로그아웃
   logout.jsp → session.invalidate() → login.jsp
```

---

## 6. DB 생성부터 실행까지 (순서)

### ① MySQL

1. MySQL Workbench 또는 커맨드에서 `db_setup.sql` 전체 실행  
2. `USE shop_db;` 후 `SELECT * FROM admin;` / `SELECT * FROM product;` 확인  

### ② Eclipse 프로젝트

1. **File → New → Dynamic Web Project**  
   - Project name: `shop-admin`  
   - Target runtime: **Apache Tomcat 10.1**  
   - Dynamic web module version: **5.0** (Jakarta)  
2. 이 폴더의 소스를 복사  
   - `src/` → 프로젝트 `src` (또는 `src/main/java`)  
   - `WebContent/` → 프로젝트 `WebContent` (또는 `src/main/webapp`)  
3. `WEB-INF/lib`에 **mysql-connector-j** JAR 추가  
4. `util/DBConnection.java`의 비밀번호(`1234`)를 본인 MySQL에 맞게 수정  

### ③ Tomcat 10.1

1. Servers 뷰에 Tomcat 10.1 추가  
2. 프로젝트 Add and Remove → shop-admin 추가  
3. 서버 Start  

### ④ 브라우저

```text
http://localhost:8080/shop-admin/
```

→ 로그인 화면 → `admin` / `1234` → 상품 목록

---

## 7. Tomcat 10.1 참고

- Tomcat 10은 **Jakarta Servlet** 패키지를 사용합니다.  
- 이 프로젝트는 **JSP + JDBC** 중심이라 별도 Servlet 클래스가 없고,  
  `web.xml`만 Jakarta EE 5 스키마로 맞춰 두었습니다.  
- Tomcat **9**를 쓸 경우 `web.xml`의 `jakarta.ee`를 `xmlns.jcp.org/xml/ns/javaee` version 4.0으로 바꿔도 JSP는 동일하게 동작합니다.

---

## 8. 구현 조건 체크

| 조건 | 적용 |
|------|------|
| 등록 후 목록 이동 | `productWriteProcess` → `productList.jsp` |
| 수정·삭제 id 기준 | hidden / `?id=` + DAO WHERE id |
| PreparedStatement | 모든 DAO |
| 입력값 검증 | Process JSP (필수값·숫자) |
| 세션 로그인 확인 | `adminCheck.jspf` |
| Bootstrap 5 | CDN |
| DAO / DTO | Product · Admin |

---

## 9. 학습 포인트

1. **화면 JSP** vs **Process JSP** (폼 → 검증 → DAO → redirect)  
2. **세션**으로 관리자만 접근  
3. **DTO**에 담고 **DAO**에서 SQL  
4. 검색은 `LIKE ?` + `"%" + keyword + "%"`  
5. `AUTO_INCREMENT` id는 INSERT에 넣지 않음  

수업 시 `loginProcess` → `productWriteProcess` → `ProductDAO` 순으로 읽으면 전체 흐름이 잡힙니다.
