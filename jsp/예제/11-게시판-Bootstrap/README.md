# 📚 JSP 게시판 시스템 (Bootstrap 적용 버전)

## 📋 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [데이터베이스 설정](#데이터베이스-설정)
3. [프로젝트 구조](#프로젝트-구조)
4. [Bootstrap 적용](#bootstrap-적용)
5. [코드 구현](#코드-구현)
6. [실행 순서](#실행-순서)

---

## 🎯 프로젝트 개요

JSP를 사용한 게시판 시스템에 Bootstrap을 적용하여 현대적이고 반응형 디자인으로 구현했습니다.

### 주요 기능
- ✅ 게시글 작성 (Create)
- ✅ 게시글 목록 조회 (Read)
- ✅ 게시글 상세 보기 (Read)
- ✅ 게시글 수정 (Update)
- ✅ 게시글 삭제 (Delete)
- ✅ Bootstrap 5를 활용한 반응형 UI
- ✅ 아이콘 지원 (Bootstrap Icons)

---

## 🗄️ 데이터베이스 설정

### 1️⃣ DB 테이블 생성

```sql
CREATE DATABASE IF NOT EXISTS testdb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE testdb;

CREATE TABLE board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    writer VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 📁 프로젝트 구조

```
JSPBoard
 ├─ src
 │   ├─ dao
 │   │    └─ BoardDAO.java
 │   ├─ dto
 │   │    └─ BoardDTO.java
 │   └─ util
 │        └─ DBUtil.java
 │
 ├─ webapp
 │   ├─ boardList.jsp
 │   ├─ boardWrite.jsp
 │   ├─ boardWriteProcess.jsp
 │   ├─ boardView.jsp
 │   ├─ boardDelete.jsp
 │   ├─ boardEdit.jsp
 │   └─ boardUpdate.jsp
```

**참고**: Java 클래스(DTO, DAO, DBUtil)는 기존 10-게시판-완성 예제와 동일합니다.

---

## 🎨 Bootstrap 적용

### CDN 링크

모든 JSP 파일의 `<head>` 섹션에 다음을 추가합니다:

```html
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
```

### JavaScript

`</body>` 태그 앞에 추가:

```html
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
```

---

## 💻 코드 구현

### ✅ 1. 게시글 목록 (Bootstrap 적용)

**📌 boardList.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO,java.util.*" %>

<%
    BoardDAO dao = new BoardDAO();
    List<BoardDTO> list = dao.selectAll();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    
    <style>
        body {
            background-color: #f8f9fa;
            padding: 20px 0;
        }
        .board-container {
            max-width: 1200px;
            margin: 0 auto;
        }
        .board-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 10px 10px 0 0;
            margin-bottom: 0;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f3f5;
        }
    </style>
</head>
<body>
    <div class="container board-container">
        <div class="board-header">
            <h2 class="mb-0">
                <i class="bi bi-clipboard-check me-2"></i>
                게시판
            </h2>
        </div>
        
        <div class="card shadow-sm">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <div>
                        <span class="badge bg-primary">전체 <span id="totalCount"><%=list.size()%></span>개</span>
                    </div>
                    <a href="boardWrite.jsp" class="btn btn-primary">
                        <i class="bi bi-pencil-square me-2"></i>글쓰기
                    </a>
                </div>
                
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-light">
                            <tr>
                                <th style="width: 80px;">번호</th>
                                <th>제목</th>
                                <th style="width: 120px;">작성자</th>
                                <th style="width: 180px;">작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if(list.isEmpty()) { %>
                            <tr>
                                <td colspan="4" class="text-center py-5">
                                    <i class="bi bi-inbox fs-1 text-muted d-block mb-3"></i>
                                    <p class="text-muted">등록된 게시글이 없습니다.</p>
                                </td>
                            </tr>
                            <% } else { %>
                                <% for(BoardDTO dto : list) { %>
                                <tr>
                                    <td><%=dto.getId()%></td>
                                    <td>
                                        <a href="boardView.jsp?id=<%=dto.getId()%>" class="text-decoration-none">
                                            <%=dto.getTitle()%>
                                        </a>
                                    </td>
                                    <td>
                                        <i class="bi bi-person-circle me-1"></i>
                                        <%=dto.getWriter()%>
                                    </td>
                                    <td>
                                        <i class="bi bi-calendar3 me-1"></i>
                                        <%=dto.getCreated_at()%>
                                    </td>
                                </tr>
                                <% } %>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

### ✅ 2. 글쓰기 페이지 (Bootstrap 적용)

**📌 boardWrite.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    
    <style>
        body {
            background-color: #f8f9fa;
            padding: 40px 0;
        }
        .write-container {
            max-width: 800px;
            margin: 0 auto;
        }
        .form-label {
            font-weight: 600;
            color: #495057;
        }
    </style>
</head>
<body>
    <div class="container write-container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">
                    <i class="bi bi-pencil-square me-2"></i>
                    글쓰기
                </h4>
            </div>
            <div class="card-body">
                <form action="boardWriteProcess.jsp" method="post">
                    <div class="mb-3">
                        <label for="title" class="form-label">
                            <i class="bi bi-card-heading me-1"></i>제목
                        </label>
                        <input type="text" class="form-control" id="title" name="title" required placeholder="제목을 입력하세요">
                    </div>
                    
                    <div class="mb-3">
                        <label for="content" class="form-label">
                            <i class="bi bi-file-text me-1"></i>내용
                        </label>
                        <textarea class="form-control" id="content" name="content" rows="10" required placeholder="내용을 입력하세요"></textarea>
                    </div>
                    
                    <div class="mb-3">
                        <label for="writer" class="form-label">
                            <i class="bi bi-person me-1"></i>작성자
                        </label>
                        <input type="text" class="form-control" id="writer" name="writer" required placeholder="작성자 이름을 입력하세요">
                    </div>
                    
                    <div class="d-flex justify-content-end gap-2">
                        <a href="boardList.jsp" class="btn btn-secondary">
                            <i class="bi bi-x-circle me-1"></i>취소
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-check-circle me-1"></i>등록
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

**📌 boardWriteProcess.jsp** (기존과 동일)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    request.setCharacterEncoding("UTF-8");

    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String writer = request.getParameter("writer");

    BoardDTO dto = new BoardDTO();

    dto.setTitle(title);
    dto.setContent(content);
    dto.setWriter(writer);

    BoardDAO dao = new BoardDAO();

    dao.insert(dto);

    response.sendRedirect("boardList.jsp");
%>
```

---

### ✅ 3. 게시글 상세 보기 (Bootstrap 적용)

**📌 boardView.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    BoardDAO dao = new BoardDAO();

    BoardDTO dto = dao.selectOne(id);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 보기</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    
    <style>
        body {
            background-color: #f8f9fa;
            padding: 40px 0;
        }
        .view-container {
            max-width: 900px;
            margin: 0 auto;
        }
        .content-box {
            min-height: 300px;
            white-space: pre-wrap;
            word-break: break-word;
        }
    </style>
</head>
<body>
    <div class="container view-container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">
                    <i class="bi bi-eye me-2"></i>
                    게시글 보기
                </h4>
            </div>
            <div class="card-body">
                <div class="mb-4">
                    <h3 class="mb-3"><%=dto.getTitle()%></h3>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <small class="text-muted">
                                <i class="bi bi-person-circle me-1"></i>
                                작성자: <strong><%=dto.getWriter()%></strong>
                            </small>
                        </div>
                        <div class="col-md-6 text-md-end">
                            <small class="text-muted">
                                <i class="bi bi-calendar3 me-1"></i>
                                작성일: <%=dto.getCreated_at()%>
                            </small>
                        </div>
                    </div>
                    
                    <hr>
                    
                    <div class="content-box p-3 bg-light rounded">
                        <%=dto.getContent()%>
                    </div>
                </div>
                
                <div class="d-flex justify-content-end gap-2">
                    <a href="boardList.jsp" class="btn btn-outline-secondary">
                        <i class="bi bi-list me-1"></i>목록
                    </a>
                    <a href="boardEdit.jsp?id=<%=dto.getId()%>" class="btn btn-outline-primary">
                        <i class="bi bi-pencil me-1"></i>수정
                    </a>
                    <a href="boardDelete.jsp?id=<%=dto.getId()%>" class="btn btn-outline-danger" onclick="return confirm('정말 삭제하시겠습니까?')">
                        <i class="bi bi-trash me-1"></i>삭제
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

### ✅ 4. 게시글 삭제 (Bootstrap 적용)

**📌 boardDelete.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    BoardDAO dao = new BoardDAO();

    dao.delete(id);

    response.sendRedirect("boardList.jsp");
%>
```

---

### ✅ 5. 게시글 수정 (Bootstrap 적용)

**📌 boardEdit.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    BoardDAO dao = new BoardDAO();

    BoardDTO dto = dao.selectOne(id);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    
    <style>
        body {
            background-color: #f8f9fa;
            padding: 40px 0;
        }
        .edit-container {
            max-width: 800px;
            margin: 0 auto;
        }
        .form-label {
            font-weight: 600;
            color: #495057;
        }
    </style>
</head>
<body>
    <div class="container edit-container">
        <div class="card shadow">
            <div class="card-header bg-warning text-dark">
                <h4 class="mb-0">
                    <i class="bi bi-pencil-square me-2"></i>
                    게시글 수정
                </h4>
            </div>
            <div class="card-body">
                <form action="boardUpdate.jsp" method="post">
                    <input type="hidden" name="id" value="<%=dto.getId()%>">
                    
                    <div class="mb-3">
                        <label for="title" class="form-label">
                            <i class="bi bi-card-heading me-1"></i>제목
                        </label>
                        <input type="text" class="form-control" id="title" name="title" value="<%=dto.getTitle()%>" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="content" class="form-label">
                            <i class="bi bi-file-text me-1"></i>내용
                        </label>
                        <textarea class="form-control" id="content" name="content" rows="10" required><%=dto.getContent()%></textarea>
                    </div>
                    
                    <div class="alert alert-info">
                        <i class="bi bi-info-circle me-1"></i>
                        작성자: <strong><%=dto.getWriter()%></strong> | 작성일: <%=dto.getCreated_at()%>
                    </div>
                    
                    <div class="d-flex justify-content-end gap-2">
                        <a href="boardView.jsp?id=<%=dto.getId()%>" class="btn btn-secondary">
                            <i class="bi bi-x-circle me-1"></i>취소
                        </a>
                        <button type="submit" class="btn btn-warning">
                            <i class="bi bi-check-circle me-1"></i>수정
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

**📌 boardUpdate.jsp** (기존과 동일)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("id"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardDTO dto = new BoardDTO();

    dto.setId(id);
    dto.setTitle(title);
    dto.setContent(content);

    BoardDAO dao = new BoardDAO();

    dao.update(dto);

    response.sendRedirect("boardList.jsp");
%>
```

---

## 🚀 실행 순서

### 1. 데이터베이스 설정
```sql
CREATE DATABASE IF NOT EXISTS testdb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE testdb;

CREATE TABLE board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    writer VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2. 프로젝트 설정
- MySQL JDBC 드라이버를 프로젝트에 추가
- `DBUtil.java`의 연결 정보 수정 (기존 예제와 동일)

### 3. 실행 흐름
1. **게시글 목록**: `boardList.jsp` - Bootstrap 스타일의 테이블로 표시
2. **글쓰기**: `boardWrite.jsp` → `boardWriteProcess.jsp` → `boardList.jsp`
3. **상세 보기**: `boardView.jsp` - 카드 형태로 표시
4. **수정**: `boardEdit.jsp` → `boardUpdate.jsp` → `boardList.jsp`
5. **삭제**: `boardDelete.jsp` → `boardList.jsp` (확인 메시지 포함)

---

## 🎨 Bootstrap 주요 특징

### 1. 반응형 디자인
- 모바일, 태블릿, 데스크톱에서 자동으로 레이아웃 조정
- `container` 클래스로 최대 너비 제한

### 2. 컴포넌트 활용
- **Card**: 게시글 상세 보기, 글쓰기 폼
- **Table**: 게시글 목록
- **Button**: 액션 버튼들
- **Badge**: 게시글 개수 표시
- **Alert**: 정보 표시

### 3. 아이콘
- Bootstrap Icons 사용
- 각 기능에 적절한 아이콘 추가

### 4. 색상 테마
- Primary: 주요 버튼, 헤더
- Secondary: 취소 버튼
- Warning: 수정 페이지
- Danger: 삭제 버튼

---

## 📝 주요 개선 사항

### 1. UI/UX 개선
- ✅ 현대적인 카드 디자인
- ✅ 호버 효과 (테이블 행)
- ✅ 아이콘으로 시각적 구분
- ✅ 반응형 레이아웃

### 2. 사용자 경험
- ✅ 삭제 확인 메시지 (JavaScript `confirm()`)
- ✅ 빈 게시글 목록 안내 메시지
- ✅ 버튼 그룹으로 액션 정리
- ✅ 플레이스홀더 텍스트

### 3. 접근성
- ✅ 시맨틱 HTML 구조
- ✅ 적절한 색상 대비
- ✅ 아이콘과 텍스트 함께 표시

---

## 💡 추가 개선 가능 사항

### 1. 모달 활용
- 삭제 확인을 모달로 구현
- 게시글 작성/수정을 모달로 구현

### 2. 알림 메시지
- 성공/실패 메시지를 Toast나 Alert로 표시

### 3. 검색 기능
- Bootstrap Input Group으로 검색 바 추가

### 4. 페이징
- Bootstrap Pagination 컴포넌트 사용

---

**이 예제는 Bootstrap을 적용하여 현대적이고 아름다운 게시판 시스템입니다! 💪**
