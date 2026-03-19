# 이미지 게시판 예제

이미지 업로드 기능이 포함된 게시판 예제입니다.

## 📋 목차

1. [개요](#개요)
2. [주요 기능](#주요-기능)
3. [파일 구조](#파일-구조)
4. [설치 및 설정](#설치-및-설정)
5. [사용 방법](#사용-방법)
6. [주요 코드 설명](#주요-코드-설명)

---

## 개요

이 예제는 JSP와 Servlet 3.0+ Part API를 사용하여 이미지 파일을 업로드하고 관리하는 게시판입니다.

### 기술 스택
- **JSP**: 동적 웹 페이지 생성
- **Java Servlet 3.0+**: 파일 업로드 처리
- **MySQL**: 데이터베이스
- **JDBC**: 데이터베이스 연결

---

## 주요 기능

### 1. 게시글 작성
- 제목, 작성자, 비밀번호, 내용 입력
- 이미지 파일 업로드 (JPG, PNG, GIF, BMP, WEBP)
- 이미지 미리보기 기능
- 파일 크기 제한 (최대 10MB)

### 2. 게시글 목록
- 그리드 레이아웃으로 이미지 썸네일 표시
- 페이징 기능
- 검색 기능 (제목, 내용, 작성자)
- 이미지가 없는 게시글은 기본 아이콘 표시

### 3. 게시글 상세보기
- 게시글 내용 및 이미지 표시
- 조회수 자동 증가
- 수정/삭제 링크

### 4. 게시글 수정
- 제목, 내용 수정
- 이미지 교체 (새 이미지 업로드 시 기존 이미지 삭제)
- 비밀번호 확인

### 5. 게시글 삭제
- 비밀번호 확인 후 삭제
- 삭제 시 이미지 파일도 함께 삭제

---

## 파일 구조

```
15-이미지게시판/
├── db_setup.sql              # 데이터베이스 및 테이블 생성 스크립트
├── DBConnection.java          # 데이터베이스 연결 유틸리티
├── ImageBoardDTO.java         # 게시글 데이터 전송 객체
├── ImageBoardDAO.java         # 데이터베이스 접근 객체
├── FileUploadUtil.java        # 파일 업로드 유틸리티
├── board_list.jsp            # 게시글 목록
├── board_write.jsp           # 게시글 작성 폼
├── board_write_process.jsp   # 게시글 작성 처리
├── board_view.jsp            # 게시글 상세보기
├── board_modify.jsp          # 게시글 수정 폼
├── board_modify_process.jsp  # 게시글 수정 처리
├── board_delete.jsp          # 게시글 삭제 확인
├── board_delete_process.jsp  # 게시글 삭제 처리
└── README.md                 # 이 파일
```

---

## 설치 및 설정

### 1. 데이터베이스 설정

```sql
-- db_setup.sql 파일 실행
mysql -u root -p < db_setup.sql
```

또는 MySQL 클라이언트에서 직접 실행:
```sql
CREATE DATABASE IF NOT EXISTS jsp_image_board DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jsp_image_board;

CREATE TABLE IF NOT EXISTS image_board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(50) NOT NULL,
    password VARCHAR(255),
    image_file VARCHAR(255),
    image_original VARCHAR(255),
    hit INT DEFAULT 0,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    mod_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_writer (writer),
    INDEX idx_reg_date (reg_date),
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 2. DBConnection.java 설정

데이터베이스 연결 정보를 수정하세요:

```java
private static final String URL = "jdbc:mysql://localhost:3306/jsp_image_board?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
private static final String USER = "root";
private static final String PASSWORD = "1234";  // 실제 비밀번호로 변경
```

### 3. 웹 애플리케이션 설정

#### web.xml 설정 (Servlet 3.0+)

`web.xml`에 다음 설정을 추가하거나, `@MultipartConfig` 어노테이션을 사용하세요:

```xml
<servlet>
    <servlet-name>FileUploadServlet</servlet-name>
    <servlet-class>jsp.imageboard.FileUploadUtil</servlet-class>
    <multipart-config>
        <max-file-size>10485760</max-file-size>  <!-- 10MB -->
        <max-request-size>10485760</max-request-size>
    </multipart-config>
</servlet>
```

또는 JSP 파일에 직접 설정:

```jsp
<%@ page import="javax.servlet.annotation.MultipartConfig" %>
```

### 4. 업로드 디렉토리 생성

웹 애플리케이션 루트에 `uploads/images/` 디렉토리를 생성하세요:

```
웹앱루트/
└── uploads/
    └── images/
```

---

## 사용 방법

### 1. 게시글 작성

1. `board_list.jsp`에서 "글쓰기" 버튼 클릭
2. 제목, 작성자, 비밀번호 입력
3. 이미지 파일 선택 (선택사항)
4. 내용 입력
5. "등록" 버튼 클릭

### 2. 게시글 목록 보기

- `board_list.jsp`에서 모든 게시글을 그리드 형태로 확인
- 검색 기능으로 원하는 게시글 찾기
- 페이징으로 여러 페이지 탐색

### 3. 게시글 상세보기

- 목록에서 게시글 클릭
- 이미지와 내용 확인
- 수정/삭제 버튼으로 관리

### 4. 게시글 수정

1. 상세보기 페이지에서 "수정" 버튼 클릭
2. 비밀번호 입력
3. 제목, 내용 수정
4. 새 이미지 선택 (선택사항)
5. "수정" 버튼 클릭

### 5. 게시글 삭제

1. 상세보기 페이지에서 "삭제" 버튼 클릭
2. 비밀번호 입력
3. "삭제" 버튼 클릭

---

## 주요 코드 설명

### 1. 파일 업로드 처리 (board_write_process.jsp)

```jsp
<%@ page import="javax.servlet.http.Part" %>
<%
    // Servlet 3.0+ Part API 사용
    Part imagePart = request.getPart("image");
    
    if (imagePart != null && imagePart.getSize() > 0) {
        String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
        String imageFile = FileUploadUtil.uploadFile(imagePart, uploadPath);
        // ...
    }
%>
```

**설명:**
- `request.getPart("image")`: 폼의 `name="image"` 필드에서 파일 가져오기
- `application.getRealPath("/")`: 웹 애플리케이션의 실제 경로
- `FileUploadUtil.uploadFile()`: 파일을 저장하고 고유한 파일명 생성

### 2. FileUploadUtil.java

```java
public static String uploadFile(Part part, String uploadPath) {
    // 1. 파일명 추출
    String originalFileName = getFileName(part);
    
    // 2. 확장자 확인
    String extension = getFileExtension(originalFileName);
    if (!isAllowedExtension(extension)) {
        throw new IllegalArgumentException("허용되지 않은 파일 형식");
    }
    
    // 3. 파일 크기 확인
    if (part.getSize() > MAX_FILE_SIZE) {
        throw new IllegalArgumentException("파일 크기 초과");
    }
    
    // 4. 고유한 파일명 생성
    String savedFileName = generateUniqueFileName(originalFileName);
    
    // 5. 파일 저장
    // ...
    
    return savedFileName;
}
```

**주요 기능:**
- 파일명 추출 및 검증
- 확장자 확인 (이미지 파일만 허용)
- 파일 크기 제한 (10MB)
- 고유한 파일명 생성 (타임스탬프 + UUID)
- 파일 저장

### 3. 이미지 표시 (board_list.jsp)

```jsp
<% if (board.hasImage()) { %>
    <img src="<%= uploadPath %><%= board.getImageFile() %>" 
         alt="<%= board.getTitle() %>" 
         class="board-image"
         onerror="this.style.display='none';">
<% } else { %>
    <div class="no-image">📷</div>
<% } %>
```

**설명:**
- `board.hasImage()`: 이미지 파일 존재 여부 확인
- `onerror`: 이미지 로드 실패 시 처리
- 이미지가 없으면 기본 아이콘 표시

### 4. 이미지 삭제 (board_delete_process.jsp)

```jsp
<%
    // 이미지 파일 삭제
    if (board.hasImage()) {
        String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
        FileUploadUtil.deleteFile(uploadPath, board.getImageFile());
    }
    
    // 데이터베이스에서 삭제
    boardDAO.deleteBoard(id);
%>
```

**설명:**
- 게시글 삭제 시 이미지 파일도 함께 삭제
- 데이터베이스 레코드 삭제 전에 파일 삭제

---

## 보안 고려사항

### 1. 파일 업로드 보안

- **확장자 검증**: 허용된 이미지 확장자만 업로드 가능
- **파일 크기 제한**: 최대 10MB로 제한
- **고유 파일명**: 타임스탬프 + UUID로 중복 방지
- **경로 검증**: 상대 경로 공격 방지

### 2. 비밀번호 보안

- 현재는 평문 저장 (실제 프로젝트에서는 해시화 권장)
- 수정/삭제 시 비밀번호 확인

### 3. SQL Injection 방지

- PreparedStatement 사용으로 SQL Injection 방지

---

## 문제 해결

### 1. 파일 업로드가 안 될 때

**원인:**
- `enctype="multipart/form-data"` 누락
- 업로드 디렉토리 권한 문제
- 파일 크기 제한 초과

**해결:**
- 폼에 `enctype="multipart/form-data"` 추가 확인
- 업로드 디렉토리 쓰기 권한 확인
- `web.xml`의 `max-file-size` 설정 확인

### 2. 이미지가 표시되지 않을 때

**원인:**
- 업로드 경로 오류
- 파일명 인코딩 문제
- 웹 서버 설정 문제

**해결:**
- `application.getRealPath("/")` 경로 확인
- 파일명에 특수문자 포함 시 URL 인코딩
- 웹 서버에서 `uploads` 디렉토리 접근 허용 확인

### 3. 한글 파일명 깨짐

**원인:**
- 파일명 인코딩 문제

**해결:**
- `FileUploadUtil.java`에서 파일명을 UTF-8로 처리
- 또는 고유 파일명 사용 (현재 구현)

---

## 확장 기능 제안

1. **다중 이미지 업로드**: 여러 이미지 동시 업로드
2. **이미지 리사이징**: 썸네일 자동 생성
3. **이미지 편집**: 크롭, 회전 기능
4. **댓글 기능**: 게시글에 댓글 추가
5. **좋아요 기능**: 게시글 추천 기능
6. **카테고리 분류**: 게시글 카테고리별 분류

---

## 라이선스

이 예제는 교육 목적으로 제공됩니다.
