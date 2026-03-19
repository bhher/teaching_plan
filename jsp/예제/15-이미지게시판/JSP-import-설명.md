# JSP import 문법 설명

## 📋 `<%@ page import="jsp.imageboard.*" %>` 설명

### 답변: **JSP에서 Java 패키지의 모든 클래스를 가져오는 문법입니다.**

---

## 🔍 상세 설명

### 1. 기본 구조

```jsp
<%@ page import="패키지명.*" %>
```

**의미:**
- `jsp.imageboard` 패키지에 있는 **모든 클래스**를 import
- `*`는 와일드카드 (모든 클래스)

### 2. 현재 프로젝트에서의 의미

```jsp
<%@ page import="jsp.imageboard.*" %>
```

**가져오는 클래스들:**
- `FileUploadUtil`
- `ImageBoardDAO`
- `ImageBoardDTO`
- `DBConnection`
- 기타 `jsp.imageboard` 패키지의 모든 클래스

---

## 📦 패키지 구조

### Java 파일들의 패키지 선언

**FileUploadUtil.java:**
```java
package jsp.imageboard;  // 패키지 선언

public class FileUploadUtil {
    // ...
}
```

**ImageBoardDAO.java:**
```java
package jsp.imageboard;  // 같은 패키지

public class ImageBoardDAO {
    // ...
}
```

**ImageBoardDTO.java:**
```java
package jsp.imageboard;  // 같은 패키지

public class ImageBoardDTO {
    // ...
}
```

**DBConnection.java:**
```java
package jsp.imageboard;  // 같은 패키지

public class DBConnection {
    // ...
}
```

### 디렉토리 구조

```
src/
  └── jsp/
      └── imageboard/
          ├── FileUploadUtil.java
          ├── ImageBoardDAO.java
          ├── ImageBoardDTO.java
          └── DBConnection.java
```

---

## 💻 사용 예시

### import 없이 사용 (전체 패키지명 필요)

```jsp
<%
    jsp.imageboard.DBConnection conn = jsp.imageboard.DBConnection.getConnection();
    jsp.imageboard.ImageBoardDAO dao = new jsp.imageboard.ImageBoardDAO(conn);
    jsp.imageboard.FileUploadUtil.uploadFile(...);
%>
```

**문제점:**
- 코드가 길고 복잡함
- 가독성 저하

### import 사용 (간단하게 사용 가능)

```jsp
<%@ page import="jsp.imageboard.*" %>

<%
    DBConnection conn = DBConnection.getConnection();
    ImageBoardDAO dao = new ImageBoardDAO(conn);
    FileUploadUtil.uploadFile(...);
%>
```

**장점:**
- 코드가 간결함
- 가독성 향상
- 유지보수 용이

---

## 🔄 다른 import 방법

### 1. 와일드카드 사용 (현재 방식)

```jsp
<%@ page import="jsp.imageboard.*" %>
```

**특징:**
- 패키지의 모든 클래스 import
- 간단하고 편리함
- 여러 클래스를 사용할 때 유용

### 2. 개별 클래스 import

```jsp
<%@ page import="jsp.imageboard.FileUploadUtil" %>
<%@ page import="jsp.imageboard.ImageBoardDAO" %>
<%@ page import="jsp.imageboard.ImageBoardDTO" %>
<%@ page import="jsp.imageboard.DBConnection" %>
```

**특징:**
- 필요한 클래스만 import
- 명시적이고 명확함
- 여러 클래스 사용 시 코드가 길어짐

### 3. 여러 패키지 import

```jsp
<%@ page import="jsp.imageboard.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
```

**특징:**
- 여러 패키지를 동시에 import 가능
- 각 패키지마다 별도 선언

---

## 📊 비교표

| 방법 | 코드 | 장점 | 단점 |
|------|------|------|------|
| **와일드카드** | `import="jsp.imageboard.*"` | 간단, 편리 | 어떤 클래스를 사용하는지 불명확 |
| **개별 import** | `import="jsp.imageboard.FileUploadUtil"` | 명확, 정확 | 코드가 길어짐 |

---

## 🎯 실제 사용 예시

### board_write_process.jsp에서의 사용

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.Part" %>
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="jsp.imageboard.*" %>  <!-- 여기! -->
<%
    // import 덕분에 간단하게 사용 가능
    Connection conn = DBConnection.getConnection();  // jsp.imageboard.DBConnection
    ImageBoardDAO boardDAO = new ImageBoardDAO(conn);  // jsp.imageboard.ImageBoardDAO
    String fileName = FileUploadUtil.uploadFile(...);  // jsp.imageboard.FileUploadUtil
%>
```

**import 없이 사용한다면:**
```jsp
<%
    java.sql.Connection conn = jsp.imageboard.DBConnection.getConnection();
    jsp.imageboard.ImageBoardDAO boardDAO = new jsp.imageboard.ImageBoardDAO(conn);
    String fileName = jsp.imageboard.FileUploadUtil.uploadFile(...);
%>
```

---

## 🔍 패키지 이름 규칙

### 패키지 이름 형식

```
패키지명.하위패키지명.클래스명
```

**예시:**
- `jsp.imageboard.FileUploadUtil`
  - 패키지: `jsp.imageboard`
  - 클래스: `FileUploadUtil`

### 패키지 이름 규칙

1. **소문자 사용** (권장)
   - `jsp.imageboard` ✅
   - `JSP.ImageBoard` ❌

2. **도메인 역순** (일반적)
   - `com.example.project` (실제 프로젝트)
   - `jsp.imageboard` (학습용)

3. **점(.)으로 구분**
   - 각 점은 디렉토리 구분

---

## 🐛 자주 발생하는 오류

### 오류 1: 패키지를 찾을 수 없음

**오류 메시지:**
```
The import jsp.imageboard cannot be resolved
```

**원인:**
- 클래스 파일이 컴파일되지 않음
- 패키지 이름이 일치하지 않음
- 클래스 경로(CLASSPATH) 설정 문제

**해결:**
- 프로젝트 빌드 확인
- 패키지 이름 확인
- 클래스 파일 위치 확인

### 오류 2: 클래스를 찾을 수 없음

**오류 메시지:**
```
DBConnection cannot be resolved
```

**원인:**
- import 문이 없음
- 패키지 이름이 잘못됨

**해결:**
```jsp
<%@ page import="jsp.imageboard.*" %>
```

---

## 📚 Java import와의 비교

### Java에서의 import

```java
package com.example;

import jsp.imageboard.*;  // Java import

public class MyClass {
    DBConnection conn = DBConnection.getConnection();
}
```

### JSP에서의 import

```jsp
<%@ page import="jsp.imageboard.*" %>  <!-- JSP import -->

<%
    DBConnection conn = DBConnection.getConnection();
%>
```

**차이점:**
- Java: `import` 키워드 사용
- JSP: `<%@ page import="..." %>` 지시어 사용
- 기능은 동일함

---

## ✅ 요약

### `<%@ page import="jsp.imageboard.*" %>`란?

**답변:**
- JSP에서 Java 패키지를 import하는 문법
- `jsp.imageboard` 패키지의 모든 클래스를 가져옴
- 클래스 이름만으로 사용 가능하게 함

### 사용하는 클래스들

- `FileUploadUtil` - 파일 업로드 유틸리티
- `ImageBoardDAO` - 데이터베이스 접근 객체
- `ImageBoardDTO` - 데이터 전송 객체
- `DBConnection` - 데이터베이스 연결

### 장점

- 코드 간결성
- 가독성 향상
- 유지보수 용이

**`<%@ page import="jsp.imageboard.*" %>`는 패키지의 모든 클래스를 가져와서 간단하게 사용할 수 있게 해주는 JSP 문법입니다!** 📦
