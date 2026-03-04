# 📚 JSP 학습 자료

## 📋 목차

1. [JSP 기초](#1-jsp-기초)
2. [JSP 문법](#2-jsp-문법)
3. [JSP 내장 객체](#3-jsp-내장-객체)
4. [JSTL과 EL](#4-jstl과-el)
5. [실전 예제](#5-실전-예제)
6. [참고 자료](#6-참고-자료)

---

## 1. JSP 기초

### 1.1 JSP란?

**JSP (JavaServer Pages)**는 Java 기반의 서버 사이드 웹 개발 기술입니다.

- **HTML + Java 코드**를 함께 사용
- 서버에서 실행되어 **동적 웹 페이지** 생성
- 클라이언트에게는 **HTML**로 전송

### 1.2 JSP의 장점

- ✅ HTML과 Java 코드를 분리하여 유지보수 용이
- ✅ 서버 사이드 로직 처리 가능
- ✅ 데이터베이스 연동 쉬움
- ✅ 재사용 가능한 컴포넌트 개발

### 1.3 JSP 동작 원리

```
사용자 요청
    ↓
JSP 파일 (.jsp)
    ↓
서블릿으로 변환 (.java)
    ↓
컴파일 (.class)
    ↓
실행 (서버에서)
    ↓
HTML 생성
    ↓
클라이언트에게 전송
```

---

## 2. JSP 문법

### 2.1 JSP 지시자 (Directive)

#### page 지시자
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
```

**주요 속성:**
- `language`: 사용 언어 (기본값: java)
- `contentType`: 응답 컨텐츠 타입
- `pageEncoding`: 페이지 인코딩
- `import`: Java 클래스 import
- `session`: 세션 사용 여부 (기본값: true)
- `errorPage`: 에러 발생 시 이동할 페이지

**예시:**
```jsp
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList, java.util.HashMap" %>
```

#### include 지시자
```jsp
<%@ include file="header.jsp" %>
```

**용도:** 다른 JSP 파일을 현재 페이지에 포함

#### taglib 지시자
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

**용도:** 태그 라이브러리 사용 (JSTL 등)

---

### 2.2 스크립틀릿 (Scriptlet)

**문법:**
```jsp
<% Java 코드 %>
```

**예시:**
```jsp
<%
    String name = "홍길동";
    int age = 25;
    out.println("이름: " + name);
%>
```

**주의사항:**
- HTML과 섞어서 사용 가능
- 변수는 메서드 내부 변수처럼 동작

---

### 2.3 표현식 (Expression)

**문법:**
```jsp
<%= 표현식 %>
```

**예시:**
```jsp
<p>이름: <%= name %></p>
<p>나이: <%= age %></p>
<p>현재 시간: <%= new Date() %></p>
```

**특징:**
- 자동으로 `out.print()`로 변환됨
- 세미콜론(`;`) 사용 불가

**비교:**
```jsp
<!-- 표현식 사용 -->
<%= name %>

<!-- 스크립틀릿 사용 (동일한 결과) -->
<% out.print(name); %>
```

---

### 2.4 선언문 (Declaration)

**문법:**
```jsp
<%! Java 코드 %>
```

**예시:**
```jsp
<%!
    int count = 0;
    
    public int add(int a, int b) {
        return a + b;
    }
%>
```

**특징:**
- 클래스 레벨의 변수/메서드 선언
- 서블릿 클래스의 멤버 변수/메서드로 변환

---

### 2.5 주석 (Comment)

**JSP 주석:**
```jsp
<%-- 이것은 JSP 주석입니다 --%>
```

**HTML 주석:**
```html
<!-- 이것은 HTML 주석입니다 -->
```

**Java 주석:**
```jsp
<%
    // 한 줄 주석
    /* 여러 줄 주석 */
%>
```

---

## 3. JSP 내장 객체

JSP는 9개의 내장 객체를 제공합니다.

### 3.1 request 객체

**용도:** 클라이언트의 요청 정보

**주요 메서드:**
```jsp
<%
    // 파라미터 가져오기
    String name = request.getParameter("name");
    String[] hobbies = request.getParameterValues("hobby");
    
    // 속성 설정/가져오기
    request.setAttribute("user", "홍길동");
    String user = (String) request.getAttribute("user");
    
    // 요청 정보
    String method = request.getMethod();  // GET, POST
    String uri = request.getRequestURI();
    String ip = request.getRemoteAddr();
%>
```

**예시:**
```jsp
<!-- form.jsp -->
<form action="process.jsp" method="post">
    이름: <input type="text" name="name"><br>
    <input type="submit" value="전송">
</form>

<!-- process.jsp -->
<%
    String name = request.getParameter("name");
    out.println("입력한 이름: " + name);
%>
```

---

### 3.2 response 객체

**용도:** 클라이언트에게 응답

**주요 메서드:**
```jsp
<%
    // 리다이렉트
    response.sendRedirect("success.jsp");
    
    // 쿠키 설정
    Cookie cookie = new Cookie("name", "value");
    cookie.setMaxAge(3600);
    response.addCookie(cookie);
    
    // 헤더 설정
    response.setContentType("text/html; charset=UTF-8");
%>
```

---

### 3.3 session 객체

**용도:** 사용자 세션 관리

**주요 메서드:**
```jsp
<%
    // 세션에 값 저장
    session.setAttribute("username", "홍길동");
    session.setAttribute("loginTime", new Date());
    
    // 세션에서 값 가져오기
    String username = (String) session.getAttribute("username");
    
    // 세션 제거
    session.removeAttribute("username");
    
    // 세션 무효화
    session.invalidate();
    
    // 세션 ID
    String sessionId = session.getId();
    
    // 세션 생성 시간
    long createTime = session.getCreationTime();
    
    // 마지막 접근 시간
    long lastAccess = session.getLastAccessedTime();
%>
```

**예시: 로그인 처리**
```jsp
<!-- login.jsp -->
<%
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    if ("admin".equals(id) && "1234".equals(pw)) {
        session.setAttribute("loginId", id);
        response.sendRedirect("main.jsp");
    } else {
        out.println("<script>alert('로그인 실패'); history.back();</script>");
    }
%>
```

---

### 3.4 application 객체

**용도:** 애플리케이션 전체에서 공유되는 정보

**주요 메서드:**
```jsp
<%
    // 속성 설정/가져오기
    application.setAttribute("visitCount", 0);
    Integer count = (Integer) application.getAttribute("visitCount");
    
    // 서버 정보
    String serverInfo = application.getServerInfo();
    String realPath = application.getRealPath("/");
%>
```

**예시: 방문자 카운터**
```jsp
<%
    Integer count = (Integer) application.getAttribute("visitCount");
    if (count == null) {
        count = 0;
    }
    count++;
    application.setAttribute("visitCount", count);
%>
<p>총 방문자 수: <%= count %></p>
```

---

### 3.5 out 객체

**용도:** 출력 스트림

**주요 메서드:**
```jsp
<%
    out.print("텍스트 출력");
    out.println("텍스트 출력 + 줄바꿈");
    out.write("문자 출력");
    
    // 버퍼 관련
    out.flush();  // 버퍼 비우기
    out.clear();  // 버퍼 지우기
%>
```

---

### 3.6 pageContext 객체

**용도:** 페이지 컨텍스트 정보

**주요 메서드:**
```jsp
<%
    // 다른 내장 객체 가져오기
    HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
    HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
    HttpSession sess = pageContext.getSession();
    
    // 속성 관리 (scope 지정)
    pageContext.setAttribute("name", "홍길동", PageContext.REQUEST_SCOPE);
    pageContext.setAttribute("name", "홍길동", PageContext.SESSION_SCOPE);
    pageContext.setAttribute("name", "홍길동", PageContext.APPLICATION_SCOPE);
%>
```

---

### 3.7 page 객체

**용도:** 현재 JSP 페이지의 서블릿 인스턴스

**사용 빈도가 낮음**

---

### 3.8 config 객체

**용도:** 서블릿 설정 정보

**주요 메서드:**
```jsp
<%
    String servletName = config.getServletName();
    String initParam = config.getInitParameter("paramName");
%>
```

---

### 3.9 exception 객체

**용도:** 에러 페이지에서 예외 정보

**사용 조건:**
- `<%@ page isErrorPage="true" %>` 설정 필요

**예시:**
```jsp
<%@ page isErrorPage="true" %>
<%
    String errorMessage = exception.getMessage();
    exception.printStackTrace();
%>
<p>에러 메시지: <%= errorMessage %></p>
```

---

## 4. JSTL과 EL

### 4.1 EL (Expression Language)

**문법:**
```jsp
${표현식}
```

**예시:**
```jsp
<!-- 변수 출력 -->
<p>이름: ${name}</p>

<!-- 내장 객체 사용 -->
<p>파라미터: ${param.name}</p>
<p>세션: ${session.username}</p>
<p>요청 속성: ${request.user}</p>

<!-- 연산 -->
<p>합계: ${num1 + num2}</p>
<p>비교: ${age > 18}</p>
<p>조건: ${empty list ? '비어있음' : '있음'}</p>
```

**내장 객체:**
- `pageScope`, `requestScope`, `sessionScope`, `applicationScope`
- `param`, `paramValues`
- `header`, `headerValues`
- `cookie`
- `initParam`
- `pageContext`

---

### 4.2 JSTL (JSP Standard Tag Library)

#### Core 태그 라이브러리

**선언:**
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

**주요 태그:**

**1. 변수 설정**
```jsp
<c:set var="name" value="홍길동" />
<c:set var="age" value="25" scope="session" />
```

**2. 출력**
```jsp
<c:out value="${name}" />
<c:out value="${name}" default="이름 없음" />
```

**3. 조건문**
```jsp
<c:if test="${age >= 18}">
    <p>성인입니다.</p>
</c:if>

<c:choose>
    <c:when test="${score >= 90}">
        <p>A등급</p>
    </c:when>
    <c:when test="${score >= 80}">
        <p>B등급</p>
    </c:when>
    <c:otherwise>
        <p>C등급</p>
    </c:otherwise>
</c:choose>
```

**4. 반복문**
```jsp
<!-- 숫자 반복 -->
<c:forEach var="i" begin="1" end="10" step="1">
    <p>${i}</p>
</c:forEach>

<!-- 컬렉션 반복 -->
<c:forEach var="item" items="${list}">
    <p>${item}</p>
</c:forEach>

<!-- Map 반복 -->
<c:forEach var="entry" items="${map}">
    <p>${entry.key}: ${entry.value}</p>
</c:forEach>

<!-- 인덱스 정보 -->
<c:forEach var="item" items="${list}" varStatus="status">
    <p>${status.index}: ${item} (${status.count}번째)</p>
</c:forEach>
```

**5. URL 처리**
```jsp
<c:url value="/page.jsp" var="pageUrl">
    <c:param name="id" value="123" />
    <c:param name="name" value="홍길동" />
</c:url>
<a href="${pageUrl}">링크</a>

<c:redirect url="/main.jsp" />
```

**6. 예외 처리**
```jsp
<c:catch var="error">
    <%
        int result = 10 / 0;
    %>
</c:catch>
<c:if test="${error != null}">
    <p>에러 발생: ${error.message}</p>
</c:if>
```

---

## 5. 실전 예제

### 5.1 회원가입 폼

**register.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
    <h2>회원가입</h2>
    <form action="register_process.jsp" method="post">
        아이디: <input type="text" name="id" required><br>
        비밀번호: <input type="password" name="pw" required><br>
        이름: <input type="text" name="name" required><br>
        이메일: <input type="email" name="email"><br>
        성별: 
        <input type="radio" name="gender" value="남">남
        <input type="radio" name="gender" value="여">여<br>
        취미:
        <input type="checkbox" name="hobby" value="독서">독서
        <input type="checkbox" name="hobby" value="운동">운동
        <input type="checkbox" name="hobby" value="영화">영화<br>
        <input type="submit" value="가입">
    </form>
</body>
</html>
```

**register_process.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>가입 완료</title>
</head>
<body>
    <h2>회원가입 정보</h2>
    <%
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobby");
    %>
    
    <p>아이디: <%= id %></p>
    <p>이름: <%= name %></p>
    <p>이메일: <%= email != null ? email : "없음" %></p>
    <p>성별: <%= gender != null ? gender : "선택 안함" %></p>
    <p>취미: 
    <%
        if (hobbies != null) {
            for (String hobby : hobbies) {
                out.print(hobby + " ");
            }
        } else {
            out.print("없음");
        }
    %>
    </p>
</body>
</html>
```

---

### 5.2 로그인/로그아웃

**login.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    <form action="login_process.jsp" method="post">
        아이디: <input type="text" name="id" required><br>
        비밀번호: <input type="password" name="pw" required><br>
        <input type="submit" value="로그인">
    </form>
</body>
</html>
```

**login_process.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    // 간단한 인증 (실제로는 DB에서 확인)
    if ("admin".equals(id) && "1234".equals(pw)) {
        session.setAttribute("loginId", id);
        session.setAttribute("loginTime", new java.util.Date());
        response.sendRedirect("main.jsp");
    } else {
        out.println("<script>");
        out.println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
        out.println("history.back();");
        out.println("</script>");
    }
%>
```

**main.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    java.util.Date loginTime = (java.util.Date) session.getAttribute("loginTime");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
</head>
<body>
    <h2>환영합니다, <%= loginId %>님!</h2>
    <p>로그인 시간: <%= loginTime %></p>
    <a href="logout.jsp">로그아웃</a>
</body>
</html>
```

**logout.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>
```

---

### 5.3 게시판 목록 (JSTL 사용)

**board_list.jsp:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // 간단한 게시글 목록 (실제로는 DB에서 가져옴)
    java.util.List<java.util.Map<String, Object>> boardList = new java.util.ArrayList<>();
    
    java.util.Map<String, Object> post1 = new java.util.HashMap<>();
    post1.put("no", 1);
    post1.put("title", "첫 번째 글");
    post1.put("writer", "홍길동");
    post1.put("date", "2024-01-01");
    boardList.add(post1);
    
    java.util.Map<String, Object> post2 = new java.util.HashMap<>();
    post2.put("no", 2);
    post2.put("title", "두 번째 글");
    post2.put("writer", "김철수");
    post2.put("date", "2024-01-02");
    boardList.add(post2);
    
    request.setAttribute("boardList", boardList);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h2>게시판 목록</h2>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
        </tr>
        <c:forEach var="post" items="${boardList}">
            <tr>
                <td>${post.no}</td>
                <td>${post.title}</td>
                <td>${post.writer}</td>
                <td>${post.date}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

---

## 6. 참고 자료

### 6.1 JSP 생명주기

1. **번역 단계**: JSP → 서블릿 소스 코드
2. **컴파일 단계**: 서블릿 소스 → 클래스 파일
3. **로드 단계**: 클래스 파일 메모리 로드
4. **인스턴스화**: 서블릿 인스턴스 생성
5. **초기화**: `jspInit()` 메서드 호출
6. **요청 처리**: `_jspService()` 메서드 호출
7. **소멸**: `jspDestroy()` 메서드 호출

### 6.2 Scope (범위)

| Scope | 범위 | 객체 |
|-------|------|------|
| page | 현재 페이지 | pageContext |
| request | 요청 범위 | request |
| session | 세션 범위 | session |
| application | 애플리케이션 전체 | application |

### 6.3 주의사항

1. **스크립틀릿 남용 지양**: JSTL과 EL 사용 권장
2. **보안**: SQL Injection, XSS 방지
3. **인코딩**: 항상 UTF-8 사용
4. **세션 관리**: 불필요한 세션 데이터 제거

---

## 📝 연습 문제

1. **간단한 계산기 만들기**
   - 두 숫자를 입력받아 사칙연산 결과 출력

2. **방문자 카운터**
   - application 객체를 사용하여 방문자 수 카운트

3. **쿠키 활용**
   - 마지막 방문 시간을 쿠키에 저장하고 표시

4. **파일 업로드**
   - 파일을 업로드하고 목록 표시

---

**화이팅! JSP 마스터가 되어봅시다! 💪**
