# Tomcat 버전별 패키지 변경 가이드

## ⚠️ 중요: Tomcat 10.1+ 패키지 변경

**맞습니다!** Tomcat 10.1 이상에서는 패키지 이름이 변경되었습니다.

---

## 📊 Tomcat 버전별 패키지

### Tomcat 9.x 이하 (Java EE)

```java
import javax.servlet.http.Part;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
```

**패키지:**
- `javax.servlet.*`
- `javax.servlet.http.*`

### Tomcat 10.x 이상 (Jakarta EE)

```java
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
```

**패키지:**
- `jakarta.servlet.*`
- `jakarta.servlet.http.*`

---

## 🔄 변경 사항

### 패키지 이름 변경

| Tomcat 버전 | 패키지 | 설명 |
|------------|--------|------|
| **9.x 이하** | `javax.servlet.*` | Java EE |
| **10.0** | `javax.servlet.*` | Java EE (마지막) |
| **10.1+** | `jakarta.servlet.*` | Jakarta EE 9+ |

### 변경 이유

- Oracle이 Java EE를 Eclipse Foundation에 기부
- Eclipse Foundation이 Jakarta EE로 이름 변경
- 패키지 이름도 `javax` → `jakarta`로 변경

---

## 🔧 코드 수정 방법

### 1. JSP 파일 수정

#### Tomcat 9.x 이하
```jsp
<%@ page import="javax.servlet.http.Part" %>
```

#### Tomcat 10.1+ 
```jsp
<%@ page import="jakarta.servlet.http.Part" %>
```

### 2. Java 파일 수정

#### Tomcat 9.x 이하
```java
import javax.servlet.http.Part;
import javax.servlet.http.HttpServletRequest;
```

#### Tomcat 10.1+
```java
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServletRequest;
```

---

## 📝 파일별 수정 가이드

### FileUploadUtil.java

#### ❌ Tomcat 9.x 이하 (기존)
```java
package jsp.imageboard;

import javax.servlet.http.Part;  // ❌ Tomcat 10.1+에서 오류
import java.io.File;
// ...
```

#### ✅ Tomcat 10.1+ (수정)
```java
package jsp.imageboard;

import jakarta.servlet.http.Part;  // ✅ 변경됨
import java.io.File;
// ...
```

### board_write_process.jsp

#### ❌ Tomcat 9.x 이하 (기존)
```jsp
<%@ page import="javax.servlet.http.Part" %>
```

#### ✅ Tomcat 10.1+ (수정)
```jsp
<%@ page import="jakarta.servlet.http.Part" %>
```

---

## 🔍 호환성 체크리스트

### 현재 프로젝트 확인

1. **Tomcat 버전 확인**
   ```
   Tomcat 설치 경로 확인
   또는 server.xml에서 확인
   ```

2. **패키지 이름 확인**
   ```java
   // 현재 코드에서
   import javax.servlet.*;  // Tomcat 9.x 이하
   import jakarta.servlet.*; // Tomcat 10.1+
   ```

3. **에러 메시지 확인**
   ```
   컴파일 오류: "package javax.servlet does not exist"
   → Tomcat 10.1+ 사용 중이면 jakarta로 변경 필요
   ```

---

## 🛠️ 일괄 변경 방법

### 방법 1: IDE에서 일괄 변경

**Eclipse/IntelliJ:**
1. `Ctrl + Shift + R` (전체 검색)
2. `javax.servlet` 검색
3. `jakarta.servlet`로 일괄 변경

### 방법 2: 정규식으로 변경

**검색:**
```
javax\.servlet
```

**변경:**
```
jakarta.servlet
```

### 방법 3: 수동 변경

**변경할 파일:**
- `FileUploadUtil.java`
- `board_write_process.jsp`
- `board_modify_process.jsp`
- 기타 Servlet API 사용 파일

---

## 📋 변경 대상 파일 목록

### Java 파일
- `FileUploadUtil.java`
- `DBConnection.java` (만약 Servlet API 사용 시)
- 기타 Servlet API 사용 클래스

### JSP 파일
- `board_write_process.jsp`
- `board_modify_process.jsp`
- 기타 `request.getPart()` 사용 파일

---

## 🔄 양쪽 호환 버전 만들기

### 조건부 import (비권장, 복잡함)

```java
// 복잡하고 비권장
try {
    Class<?> partClass = Class.forName("jakarta.servlet.http.Part");
    // Jakarta EE 사용
} catch (ClassNotFoundException e) {
    // Java EE 사용
    Class<?> partClass = Class.forName("javax.servlet.http.Part");
}
```

**권장:**
- 프로젝트의 Tomcat 버전에 맞게 하나로 통일

---

## 📚 Tomcat 버전별 권장사항

### Tomcat 9.x 사용 시
```java
import javax.servlet.http.Part;
```

### Tomcat 10.1+ 사용 시
```java
import jakarta.servlet.http.Part;
```

### 둘 다 지원해야 할 경우
- 별도 브랜치로 관리
- 또는 Maven 프로파일 사용

---

## 🐛 자주 발생하는 오류

### 오류 1: 패키지를 찾을 수 없음

**에러 메시지:**
```
package javax.servlet does not exist
```

**원인:**
- Tomcat 10.1+에서 `javax.servlet` 사용

**해결:**
```java
// ❌
import javax.servlet.http.Part;

// ✅
import jakarta.servlet.http.Part;
```

### 오류 2: 컴파일은 되지만 런타임 오류

**원인:**
- 컴파일 시 다른 버전의 Servlet API 사용
- 런타임 시 다른 버전의 Tomcat 사용

**해결:**
- 컴파일과 런타임 환경 일치 확인

---

## ✅ 수정된 코드 예제

### FileUploadUtil.java (Tomcat 10.1+)

```java
package jsp.imageboard;

import jakarta.servlet.http.Part;  // ✅ 변경됨
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 파일 업로드 유틸리티 클래스
 * Tomcat 10.1+ 호환
 */
public class FileUploadUtil {
    // ... 나머지 코드 동일
}
```

### board_write_process.jsp (Tomcat 10.1+)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.Part" %>  <!-- ✅ 변경됨 -->
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="jsp.imageboard.*" %>

<%
    request.setCharacterEncoding("UTF-8");
    
    // Part 객체 가져오기
    Part imagePart = request.getPart("image");
    // ... 나머지 코드 동일
%>
```

---

## 🔍 Tomcat 버전 확인 방법

### 방법 1: 서버 정보 확인

**브라우저에서:**
```
http://localhost:8080/
```

**또는:**
```jsp
<%
    out.println("Server Info: " + application.getServerInfo());
%>
```

### 방법 2: 설치 경로 확인

**Windows:**
```
C:\Program Files\Apache Software Foundation\Tomcat 10.1
```

**Linux/Mac:**
```
/usr/local/tomcat10
```

### 방법 3: 버전 파일 확인

**파일:**
```
$CATALINA_HOME/RELEASE-NOTES
```

---

## 📦 Maven 의존성 (참고)

### Tomcat 9.x 이하

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
```

### Tomcat 10.1+

```xml
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
    <scope>provided</scope>
</dependency>
```

---

## 🎯 요약

### Tomcat 버전별 패키지

| Tomcat 버전 | 패키지 | 예시 |
|------------|--------|------|
| **9.x 이하** | `javax.servlet.*` | `javax.servlet.http.Part` |
| **10.0** | `javax.servlet.*` | `javax.servlet.http.Part` |
| **10.1+** | `jakarta.servlet.*` | `jakarta.servlet.http.Part` |

### 수정 방법

1. **JSP 파일:**
   ```jsp
   <%@ page import="jakarta.servlet.http.Part" %>
   ```

2. **Java 파일:**
   ```java
   import jakarta.servlet.http.Part;
   ```

3. **일괄 변경:**
   - `javax.servlet` → `jakarta.servlet`

### 체크리스트

- [ ] Tomcat 버전 확인
- [ ] `javax.servlet` → `jakarta.servlet` 변경
- [ ] 모든 파일 수정 확인
- [ ] 컴파일 및 실행 테스트

---

## 💡 권장사항

### 1. 프로젝트 시작 시

- 사용할 Tomcat 버전 결정
- 해당 버전에 맞는 패키지 사용

### 2. 기존 프로젝트 마이그레이션

- Tomcat 버전 확인
- 패키지 일괄 변경
- 테스트 진행

### 3. 호환성 고려

- 가능하면 최신 버전 사용 (Jakarta EE)
- 또는 프로젝트 요구사항에 맞게 선택

**Tomcat 10.1+ 사용 시 `jakarta.servlet` 패키지를 사용해야 합니다!** ✅
