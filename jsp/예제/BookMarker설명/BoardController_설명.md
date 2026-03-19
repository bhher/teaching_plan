# BoardController 설명

`BoardController`는 **게시판 기능을 담당하는 서블릿**으로, MVC 패턴에서 **Controller** 역할을 합니다.

---

## 1. 기본 구조

```java
public class BoardController extends HttpServlet
```

- `HttpServlet`을 상속하여 웹 요청을 처리합니다.
- `web.xml`에서 `*.do` URL 패턴으로 매핑됩니다.

---

## 2. 요청 처리 흐름

### 2.1 doGet / doPost

```java
public void doGet(...) {
    doPost(request, response);  // GET 요청도 POST로 통합 처리
}
```

- GET과 POST를 모두 `doPost`에서 처리합니다.

### 2.2 command 분기

```java
String RequestURI = request.getRequestURI();      // 예: /BookMarkert/BoardListAction.do
String contextPath = request.getContextPath();    // 예: /BookMarkert
String command = RequestURI.substring(contextPath.length());  // /BoardListAction.do
```

- URI에서 `command`를 추출하여 기능별로 분기합니다.

---

## 3. URL별 동작

| URL | 메서드 | 동작 |
|-----|--------|------|
| `/BoardListAction.do` | `requestBoardList()` | 게시글 목록 조회 후 `board/list.jsp`로 forward |
| `/BoardWriteForm.do` | `requestLoginName()` | 글쓰기 폼용 작성자명 조회 후 `board/writeForm.jsp`로 forward |
| `/BoardWriteAction.do` | `requestBoardWrite()` | 새 글 DB 저장 후 목록으로 redirect |
| `/BoardViewAction.do` | `requestBoardView()` | 글 상세 조회 후 `/BoardView.do`로 forward |
| `/BoardView.do` | (없음) | `board/view.jsp`로 forward |
| `/BoardUpdateAction.do` | `requestBoardUpdate()` | 글 수정 후 목록으로 redirect |
| `/BoardDeleteAction.do` | `requestBoardDelete()` | 글 삭제 후 목록으로 redirect |

---

## 4. 주요 메서드 설명

### 4.1 requestBoardList – 목록 조회

```java
int pageNum = 1;           // 기본 1페이지
int limit = 5;             // 페이지당 5개
String items, text;        // 검색 조건
```

- `BoardDAO.getListCount()`: 전체 글 개수 조회
- `BoardDAO.getBoardList()`: 해당 페이지 글 목록 조회
- `total_page` 계산 후 `pageNum`, `total_page`, `total_record`, `boardlist`를 request에 저장
- `board/list.jsp`에서 이 값들을 사용

### 4.2 requestLoginName – 글쓰기 폼용 작성자명

- 파라미터 `id`로 `BoardDAO.getLoginNameById(id)` 호출
- `member` 테이블에서 이름 조회 후 `name`으로 request에 저장
- `writeForm.jsp`에서 작성자명 표시용

### 4.3 requestBoardWrite – 글 등록

- `id`, `name`, `subject`, `content` 파라미터 수집
- `regist_day`, `hit`, `ip` 설정
- `BoardDAO.insertBoard()`로 DB INSERT
- 이후 `/BoardListAction.do`로 forward

### 4.4 requestBoardView – 글 상세 조회

- `num`, `pageNum` 파라미터로 글 번호와 페이지 번호 전달
- `BoardDAO.getBoardByNum()`로 글 조회 (조회수 증가 포함)
- `num`, `page`, `board`를 request에 저장
- `/BoardView.do` → `board/view.jsp`에서 상세 표시

### 4.5 requestBoardUpdate – 글 수정

- `num`, `pageNum`과 수정된 `name`, `subject`, `content` 수집
- `BoardDAO.updateBoard()`로 DB UPDATE
- 이후 목록으로 forward

### 4.6 requestBoardDelete – 글 삭제

- `num`으로 삭제할 글 지정
- `BoardDAO.deleteBoard(num)`로 DB DELETE
- 이후 목록으로 forward

---

## 5. MVC 흐름

```
[요청] *.do
    ↓
BoardController (Controller)
    ↓
BoardDAO (Model) → DB
    ↓
request.setAttribute(...)
    ↓
RequestDispatcher.forward() → JSP (View)
```

- **Controller**: URL 분기, 파라미터 처리, DAO 호출
- **Model**: BoardDAO, BoardDTO
- **View**: `board/list.jsp`, `view.jsp`, `writeForm.jsp`

---

## 6. 참고 사항

1. **total_page 계산**  
   `Math.floor(total_page)`는 반환값을 사용하지 않습니다. `total_page`는 이미 정수 나눗셈으로 계산되므로, 의도한 동작이라면 그대로 두어도 됩니다.

2. **BoardViewAction → BoardView**  
   상세 조회는 두 단계로 나뉩니다.
   - `BoardViewAction.do`: DB 조회 후 `board`를 request에 저장
   - `BoardView.do`: `view.jsp`로 forward

3. **인코딩**  
   `request.setCharacterEncoding("utf-8")`로 한글 파라미터를 처리합니다.

4. **pageNum**  
   수정/삭제 후 목록으로 돌아갈 때 `pageNum`을 유지하려면, forward 경로에 `pageNum` 쿼리 파라미터를 추가하는 방식으로 개선할 수 있습니다.
