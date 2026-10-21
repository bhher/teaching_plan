# JSP · Servlet — 세션(Session)과 쿠키(Cookie) 정리

HTTP는 기본적으로 **이전 요청과 다음 요청이 같은 사용자인지** 기억하지 못합니다.  
이를 보완하기 위해 **세션**과 **쿠키**를 사용합니다.

---

## 1. 세션과 쿠키의 차이

### 세션 (Session)

- 사용자 정보를 **서버에** 저장
- 서버가 사용자별 세션을 관리
- 로그인 상태 유지 등에 사용
- 서버 자원을 사용함

### 쿠키 (Cookie)

- 작은 데이터를 **브라우저에** 저장
- 요청 시 조건에 따라 서버로 전송
- 아이디 기억, 사용자 설정 등에 사용
- 사용자가 삭제할 수 있음

### 비교표

| 비교 항목 | 세션 | 쿠키 |
|-----------|------|------|
| 저장 위치 | 서버 | 클라이언트 브라우저 |
| 데이터 관리 | 서버가 관리 | 브라우저가 보관 |
| 식별 방식 | 주로 세션 ID | 쿠키에 저장된 값 |
| 유효 기간 | 만료·세션 종료 정책 | 만료 시간 설정 가능 |
| 대표 용도 | 로그인, 장바구니 | 아이디 기억, 언어·테마 |
| 보안 | 서버 저장으로 노출↓ | 브라우저 저장 → 민감정보 주의 |

---

## 2. 쿠키 동작 원리

예: 쇼핑몰에서 **아이디 기억하기**

```text
① 브라우저 → 로그인 요청
② 서버 → 쿠키 생성 후 응답에 포함
③ 브라우저 → 쿠키 저장 (예: userId=hong)
④ 다음 요청 → 조건에 맞는 쿠키를 서버에 전송
```

### 쿠키 생성 (Java)

```java
Cookie cookie = new Cookie("userId", "hong");
cookie.setMaxAge(60 * 60);  // 1시간 (초)
response.addCookie(cookie);  // 브라우저에 저장 요청
```

### 쿠키 읽기

```java
Cookie[] cookies = request.getCookies();

if (cookies != null) {
    for (Cookie cookie : cookies) {
        if ("userId".equals(cookie.getName())) {
            System.out.println(cookie.getValue());
        }
    }
}
```

| 메서드 | 기능 |
|--------|------|
| `request.getCookies()` | 요청에 포함된 쿠키 배열 |
| `getName()` | 쿠키 이름 |
| `getValue()` | 쿠키 값 |
| `setMaxAge(초)` | 유효 기간 (`0`이면 삭제) |
| `response.addCookie()` | 응답에 쿠키 추가 |

---

## 3. 세션 동작 원리

```text
① 브라우저 → 로그인 정보 전송
② 서버 → 세션 생성, 사용자 정보 저장 (예: userId=hong)
③ 브라우저 → 세션 ID 쿠키 보관 (예: JSESSIONID=...)
④ 다음 요청 → 세션 ID로 서버가 해당 세션을 찾음
```

세션과 쿠키는 **경쟁 관계가 아니라 함께** 쓰일 수 있습니다.  
일반적인 Servlet 환경에서는:

- **서버**: 세션 데이터 보관  
- **브라우저**: `JSESSIONID` 쿠키로 세션 식별  

### 세션 생성 · 사용

```java
HttpSession session = request.getSession(); // 없으면 생성

session.setAttribute("userId", "hong");

String userId = (String) session.getAttribute("userId");
System.out.println(userId);
```

### 주요 메서드

| 메서드 | 기능 |
|--------|------|
| `request.getSession()` | 세션 가져오기 (없으면 생성) |
| `request.getSession(false)` | 있을 때만 가져오기 (없으면 `null`, 새로 안 만듦) |
| `setAttribute(이름, 값)` | 세션에 저장 |
| `getAttribute(이름)` | 세션에서 조회 |
| `removeAttribute(이름)` | 특정 키 삭제 |
| `invalidate()` | 세션 전체 무효화 (로그아웃) |

### 로그아웃

```java
HttpSession session = request.getSession(false);

if (session != null) {
    session.invalidate();
}
```

---

## 4. 언제 무엇을 쓸까?

| 상황 | 적합한 방식 |
|------|-------------|
| 로그인 상태 유지 | 세션 |
| 로그아웃 | 세션 `invalidate()` |
| 화면 테마 기억 | 쿠키 (또는 브라우저 저장소) |
| 언어 설정 | 쿠키 |
| 장바구니 | 세션 또는 DB |
| 비밀번호 | **쿠키에 평문 저장 금지** |

쿠키는 사용자가 내용을 보거나 변조할 수 있으므로  
비밀번호·민감 정보를 그대로 넣으면 안 됩니다.  
로그인 유지용 쿠키를 쓴다면 `HttpOnly`, `Secure`, `SameSite` 등도 고려합니다.

---

## 5. JSP에서 간단히 쓰기

### 쿠키 (JSP)

```jsp
<%
  Cookie cookie = new Cookie("userId", "hong");
  cookie.setMaxAge(60 * 60);
  response.addCookie(cookie);
%>
```

```jsp
<%
  Cookie[] cookies = request.getCookies();
  if (cookies != null) {
    for (Cookie c : cookies) {
      if ("userId".equals(c.getName())) {
        out.println(c.getValue());
      }
    }
  }
%>
```

### 세션 (JSP) — 내장 객체 `session`

```jsp
<%
  session.setAttribute("userId", "hong");
  String userId = (String) session.getAttribute("userId");
%>
<p>로그인: <%= userId %></p>
```

```jsp
<%
  session.invalidate();  // 로그아웃
%>
```

---

## 6. 이해도 확인 퀴즈

**1.** 사용자 정보를 **서버**에 저장하는 방식은?  
① 쿠키 ② 세션 ③ 둘 다 브라우저에만 저장  

**2.** 브라우저에 `JSESSIONID`를 두는 대표 목적은?  
① 세션 전체 데이터를 브라우저에 저장  
② **서버의 세션을 식별**  
③ Java 클래스를 실행  

**3.** 세션에 사용자 ID를 저장하는 메서드는?  
① `setAttribute()` ② `getAttribute()` ③ `getCookies()`  

**4.** 쿠키에 비밀번호를 평문으로 저장해도 되나?  
① 네 ② **아니요**

### 정답

| 문항 | 정답 |
|------|------|
| 1 | ② 세션 |
| 2 | ② 서버 세션 식별 |
| 3 | ① setAttribute() |
| 4 | ② 아니요 |

---

## 7. 수업 핵심 암기

```text
세션  = 서버에 사용자 정보 저장
쿠키  = 브라우저에 작은 데이터 저장

일반적 연결:
  서버에 세션 데이터
  + 브라우저에 JSESSIONID 쿠키
  → 다음 요청 때 같은 세션을 찾음
```

---

## 관련 예제

- `jsp/예제/05-세션/` — login · session · logout  
- `jsp/예제/05-세션-설명.md`  
