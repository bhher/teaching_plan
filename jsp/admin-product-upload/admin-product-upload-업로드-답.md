# admin-product-upload — 이미지 업로드 정답

문제: [admin-product-upload-업로드-문제.md](./admin-product-upload-업로드-문제.md)

---

## 객관식

| 번호 | 정답 | 해설 |
|------|------|------|
| Q1 | **②** | 파일 전송용 enctype |
| Q2 | **②** | COS `MultipartRequest` |
| Q3 | **②** | 파라미터 이름 일치 |
| Q4 | **②** | 파일명만 DB 저장 |
| Q5 | **②** | `getRealPath("/uploads")` |
| Q6 | **②** | multipart 이후에는 `multi`로 읽음 |
| Q7 | **②** | `getFilesystemName` |
| Q8 | **①** | DefaultFileRenamePolicy |
| Q9 | **②** | adminCheck / 세션 |
| Q10 | **②** | 5MB |

---

## 단답

### Q11
```text
productWriteProcess.jsp
insert
```

### Q12
`uploads`

### Q13
`cos.jar` (또는 cos 계열 JAR)

### Q14
`file` → `productWrite.jsp?error=file`

---

## 코드 고치기

### Q15
1. `method="post"`  
2. `enctype="multipart/form-data"`  
3. `name="photo"` → `name="imageFile"` (Process와 동일)

### Q16
multipart 폼에서는 **MultipartRequest 생성 후** `multi.getParameter`로 읽어야 함.  
생성 **전** `request.getParameter`는 값이 안 나오거나 비는 경우가 많음.

올바른 순서:

```jsp
MultipartRequest multi = new MultipartRequest(...);
String name = multi.getParameter("name");
String file = multi.getFilesystemName("imageFile");
```

---

## 서술

### Q17
파일을 보내려면 본문이 `multipart/form-data`로 인코딩되어야 함.  
일반 폼 파싱용 `request.getParameter`만으로는 파일 파트를 처리하기 어렵고,  
COS `MultipartRequest` 등이 파트를 나눠 폴더에 저장하고 텍스트 필드도 읽어 줌.

### Q18 (예시)
DB 부담을 줄이고, 웹 서버가 정적 파일로 빠르게 제공하기 쉽기 때문.  
(또는 백업·교체가 파일 단위로 쉬움)

---

## 실습 가이드

### 실습 1
모두 O이면 업로드 파이프라인 정상.

### 실습 2
서버 검증 시 `error=file`, 경고 메시지 「이미지 파일을 선택하세요.」  
(브라우저 `required`만 있으면 제출 자체가 막힐 수 있음)

### 실습 3
주석 위치 예: (1) savePath (2) MultipartRequest (3) getParameter/getFilesystemName (4) DTO (5) insert (6) redirect

### 실습 4
`5 * 1024 * 1024` → `2 * 1024 * 1024`  
초과 시 COS/서블릿 예외 또는 업로드 실패 — 콘솔·에러 페이지 확인.

### 실습 5 (예시)

```text
fileName을 소문자로
endsWith(".jpg") 또는 ".jpeg" 또는 ".png" 아니면 error로 redirect
```
