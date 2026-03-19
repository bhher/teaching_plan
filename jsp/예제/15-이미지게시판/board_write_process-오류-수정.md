# board_write_process.jsp 오류 수정

## 🐛 문제: 저장은 되지만 성공 후 처리가 없음

### 발견된 문제

**현재 코드:**
```jsp
int boardId = boardDAO.insertBoard(board);
// ❌ 성공 시 리다이렉트가 없음!
```

**문제점:**
- 데이터베이스에는 저장됨
- 하지만 성공 후 페이지 이동이 없음
- 사용자가 결과를 확인할 수 없음

---

## ✅ 해결 방법

### 성공 시 리다이렉트 추가

**수정 전:**
```jsp
int boardId = boardDAO.insertBoard(board);
// 성공 처리가 없음
```

**수정 후:**
```jsp
int boardId = boardDAO.insertBoard(board);

// ✅ 성공 시 리다이렉트 추가!
if (boardId > 0) {
    response.sendRedirect("board_view.jsp?id=" + boardId);
    return;  // 리다이렉트 후 실행 중단
} else {
    // 실패 처리
    if (imageFile != null) {
        String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
        FileUploadUtil.deleteFile(uploadPath, imageFile);
    }
    out.println("<script>alert('게시글 등록에 실패했습니다.'); history.back();</script>");
}
```

---

## 🔍 전체 수정된 코드

### 핵심 부분

```jsp
<%
    // ... 파일 업로드 처리 ...
    
    // 데이터베이스에 저장
    Connection conn = null;
    
    try {
        conn = DBConnection.getConnection();
        ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
        
        ImageBoardDTO board = new ImageBoardDTO(title, content, writer, password, imageFile, imageOriginal);
        int boardId = boardDAO.insertBoard(board);
        
        // ✅ 성공 시 리다이렉트 추가!
        if (boardId > 0) {
            response.sendRedirect("board_view.jsp?id=" + boardId);
            return;  // 중요: 리다이렉트 후 실행 중단
        } else {
            // 실패 시 업로드된 파일 삭제
            if (imageFile != null) {
                String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
                FileUploadUtil.deleteFile(uploadPath, imageFile);
            }
            out.println("<script>alert('게시글 등록에 실패했습니다.'); history.back();</script>");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        // 실패 시 업로드된 파일 삭제
        if (imageFile != null) {
            String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
            FileUploadUtil.deleteFile(uploadPath, imageFile);
        }
        out.println("<script>alert('게시글 등록 중 오류가 발생했습니다.'); history.back();</script>");
    } finally {
        DBConnection.close(conn);
    }
%>
```

---

## 📋 주요 변경 사항

### 1. 성공 시 리다이렉트 추가

```jsp
if (boardId > 0) {
    response.sendRedirect("board_view.jsp?id=" + boardId);
    return;  // ✅ 중요: 리다이렉트 후 실행 중단
}
```

**설명:**
- `boardId > 0`: 저장 성공 (새로운 게시글 ID 반환)
- `response.sendRedirect()`: 상세보기 페이지로 이동
- `return`: 리다이렉트 후 코드 실행 중단 (중요!)

### 2. 실패 시 파일 삭제 추가

```jsp
else {
    // 실패 시 업로드된 파일 삭제
    if (imageFile != null) {
        String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
        FileUploadUtil.deleteFile(uploadPath, imageFile);
    }
    out.println("<script>alert('게시글 등록에 실패했습니다.'); history.back();</script>");
}
```

**설명:**
- DB 저장 실패 시 업로드된 파일도 삭제
- 불필요한 파일이 서버에 남지 않도록 처리

### 3. return 문 추가

**중요:**
```jsp
response.sendRedirect("board_view.jsp?id=" + boardId);
return;  // ✅ 필수!
```

**이유:**
- 리다이렉트 후에도 코드가 계속 실행됨
- `return`으로 실행 중단 필요
- 없으면 리다이렉트 후에도 HTML이 출력될 수 있음

---

## 🔄 실행 흐름

### 정상적인 경우

```
1. 사용자가 폼 제출
   ↓
2. 파일 업로드 처리
   ↓
3. 데이터베이스에 저장
   ↓
4. boardId > 0 (성공)
   ↓
5. response.sendRedirect() 실행
   ↓
6. board_view.jsp로 이동 ✅
   ↓
7. 게시글 상세보기 표시
```

### 실패한 경우

```
1. 사용자가 폼 제출
   ↓
2. 파일 업로드 처리
   ↓
3. 데이터베이스에 저장 시도
   ↓
4. boardId = 0 또는 예외 발생 (실패)
   ↓
5. 업로드된 파일 삭제
   ↓
6. alert 표시 및 이전 페이지로 이동
```

---

## 🐛 자주 발생하는 오류

### 오류 1: 리다이렉트 후에도 HTML 출력

**원인:**
- `return` 문이 없음

**해결:**
```jsp
response.sendRedirect("board_view.jsp?id=" + boardId);
return;  // ✅ 추가
```

### 오류 2: "response already committed" 오류

**원인:**
- 리다이렉트 전에 이미 출력이 시작됨

**해결:**
- JSP 상단에만 스크립틀릿 사용
- HTML 출력 전에 리다이렉트 완료

### 오류 3: 파일이 업로드되지만 DB에 저장 안 됨

**원인:**
- DB 연결 오류
- SQL 오류

**해결:**
- 예외 처리 확인
- 업로드된 파일 삭제 로직 추가

---

## ✅ 체크리스트

수정 확인:

- [ ] `boardId > 0` 체크 추가
- [ ] `response.sendRedirect()` 추가
- [ ] `return` 문 추가 (리다이렉트 후)
- [ ] 실패 시 파일 삭제 로직 추가
- [ ] 예외 처리 확인

---

## 📚 요약

### 문제

- 데이터베이스에는 저장되지만 성공 후 처리가 없음
- 사용자가 결과를 확인할 수 없음

### 해결

1. ✅ 성공 시 리다이렉트 추가: `response.sendRedirect("board_view.jsp?id=" + boardId);`
2. ✅ `return` 문 추가: 리다이렉트 후 실행 중단
3. ✅ 실패 시 파일 삭제: DB 저장 실패 시 업로드 파일도 삭제

**성공 시 리다이렉트를 추가하면 저장 후 상세보기 페이지로 이동합니다!** ✅
