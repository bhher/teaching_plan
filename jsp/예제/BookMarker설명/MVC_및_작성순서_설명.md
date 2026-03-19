# MVC 패턴과 게시판 작성 순서·동작 설명

교안이나 발표 시 사용할 수 있는 **설명 구조**와 **작성 순서**를 정리했습니다.

---

## 1. MVC란? (설명 포인트)

### 1.1 한 줄 정의
> **MVC**는 Model(데이터·비즈니스 로직), View(화면), Controller(요청 분기·제어)를 나누어 개발하는 패턴입니다.

### 1.2 왜 MVC를 쓰나?
- **역할 분리**: 화면(JSP)과 로직(Java) 분리 → 유지보수 용이
- **재사용**: Model은 다른 화면에서도 사용 가능
- **협업**: 화면 담당과 로직 담당이 나누어 작업 가능

### 1.3 이 프로젝트에서의 MVC 매핑

| 구분 | 역할 | 이 프로젝트 |
|------|------|-------------|
| **Model** | 데이터 구조, DB 접근 | BoardDTO, BoardDAO, DBConnection |
| **View** | 화면 출력 | list.jsp, view.jsp, writeForm.jsp |
| **Controller** | URL 분기, Model 호출, View 선택 | BoardController (서블릿) |

---

## 2. mvc 폴더 구조 설명

```
mvc/
├── controller/     ← 요청을 받아 분기하고, Model 호출 후 View로 전달
│   └── BoardController.java
├── model/         ← 데이터와 DB 접근
│   ├── BoardDAO.java    (Data Access Object)
│   └── BoardDTO.java    (Data Transfer Object)
└── database/      ← DB 연결 공통 처리
    └── DBConnection.java
```

### 2.1 model 폴더의 두 가지 역할

| 파일 | 역할 | 비유 |
|------|------|------|
| **BoardDTO** | 게시글 한 건의 데이터 구조 | "편지 봉투" – num, subject, content 등 필드 |
| **BoardDAO** | DB에 접근하는 메서드 모음 | "우체국" – insert, select, update, delete |

### 2.2 DTO vs DAO (설명용 비교)

```
DTO (Data Transfer Object)
- board 테이블의 한 행 = BoardDTO 객체 하나
- getter/setter만 있는 "데이터 담는 그릇"
- DB 컬럼과 1:1 매핑

DAO (Data Access Object)  
- DB CRUD를 수행하는 메서드
- getBoardList(), insertBoard(), updateBoard() 등
- ResultSet → BoardDTO로 변환하여 반환
```

---

## 3. 작성 순서 (개발 흐름)

### 3.1 권장 순서 (아래에서 위로)

```
1단계: DB 테이블 생성 (board)
       ↓
2단계: BoardDTO 작성 (테이블 컬럼과 맞춤)
       ↓
3단계: DBConnection 작성 (연결 테스트)
       ↓
4단계: BoardDAO 작성 (CRUD 메서드)
       ↓
5단계: BoardController 작성 (URL 분기, DAO 호출)
       ↓
6단계: web.xml에 서블릿 매핑 (*.do)
       ↓
7단계: JSP 작성 (list → view → writeForm)
```

### 3.2 왜 이 순서인가?

| 순서 | 이유 |
|------|------|
| DTO 먼저 | DAO가 반환/전달할 데이터 타입이 필요 |
| DAO 먼저 | Controller가 호출할 메서드가 필요 |
| Controller 먼저 | JSP가 어떤 데이터를 request에서 받는지 결정 |
| list.jsp 먼저 | 목록이 기본 화면, 다른 화면의 진입점 |

---

## 4. JSP 작성 순서와 역할

### 4.1 권장 순서: list → view → writeForm

| 순서 | JSP | 이유 |
|------|-----|------|
| 1 | **list.jsp** | 목록이 메인 화면, 상세/글쓰기로 가는 출발점 |
| 2 | **view.jsp** | 목록에서 클릭 시 이동, 수정/삭제 링크 포함 |
| 3 | **writeForm.jsp** | 글쓰기 폼, 등록 후 list로 돌아감 |

### 4.2 각 JSP가 받는 데이터 (request.setAttribute)

| JSP | Controller에서 설정하는 데이터 |
|-----|------------------------------|
| list.jsp | boardlist, pageNum, total_page, total_record |
| view.jsp | board, num, page |
| writeForm.jsp | name (작성자명) |

---

## 5. 동작 흐름 (설명용 다이어그램)

### 5.1 목록 조회

```
[사용자] BoardListAction.do?pageNum=1 클릭
    ↓
[Controller] command == "/BoardListAction.do"
    ↓
[Controller] requestBoardList(request)
    ↓
[DAO] getListCount() → 전체 글 수
[DAO] getBoardList() → 해당 페이지 글 목록 (ArrayList<BoardDTO>)
    ↓
[Controller] request.setAttribute("boardlist", boardlist)
[Controller] request.setAttribute("pageNum", pageNum)
    ↓
[Controller] forward("./board/list.jsp")
    ↓
[View] list.jsp에서 ${boardlist} 등으로 출력
```

### 5.2 글쓰기

```
[사용자] writeForm.jsp에서 폼 작성 후 제출
    ↓
[Controller] BoardWriteAction.do
    ↓
[Controller] requestBoardWrite(request)
    ↓
[Controller] BoardDTO에 파라미터 담기
[DAO] insertBoard(board) → DB INSERT
    ↓
[Controller] forward("/BoardListAction.do") → 목록으로
```

### 5.3 상세 보기 (2단계)

```
[사용자] list.jsp에서 제목 클릭 → BoardViewAction.do?num=5&pageNum=1
    ↓
[Controller] requestBoardView() → DAO.getBoardByNum()
    ↓
[Controller] request.setAttribute("board", board)
[Controller] forward("/BoardView.do")
    ↓
[Controller] BoardView.do → forward("./board/view.jsp")
    ↓
[View] view.jsp에서 ${board.subject} 등 출력
```

---

## 6. 설명할 때 강조할 점

### 6.1 Model (DTO + DAO)
- **DTO**: DB 한 행 = 객체 하나, 화면과 무관
- **DAO**: DB 작업만 담당, 화면·URL 모름
- **싱글톤**: BoardDAO.getInstance()로 하나의 인스턴스만 사용

### 6.2 Controller
- **command 분기**: URI에서 `/BoardListAction.do` 등 추출
- **forward vs redirect**: forward는 request 유지, redirect는 새 요청
- **setAttribute**: Model 결과를 request에 담아 View로 전달

### 6.3 View (JSP)
- **Controller가 준 데이터만 사용**: request.getAttribute(), EL `${}`
- **직접 DB 접근 안 함**: JSP는 출력만 담당

---

## 7. 요약 (발표/교안용)

1. **MVC** = Model(데이터·DB) + View(화면) + Controller(분기·제어)
2. **model 폴더** = DTO(데이터 구조) + DAO(DB 접근)
3. **작성 순서** = DB → DTO → DAO → Controller → JSP
4. **JSP 순서** = list(목록) → view(상세) → writeForm(등록)
5. **동작** = 요청 → Controller 분기 → DAO 호출 → request에 저장 → JSP forward → 화면 출력
