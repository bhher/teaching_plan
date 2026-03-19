# FileUploadUtil.java 상세 설명

## 📋 개요

`FileUploadUtil`은 JSP 이미지 게시판에서 파일 업로드를 처리하는 유틸리티 클래스입니다. Servlet 3.0+의 `Part` API를 사용하여 안전하고 효율적인 파일 업로드 기능을 제공합니다.

---

## 🏗️ 클래스 구조

```java
package jsp.imageboard;

public class FileUploadUtil {
    // 상수 정의
    // 메서드들
}
```

**특징:**
- 모든 메서드가 `static`으로 선언되어 있어 인스턴스 생성 없이 사용 가능
- 유틸리티 클래스 패턴 사용

---

## 📌 상수 정의

### 1. UPLOAD_DIR

```java
private static final String UPLOAD_DIR = "uploads/images";
```

**설명:**
- 업로드된 파일이 저장될 디렉토리 경로
- 웹 애플리케이션 루트 기준 상대 경로
- 예: `webapps/your-app/uploads/images/`

**주의사항:**
- 실제 물리적 경로는 JSP에서 `application.getRealPath()`로 변환 필요
- 디렉토리가 없으면 자동 생성됨

### 2. ALLOWED_EXTENSIONS

```java
private static final String[] ALLOWED_EXTENSIONS = {
    ".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp"
};
```

**설명:**
- 허용된 이미지 파일 확장자 목록
- 보안을 위해 특정 파일 형식만 허용
- 대소문자 구분 없이 비교

**보안 고려사항:**
- 확장자만으로는 완전한 보안이 불가능
- 실제 파일 내용 검증(MIME 타입)도 권장됨

### 3. MAX_FILE_SIZE

```java
private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
```

**설명:**
- 최대 파일 크기 제한 (10MB)
- 서버 리소스 보호 및 DoS 공격 방지
- 바이트 단위로 계산: `10 * 1024 * 1024 = 10,485,760 bytes`

---

## 🔧 주요 메서드

### 1. uploadFile() - 파일 업로드 처리

```java
public static String uploadFile(Part part, String uploadPath)
```

**기능:**
- `Part` 객체로부터 파일을 추출하여 서버에 저장
- 파일 검증 (확장자, 크기)
- 고유한 파일명 생성

**파라미터:**
- `part`: Servlet 3.0+의 `Part` 객체 (multipart/form-data에서 추출)
- `uploadPath`: 실제 업로드 경로 (절대 경로)

**반환값:**
- 성공: 저장된 파일명 (예: `20240310123456_a1b2c3d4.jpg`)
- 실패: `null`

**처리 흐름:**

```
1. Part 객체 유효성 검사
   ↓
2. 원본 파일명 추출
   ↓
3. 확장자 확인 및 검증
   ↓
4. 파일 크기 검증
   ↓
5. 업로드 디렉토리 생성 (없는 경우)
   ↓
6. 고유한 파일명 생성
   ↓
7. 파일 저장 (스트림 복사)
   ↓
8. 저장된 파일명 반환
```

**코드 분석:**

```java
// 1. Part 객체 및 크기 확인
if (part == null || part.getSize() == 0) {
    return null;
}

// 2. 원본 파일명 추출
String originalFileName = getFileName(part);
if (originalFileName == null || originalFileName.isEmpty()) {
    return null;
}

// 3. 확장자 검증
String extension = getFileExtension(originalFileName);
if (!isAllowedExtension(extension)) {
    throw new IllegalArgumentException("허용되지 않은 파일 형식입니다: " + extension);
}

// 4. 파일 크기 검증
if (part.getSize() > MAX_FILE_SIZE) {
    throw new IllegalArgumentException("파일 크기가 너무 큽니다. (최대 10MB)");
}

// 5. 디렉토리 생성
File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) {
    uploadDir.mkdirs(); // 하위 디렉토리까지 생성
}

// 6. 고유 파일명 생성 및 저장
String savedFileName = generateUniqueFileName(originalFileName);
String filePath = uploadPath + File.separator + savedFileName;

// 7. 파일 저장 (try-with-resources 사용)
try (InputStream input = part.getInputStream();
     OutputStream output = new FileOutputStream(filePath)) {
    
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = input.read(buffer)) != -1) {
        output.write(buffer, 0, bytesRead);
    }
}

return savedFileName;
```

**주요 포인트:**
- `try-with-resources`: 자동 리소스 관리 (Java 7+)
- 버퍼 크기 1024바이트: 효율적인 I/O
- `File.separator`: OS 독립적인 경로 구분자

---

### 2. getFileName() - 파일명 추출

```java
private static String getFileName(Part part)
```

**기능:**
- `Part` 객체의 `content-disposition` 헤더에서 파일명 추출
- 브라우저별 경로 형식 차이 처리

**content-disposition 헤더 예시:**
```
content-disposition: form-data; name="image"; filename="photo.jpg"
```

**코드 분석:**

```java
String contentDisposition = part.getHeader("content-disposition");
if (contentDisposition == null) {
    return null;
}

String[] tokens = contentDisposition.split(";");
for (String token : tokens) {
    if (token.trim().startsWith("filename")) {
        // filename="photo.jpg" 또는 filename=photo.jpg
        String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
        // Windows 경로 처리: C:\Users\...\photo.jpg → photo.jpg
        return fileName.substring(fileName.lastIndexOf("\\") + 1);
    }
}
```

**처리 과정:**

1. `content-disposition` 헤더 가져오기
2. 세미콜론(`;`)으로 분리
3. `filename`으로 시작하는 토큰 찾기
4. `=` 뒤의 값 추출 (따옴표 제거)
5. 경로에서 파일명만 추출 (백슬래시 처리)

**예시:**
- 입력: `filename="C:\Users\user\photo.jpg"`
- 출력: `photo.jpg`

---

### 3. getFileExtension() - 확장자 추출

```java
private static String getFileExtension(String fileName)
```

**기능:**
- 파일명에서 확장자 추출
- 소문자로 변환하여 반환

**코드 분석:**

```java
int lastDot = fileName.lastIndexOf(".");
if (lastDot == -1) {
    return ""; // 확장자가 없는 경우
}
return fileName.substring(lastDot).toLowerCase();
```

**예시:**
- `photo.jpg` → `.jpg`
- `image.PNG` → `.png`
- `file` → `` (빈 문자열)

**주의사항:**
- 마지막 점(`.`) 기준으로 확장자 추출
- 여러 점이 있어도 마지막 점 이후만 확장자로 인식

---

### 4. isAllowedExtension() - 확장자 검증

```java
private static boolean isAllowedExtension(String extension)
```

**기능:**
- 주어진 확장자가 허용 목록에 있는지 확인
- 대소문자 구분 없이 비교

**코드 분석:**

```java
for (String allowed : ALLOWED_EXTENSIONS) {
    if (allowed.equalsIgnoreCase(extension)) {
        return true;
    }
}
return false;
```

**예시:**
- `.jpg` → `true`
- `.JPG` → `true` (대소문자 무시)
- `.exe` → `false`
- `.txt` → `false`

---

### 5. generateUniqueFileName() - 고유 파일명 생성

```java
private static String generateUniqueFileName(String originalFileName)
```

**기능:**
- 중복을 방지하기 위한 고유한 파일명 생성
- 타임스탬프 + UUID 조합 사용

**코드 분석:**

```java
String extension = getFileExtension(originalFileName);
String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
String uuid = UUID.randomUUID().toString().substring(0, 8);
return timestamp + "_" + uuid + extension;
```

**생성 형식:**
```
{타임스탬프}_{UUID8자리}{확장자}
```

**예시:**
- 원본: `photo.jpg`
- 생성: `20240310123456_a1b2c3d4.jpg`

**장점:**
- 타임스탬프: 시간 순서 정렬 가능
- UUID: 충돌 가능성 극히 낮음
- 원본 파일명과 분리: 보안 강화

**타임스탬프 형식:**
- `yyyyMMddHHmmss`: 년월일시분초
- 예: `20240310123456` = 2024년 3월 10일 12시 34분 56초

---

### 6. deleteFile() - 파일 삭제

```java
public static boolean deleteFile(String uploadPath, String fileName)
```

**기능:**
- 업로드된 파일 삭제
- 게시글 삭제 시 연관 파일도 함께 삭제할 때 사용

**코드 분석:**

```java
if (fileName == null || fileName.isEmpty()) {
    return false;
}

try {
    File file = new File(uploadPath + File.separator + fileName);
    if (file.exists()) {
        return file.delete();
    }
    return false;
} catch (Exception e) {
    e.printStackTrace();
    return false;
}
```

**반환값:**
- `true`: 삭제 성공
- `false`: 삭제 실패 (파일 없음, 권한 없음 등)

**사용 예시:**
```java
// 게시글 삭제 시
String imageFile = board.getImageFile();
if (imageFile != null) {
    FileUploadUtil.deleteFile(uploadPath, imageFile);
}
```

---

### 7. getUploadDir() - 업로드 디렉토리 경로 가져오기

```java
public static String getUploadDir()
```

**기능:**
- 상대 경로인 `UPLOAD_DIR` 상수값 반환
- 다른 클래스에서 업로드 디렉토리 경로를 참조할 때 사용

**사용 예시:**
```java
String relativePath = FileUploadUtil.getUploadDir();
String realPath = application.getRealPath(relativePath);
```

---

## 🔄 전체 처리 흐름

### 파일 업로드 시나리오

```
1. 사용자가 JSP 폼에서 파일 선택
   <input type="file" name="image">
   ↓
2. 폼 제출 (multipart/form-data)
   ↓
3. JSP에서 Part 객체 추출
   Part part = request.getPart("image");
   ↓
4. FileUploadUtil.uploadFile() 호출
   String savedFileName = FileUploadUtil.uploadFile(part, realPath);
   ↓
5. 파일 검증
   - 확장자 확인
   - 크기 확인
   ↓
6. 고유 파일명 생성
   "20240310123456_a1b2c3d4.jpg"
   ↓
7. 파일 저장
   uploads/images/20240310123456_a1b2c3d4.jpg
   ↓
8. 파일명 반환
   ↓
9. 데이터베이스에 파일명 저장
   INSERT INTO image_board (image_file, ...) VALUES ('20240310123456_a1b2c3d4.jpg', ...)
```

---

## 🛡️ 보안 고려사항

### 1. 파일 확장자 검증

**현재 구현:**
```java
if (!isAllowedExtension(extension)) {
    throw new IllegalArgumentException("허용되지 않은 파일 형식입니다: " + extension);
}
```

**추가 권장사항:**
- MIME 타입 검증 추가
- 파일 시그니처(매직 넘버) 검증
- 실행 가능한 파일 확장자 차단

### 2. 파일 크기 제한

**현재 구현:**
```java
if (part.getSize() > MAX_FILE_SIZE) {
    throw new IllegalArgumentException("파일 크기가 너무 큽니다. (최대 10MB)");
}
```

**추가 권장사항:**
- `web.xml`에서도 크기 제한 설정
- 클라이언트 사이드에서도 사전 검증

### 3. 파일명 보안

**현재 구현:**
- 원본 파일명을 사용하지 않고 고유 파일명 생성
- 경로 조작 공격 방지

**추가 권장사항:**
- 파일명에 특수문자 제거
- 디렉토리 순회 공격 방지 (`../` 제거)

### 4. 업로드 디렉토리 보안

**권장사항:**
- 웹 루트 외부에 저장
- 직접 접근 불가능한 위치에 저장
- 또는 `.htaccess`로 접근 제한

---

## 📝 사용 예시

### JSP에서 사용하기

```jsp
<%@ page import="javax.servlet.http.Part" %>
<%@ page import="jsp.imageboard.FileUploadUtil" %>

<%
    request.setCharacterEncoding("UTF-8");
    
    // 실제 업로드 경로 가져오기
    String uploadDir = FileUploadUtil.getUploadDir();
    String realPath = application.getRealPath(uploadDir);
    
    // Part 객체 가져오기
    Part part = request.getPart("image");
    
    // 파일 업로드
    String savedFileName = FileUploadUtil.uploadFile(part, realPath);
    
    if (savedFileName != null) {
        // 데이터베이스에 저장
        // ...
    } else {
        // 업로드 실패 처리
        // ...
    }
%>
```

### 파일 삭제 예시

```jsp
<%
    // 게시글 삭제 시
    String imageFile = board.getImageFile();
    if (imageFile != null && !imageFile.isEmpty()) {
        String uploadDir = FileUploadUtil.getUploadDir();
        String realPath = application.getRealPath(uploadDir);
        FileUploadUtil.deleteFile(realPath, imageFile);
    }
%>
```

---

## 🐛 에러 처리

### 예외 상황

1. **Part가 null이거나 크기가 0인 경우**
   - 반환: `null`
   - 처리: 파일이 선택되지 않았거나 빈 파일

2. **허용되지 않은 확장자**
   - 예외: `IllegalArgumentException`
   - 메시지: "허용되지 않은 파일 형식입니다: {확장자}"

3. **파일 크기 초과**
   - 예외: `IllegalArgumentException`
   - 메시지: "파일 크기가 너무 큽니다. (최대 10MB)"

4. **파일 저장 실패**
   - 반환: `null`
   - 원인: 디스크 공간 부족, 권한 없음 등

---

## 🔍 주요 개념

### 1. Servlet 3.0 Part API

**특징:**
- `multipart/form-data` 요청을 쉽게 처리
- `@MultipartConfig` 어노테이션 또는 `web.xml` 설정 필요

**web.xml 설정 예시:**
```xml
<servlet>
    <servlet-name>UploadServlet</servlet-name>
    <servlet-class>...</servlet-class>
    <multipart-config>
        <max-file-size>10485760</max-file-size> <!-- 10MB -->
        <max-request-size>10485760</max-request-size>
    </multipart-config>
</servlet>
```

### 2. Try-with-Resources

```java
try (InputStream input = part.getInputStream();
     OutputStream output = new FileOutputStream(filePath)) {
    // 자동으로 close() 호출
}
```

**장점:**
- 자동 리소스 관리
- 예외 발생 시에도 안전하게 닫힘
- 코드 간결성

### 3. UUID (Universally Unique Identifier)

```java
UUID.randomUUID().toString().substring(0, 8)
```

**특징:**
- 전역적으로 고유한 식별자 생성
- 충돌 가능성 극히 낮음
- 8자리만 사용하여 파일명 길이 제한

---

## 📚 학습 포인트

### 1. 파일 I/O

- `InputStream`: 데이터 읽기
- `OutputStream`: 데이터 쓰기
- 버퍼를 사용한 효율적인 복사

### 2. 파일 시스템

- `File` 클래스: 파일/디렉토리 조작
- `mkdirs()`: 디렉토리 생성
- `exists()`: 존재 여부 확인

### 3. 문자열 처리

- `substring()`: 부분 문자열 추출
- `lastIndexOf()`: 마지막 위치 찾기
- `split()`: 문자열 분리

### 4. 날짜/시간 처리

- `SimpleDateFormat`: 날짜 포맷팅
- `Date`: 현재 시간 가져오기

---

## 🚀 개선 제안

### 1. MIME 타입 검증 추가

```java
private static boolean isValidImage(Part part) {
    String contentType = part.getContentType();
    return contentType != null && contentType.startsWith("image/");
}
```

### 2. 파일 시그니처 검증

```java
private static boolean isValidImageFile(File file) {
    try (FileInputStream fis = new FileInputStream(file)) {
        byte[] header = new byte[4];
        fis.read(header);
        // JPEG: FF D8 FF E0
        // PNG: 89 50 4E 47
        // ...
    }
}
```

### 3. 이미지 리사이징

```java
// 이미지 크기 조정 (예: 썸네일 생성)
BufferedImage originalImage = ImageIO.read(file);
// 리사이징 로직...
```

### 4. 비동기 업로드

- AJAX를 사용한 파일 업로드 진행률 표시
- 여러 파일 동시 업로드

---

## 요약

### 핵심 기능

1. ✅ 파일 업로드 처리 (`uploadFile`)
2. ✅ 파일명 추출 (`getFileName`)
3. ✅ 확장자 검증 (`isAllowedExtension`)
4. ✅ 고유 파일명 생성 (`generateUniqueFileName`)
5. ✅ 파일 삭제 (`deleteFile`)

### 보안 기능

1. ✅ 확장자 검증
2. ✅ 파일 크기 제한
3. ✅ 고유 파일명 생성 (경로 조작 방지)

### 주요 특징

- Servlet 3.0+ Part API 사용
- Try-with-resources로 안전한 리소스 관리
- 타임스탬프 + UUID로 고유 파일명 생성
- 예외 처리 및 에러 핸들링
