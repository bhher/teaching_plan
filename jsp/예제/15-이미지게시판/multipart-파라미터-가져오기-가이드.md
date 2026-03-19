# multipart/form-data 파라미터 가져오기 가이드

## 🐛 오류: Column 'title' cannot be null

### 문제 원인

**오류 메시지:**
```
java.sql.SQLIntegrityConstraintViolationException: Column 'title' cannot be null
```

**원인:**
- `enctype="multipart/form-data"`를 사용하면 `request.getParameter()`가 작동하지 않음
- 일반 파라미터도 `null`로 전달됨

---

## ❌ 문제 코드

```jsp
<form enctype="multipart/form-data">
    <input type="text" name="title">
    <input type="file" name="image">
</form>

<%
    // ❌ multipart/form-data에서는 작동하지 않음!
    String title = request.getParameter("title");  // null 반환
%>
```

---

## ✅ 해결 방법

### 방법 1: Part API로 일반 파라미터 가져오기 (권장)

```jsp
<%
    String title = null;
    String content = null;
    String writer = null;
    String password = null;
    
    // 모든 Part를 순회하면서 파라미터 추출
    for (Part part : request.getParts()) {
        String name = part.getName();
        
        if (name != null && part.getHeader("content-disposition") != null) {
            String contentDisposition = part.getHeader("content-disposition");
            
            // 파일이 아닌 일반 파라미터인 경우
            if (!contentDisposition.contains("filename")) {
                // Part의 내용을 읽어서 파라미터 값으로 사용
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(part.getInputStream(), "UTF-8"));
                StringBuilder value = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    value.append(line);
                }
                
                // 파라미터 이름에 따라 값 저장
                if ("title".equals(name)) {
                    title = value.toString();
                } else if ("content".equals(name)) {
                    content = value.toString();
                } else if ("writer".equals(name)) {
                    writer = value.toString();
                } else if ("password".equals(name)) {
                    password = value.toString();
                }
            }
        }
    }
%>
```

### 방법 2: Apache Commons FileUpload 사용 (복잡함)

- 별도 라이브러리 필요
- 권장하지 않음

---

## 🔍 상세 설명

### multipart/form-data의 특징

**일반 폼 (application/x-www-form-urlencoded):**
```
POST /board_write_process.jsp HTTP/1.1
Content-Type: application/x-www-form-urlencoded

title=제목&writer=작성자&content=내용
```

**multipart/form-data:**
```
POST /board_write_process.jsp HTTP/1.1
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary...

------WebKitFormBoundary...
Content-Disposition: form-data; name="title"

제목
------WebKitFormBoundary...
Content-Disposition: form-data; name="writer"

작성자
------WebKitFormBoundary...
Content-Disposition: form-data; name="image"; filename="photo.jpg"
Content-Type: image/jpeg

[바이너리 데이터]
------WebKitFormBoundary...--
```

**차이점:**
- 일반 폼: `request.getParameter()` 사용 가능
- multipart: Part API 사용 필요

---

## 📝 완전한 수정 코드

### 파라미터 추출 함수 (재사용 가능)

```jsp
<%
    /**
     * multipart/form-data에서 일반 파라미터 가져오기
     */
    String getParameterValue(Part part) throws Exception {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (value.length() > 0) {
                value.append("\n");
            }
            value.append(line);
        }
        return value.toString();
    }
    
    // 파라미터 추출
    String title = null;
    String content = null;
    String writer = null;
    String password = null;
    
    for (Part part : request.getParts()) {
        String name = part.getName();
        if (name != null) {
            String contentDisposition = part.getHeader("content-disposition");
            if (contentDisposition != null && !contentDisposition.contains("filename")) {
                String value = getParameterValue(part);
                
                if ("title".equals(name)) {
                    title = value;
                } else if ("content".equals(name)) {
                    content = value;
                } else if ("writer".equals(name)) {
                    writer = value;
                } else if ("password".equals(name)) {
                    password = value;
                }
            }
        }
    }
%>
```

---

## 🎯 간단한 해결 방법 (추천)

### 더 간단한 방법: Apache Commons FileUpload 사용하지 않고 직접 처리

```jsp
<%
    // 파라미터 저장용 Map
    java.util.Map<String, String> params = new java.util.HashMap<>();
    
    // 모든 Part 순회
    for (Part part : request.getParts()) {
        String name = part.getName();
        if (name != null) {
            String contentDisposition = part.getHeader("content-disposition");
            
            // 파일이 아닌 경우 (일반 파라미터)
            if (contentDisposition == null || !contentDisposition.contains("filename")) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(part.getInputStream(), "UTF-8"))) {
                    StringBuilder value = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (value.length() > 0) {
                            value.append("\n");
                        }
                        value.append(line);
                    }
                    params.put(name, value.toString());
                }
            }
        }
    }
    
    // 파라미터 사용
    String title = params.get("title");
    String content = params.get("content");
    String writer = params.get("writer");
    String password = params.get("password");
%>
```

---

## 🔄 비교: 일반 폼 vs multipart 폼

### 일반 폼 (application/x-www-form-urlencoded)

```jsp
<form method="post">
    <input type="text" name="title">
</form>

<%
    // ✅ 작동함
    String title = request.getParameter("title");
%>
```

### multipart 폼 (multipart/form-data)

```jsp
<form method="post" enctype="multipart/form-data">
    <input type="text" name="title">
    <input type="file" name="image">
</form>

<%
    // ❌ 작동하지 않음
    String title = request.getParameter("title");  // null
    
    // ✅ Part API 사용 필요
    for (Part part : request.getParts()) {
        if ("title".equals(part.getName())) {
            // Part에서 값 읽기
        }
    }
%>
```

---

## 🐛 자주 발생하는 오류

### 오류 1: Column 'title' cannot be null

**원인:**
- `request.getParameter("title")`이 `null` 반환

**해결:**
- Part API로 파라미터 가져오기

### 오류 2: 한글 깨짐

**원인:**
- 인코딩 설정 누락

**해결:**
```java
new InputStreamReader(part.getInputStream(), "UTF-8")
```

### 오류 3: 여러 줄 텍스트 처리

**원인:**
- 줄바꿈 문자 처리 누락

**해결:**
```java
while ((line = reader.readLine()) != null) {
    if (value.length() > 0) {
        value.append("\n");  // 줄바꿈 추가
    }
    value.append(line);
}
```

---

## ✅ 체크리스트

수정 확인:

- [ ] `request.getParameter()` 대신 Part API 사용
- [ ] 일반 파라미터를 Part에서 추출
- [ ] 파일과 일반 파라미터 구분 (`filename` 체크)
- [ ] UTF-8 인코딩 설정
- [ ] 여러 줄 텍스트 처리 (줄바꿈)

---

## 📚 요약

### 문제

- `multipart/form-data`에서는 `request.getParameter()`가 작동하지 않음
- 일반 파라미터도 `null`로 전달됨

### 해결

1. ✅ Part API로 모든 Part 순회
2. ✅ 파일이 아닌 Part에서 파라미터 값 읽기
3. ✅ BufferedReader로 InputStream 읽기
4. ✅ UTF-8 인코딩 설정

**Part API를 사용하여 일반 파라미터를 가져오면 해결됩니다!** ✅
