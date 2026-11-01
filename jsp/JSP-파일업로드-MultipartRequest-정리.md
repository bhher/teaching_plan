# JSP 파일 업로드 — MultipartRequest (COS) 정리

JSP에서 **파일 업로드**를 처리하면서 **일반 파라미터**와 **파일 정보**를 확인하는 테스트 코드 설명입니다.  
(COS `com.oreilly.servlet` · BookMarket 등 교재 예제와 동일 계열)

---

## 전체 흐름

```text
HTML form (enctype="multipart/form-data")
   ↓
MultipartRequest
   ↓
┌─────────────────────────────┐
│ 일반 데이터                  │
│ name, author, price ...     │
└─────────────────────────────┘
   ↓
getParameterNames()
getParameter()

┌─────────────────────────────┐
│ 파일 데이터                  │
│ image.jpg                   │
└─────────────────────────────┘
   ↓
getFileNames()
getOriginalFileName()
getFilesystemName()
getContentType()
getFile()
```

---

## 1. MultipartRequest 생성

```java
MultipartRequest multi = new MultipartRequest(
    request,
    "C:\\upload",
    5 * 1024 * 1024,
    "utf-8",
    new DefaultFileRenamePolicy()
);
```

이 한 줄이 파일 업로드의 **핵심**입니다.

| 값 | 의미 |
|----|------|
| `request` | 현재 브라우저 요청 |
| `"C:\\upload"` | 업로드 파일 저장 위치 |
| `5 * 1024 * 1024` | 최대 크기 **5MB** |
| `"utf-8"` | 인코딩 |
| `DefaultFileRenamePolicy()` | 같은 이름 파일이 있으면 자동 이름 변경 |

```text
브라우저 → request → MultipartRequest → C:\upload
```

> HTML form에는 반드시  
> `enctype="multipart/form-data"` · `method="post"` 가 필요합니다.

---

## 2. 일반 파라미터 가져오기

```java
Enumeration params = multi.getParameterNames();
```

`getParameterNames()`는 **파일이 아닌** 일반 입력값의 이름을 가져옵니다.

예) HTML:

```html
<input type="text" name="name">
<input type="text" name="author">
<input type="text" name="price">
```

```text
params
 ├─ name
 ├─ author
 └─ price
```

---

## 3. while문으로 하나씩 가져오기

```java
while (params.hasMoreElements()) {
    String name = (String) params.nextElement();
    String value = multi.getParameter(name);
    out.println(name + " = " + value + "<br>");
}
```

| 코드 | 의미 |
|------|------|
| `hasMoreElements()` | 아직 꺼낼 파라미터가 있으면 반복 |
| `nextElement()` | 다음 파라미터 **이름** (예: `name`) |
| `multi.getParameter(name)` | 그 이름의 **값** (예: `홍길동`) |

출력 예:

```text
name = 홍길동
author = 우재남
price = 27000
```

---

## 4. 구분선

```java
out.println("-----------------------------------<br>");
```

여기까지 **일반 파라미터**, 아래부터 **파일 정보**임을 구분합니다.

---

## 5. 파일 파라미터 가져오기

```java
Enumeration files = multi.getFileNames();
```

| 메서드 | 대상 |
|--------|------|
| `multi.getParameterNames()` | 일반 입력값 |
| `multi.getFileNames()` | 업로드 파일의 **파라미터 이름** |

예) HTML:

```html
<input type="file" name="filename">
```

```text
files → filename
```

---

## 6. 파일 파라미터 이름

```java
String name = (String) files.nextElement();
```

여기서 `name`은 **실제 이미지 파일명이 아닙니다.**  
HTML의 `name="filename"` 같은 **요청 파라미터 이름**입니다.

---

## 7. 원본 파일 이름

```java
String original = multi.getOriginalFileName(name);
```

사용자가 선택한 원래 이름. 예: `book.jpg`

---

## 8. 서버에 저장된 파일 이름

```java
String filename = multi.getFilesystemName(name);
```

`DefaultFileRenamePolicy()` 때문에 중복 시 이름이 바뀔 수 있습니다.

```text
원본: book.jpg
저장: book.jpg  또는  book1.jpg, book2.jpg ...
```

그래서 둘을 구분합니다.

| 메서드 | 의미 |
|--------|------|
| `getOriginalFileName()` | 사용자가 고른 이름 |
| `getFilesystemName()` | 서버에 실제로 저장된 이름 |

---

## 9. Content-Type

```java
String type = multi.getContentType(name);
```

예: `image/jpeg`, `image/png`, `image/gif`

---

## 10. File 객체

```java
File file = multi.getFile(name);
```

예: `C:\upload\book.jpg` 를 가리키는 `java.io.File`

---

## 11. null 체크

```java
if (file != null) {
    // 실제 업로드된 파일이 있을 때만
}
```

파일이 없으면 `null`일 수 있으므로 먼저 확인합니다.

---

## 12. 파일 크기

```java
out.println("파일 크기 : " + file.length());  // byte
```

MB로 보기:

```java
double size = file.length() / (1024.0 * 1024.0);
out.println("파일 크기 : " + size + " MB<br>");
```

---

## 전체 코드

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%
MultipartRequest multi = new MultipartRequest(
    request,
    "C:\\upload",
    5 * 1024 * 1024,
    "utf-8",
    new DefaultFileRenamePolicy()
);

Enumeration params = multi.getParameterNames();

while (params.hasMoreElements()) {
    String name = (String) params.nextElement();
    String value = multi.getParameter(name);
    out.println(name + " = " + value + "<br>");
}
out.println("-----------------------------------<br>");

Enumeration files = multi.getFileNames();

while (files.hasMoreElements()) {
    String name = (String) files.nextElement();
    String filename = multi.getFilesystemName(name);
    String original = multi.getOriginalFileName(name);
    String type = multi.getContentType(name);
    File file = multi.getFile(name);

    out.println("요청 파라미터 이름 : " + name + "<br>");
    out.println("실제 파일 이름 : " + original + "<br>");
    out.println("저장 파일 이름 : " + filename + "<br>");
    out.println("파일 콘텐츠 유형 : " + type + "<br>");

    if (file != null) {
        out.println(" 파일 크기 : " + file.length());
        out.println("<br>");
    }
}
%>
```

---

## 구조로 다시 보기

```text
① MultipartRequest 생성
        ↓
② getParameterNames()     ← 일반
        ↓
③ nextElement() → 이름
        ↓
④ getParameter(name) → 값
        ↓
⑤ getFileNames()           ← 파일
        ↓
⑥ getOriginalFileName()    원본 이름
⑦ getFilesystemName()      저장 이름
⑧ getContentType()         MIME 타입
⑨ getFile()                File 객체
```

---

## 학생용 핵심 4개 (파일)

```java
multi.getOriginalFileName(name); // 원본 이름
multi.getFilesystemName(name);   // 실제 저장 이름
multi.getContentType(name);      // 파일 종류
multi.getFile(name);             // File 객체
```

일반 데이터:

```java
multi.getParameter("name");      // 예: 홍길동
```

파일:

```java
multi.getFilesystemName("filename"); // 예: book.jpg (또는 book1.jpg)
```

---

## 한 줄 요약

> **`getParameter()`** 는 일반 입력값,  
> **`getFile…()` 계열** 은 업로드된 파일 정보를 가져온다.

---

## BookMarket 연결 (다음 단계)

상품 등록에서는 보통 이렇게 이어집니다.

| 데이터 | 받는 방법 | 예 |
|--------|-----------|-----|
| bookId, name, unitPrice … | `multi.getParameter(...)` | 텍스트 필드 |
| bookImage (표지) | `multi.getFilesystemName("bookImage")` | 파일 |

받은 값을 `Book` 객체에 `set…()` 한 뒤 Repository/DB에 저장합니다.

```text
form (multipart)
  → MultipartRequest
  → getParameter / getFilesystemName
  → Book 객체
  → 목록·상세 화면에 이미지 경로로 사용
```

---

## 실습 체크리스트

- [ ] form에 `enctype="multipart/form-data"`  
- [ ] `C:\upload` 폴더 존재 · Tomcat 쓰기 권한  
- [ ] `cos.jar`(또는 해당 라이브러리) 프로젝트에 추가  
- [ ] 일반/파일 Enumeration을 **따로** 순회  
- [ ] 원본 이름 ≠ 저장 이름일 수 있음을 확인  
- [ ] `getFile() != null` 후 `length()`  

---

## 관련 자료

- `jsp/예제/15-이미지게시판/` — 이미지 게시판·업로드 실습  
- `jsp/예제/15-이미지게시판/cos2.jar-필요성-설명.md`
