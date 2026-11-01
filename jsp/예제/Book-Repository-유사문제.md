# 도서 쇼핑몰 유사 문제 — DTO · Repository · useBean · 목록 JSP

**참고 패턴**

```text
dto.Book          ← JavaBean (Serializable)
dao.BookRepository ← 샘플 데이터 + getAllBooks()
books.jsp         ← useBean + for + getter 출력
```

**핵심**

| 구성 | 역할 |
|------|------|
| DTO / Bean | private 필드 + getter/setter (+ Serializable) |
| Repository | ArrayList에 객체 담아 보관 · 조회 |
| JSP | `useBean`으로 Repository 사용 · 목록 반복 출력 |

---

## 문제 ① 상품 목록 (예제와 거의 동일) ⭐⭐

도서 대신 **상품(Product)** 으로 같은 구조를 만드세요.

### 1) `dto.Product` (JavaBean)

필드 예시:

| 필드 | 타입 | 설명 |
|------|------|------|
| productId | String | 상품 ID |
| name | String | 상품명 |
| unitPrice | int | 가격 |
| brand | String | 브랜드 |
| description | String | 설명 |
| category | String | 분류 |
| unitsInStock | long | 재고 |

요구사항:

- `implements Serializable`
- `serialVersionUID` 선언
- 기본 생성자 + `(productId, name, unitPrice)` 생성자
- 모든 필드 getter / setter

### 2) `dao.ProductRepository`

- `ArrayList<Product>` 에 상품 **3개** 이상 등록 (생성자에서)
- `getAllProducts()` 메서드 제공

샘플 예:

```text
P1001 | 무선마우스 | 25000 | 로지텍 | 주변기기
P1002 | 기계식키보드 | 89000 | 키크론 | 주변기기
P1003 | USB허브 | 18000 | 벨킨 | 주변기기
```

### 3) `products.jsp`

```jsp
<jsp:useBean id="productDAO" class="dao.ProductRepository" scope="session" />
```

- `getAllProducts()`로 목록 받기
- `for` 문으로 카드/컬럼 출력 (Bootstrap 가능)
- 출력: 상품명, 브랜드, 가격, 설명 앞 40자 + `...`

---

## 문제 ② ID로 한 건 조회 ⭐⭐

`BookRepository`(또는 `ProductRepository`)에 메서드를 추가하세요.

```java
public Book getBookById(String bookId) {
    // listOfBooks를 순회하며 bookId가 같으면 반환
    // 없으면 null
}
```

**JSP:** `book.jsp?id=ISBN1234`

```jsp
<%
  String id = request.getParameter("id");
  Book book = bookDAO.getBookById(id);
%>
```

요구사항:

1. 목록(`books.jsp`)에서 각 도서에 **상세보기** 링크  
   `book.jsp?id=<%= book.getBookId() %>`
2. 상세 페이지에서 제목·저자·가격·전체 설명·재고 출력
3. 없는 ID면 `"도서를 찾을 수 없습니다."` 출력

---

## 문제 ③ 영화 목록으로 바꾸기 ⭐⭐

같은 패턴으로 **Movie** 를 만드세요.

| 필드 | 타입 |
|------|------|
| movieId | String |
| title | String |
| director | String |
| genre | String |
| runningTime | int (분) |
| description | String |

- `MovieRepository`에 영화 3편 등록
- `movies.jsp`에서 제목 · 감독 · 장르 · 상영시간 출력
- `scope="session"` 으로 `useBean` 사용

---

## 문제 ④ 카테고리 필터 (JSP) ⭐⭐⭐

도서/상품 목록에서 **카테고리**로 걸러 보이기.

1. `books.jsp?category=IT모바일` 또는 form select  
2. `category` 파라미터가 없거나 `"전체"`이면 전체 목록  
3. 있으면 해당 카테고리만 출력  

힌트:

```jsp
String category = request.getParameter("category");
for (...) {
  if (category == null || category.equals("전체")
      || book.getCategory().equals(category)) {
    // 출력
  }
}
```

(선택) Repository에 `getBooksByCategory(String category)` 추가

---

## 문제 ⑤ 장바구니용 Serializable 확인 ⭐

`Book`이 `Serializable`인 이유를 서술하고, 아래를 실습하세요.

1. 세션에 선택한 책 ID 저장  

```jsp
<%
  session.setAttribute("cartBookId", book.getBookId());
%>
```

2. 다른 JSP에서 `session.getAttribute("cartBookId")`로 읽어 출력  
3. (서술) `scope="session"`인 `useBean`과 세션 저장 객체가 Serializable이어야 하는 이유 2줄 이상

---

## 문제 ⑥ 코드 고쳐보기 (디버깅) ⭐⭐

아래는 **잘못된 코드**입니다. 고쳐 보세요.

```jsp
<%-- 잘못됨 --%>
<jsp:useBean id="bookDAO" class="dao.BookRepository" scope="session" />
<%
  ArrayList list = bookDAO.getAllBooks();  // 제네릭·타입?
  for (int i = 0; i < list.size(); i++) {
%>
  <p><%= list.get(i).getName() %></p>   <%-- 컴파일/실행 문제? --%>
<%
  }
%>
```

확인할 점:

- import (`dto.Book`, `ArrayList`)
- `ArrayList<Book>` 캐스팅/선언
- `Book book = listOfBooks.get(i);` 후 getter 사용
- `description`이 60자보다 짧을 때 `substring(0,60)` 예외 → 길이 체크

---

## 제출물 체크리스트

- [ ] DTO: Serializable + serialVersionUID + getter/setter  
- [ ] Repository: 생성자에서 샘플 3개 이상 add  
- [ ] JSP: `useBean` + `getAllXxx()` + for 출력  
- [ ] (②) 상세 링크 + `getXxxById`  
- [ ] 한글·UTF-8 `pageEncoding` 확인  

---

## 수업 추천 순서

```text
① 상품 목록 (구조 복습)
    ↓
② ID 상세 조회
    ↓
④ 카테고리 필터 또는 ③ 영화로 응용
    ↓
⑤ 직렬화·세션 개념 정리
```

**오늘 숙제 추천:** ① + ②  
**응용:** ③ 또는 ④

---

## 학생용 한 줄 정리

```text
Book (데이터 한 권)
   ↓
BookRepository (여러 권을 리스트로 보관)
   ↓
useBean으로 Repository 가져오기
   ↓
getAllBooks() + for + getter로 화면 출력
```
