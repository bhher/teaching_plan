# JSP 폼 검증 · 정규식(Regular Expression) 정리

회원 가입 폼에서 **자바스크립트 정규식**으로 입력값을 검사한 뒤,  
통과하면 `form.submit()`으로 처리 JSP에 보내는 패턴입니다.

예제 흐름:

```text
validation05.jsp  (JS 정규식 검사)
        ↓ submit
validation05_process.jsp  (request 파라미터 출력)
```

---

## 1. 정규식이란?

**정규식(Regular Expression, RegExp)** 은  
문자열이 **정해진 규칙에 맞는지** 검사하는 패턴 표기법입니다.

```js
var regExpName = /^[가-힣]*$/;

regExpName.test("홍길동");  // true
regExpName.test("Hong");   // false
```

| 용도 | 예 |
|------|-----|
| 아이디 형식 | 영문·한글로 시작 |
| 비밀번호 | 숫자만 / 영문+숫자 |
| 휴대폰 | `010-1234-5678` |
| 이메일 | `user@mail.com` |

JSP 자체 문법이라기보다, **브라우저에서 실행되는 JavaScript**로 검사하는 경우가 많습니다.  
(서버에서도 Java `Pattern`으로 검사할 수 있지만, 교재·수업은 보통 **클라이언트 JS 정규식**부터 합니다.)

---

## 2. 정규식 기본 기호

| 기호 | 의미 | 예 |
|------|------|-----|
| `/ ... /` | 정규식 리터럴 | `/abc/` |
| `^` | **맨 앞**부터 | `^a` → a로 시작 |
| `$` | **맨 끝**까지 | `a$` → a로 끝 |
| `*` | 0번 이상 반복 | `a*` → 빈문자, a, aa… |
| `+` | 1번 이상 반복 | `a+` → a, aa… (빈문자 X) |
| `?` | 0 또는 1번 | `-?` → 하이픈 있어도/없어도 |
| `.` | 아무 문자 1개 | `a.c` → abc, a1c… |
| `\|` | 또는 | `a\|b` |
| `[]` | 문자 집합 | `[0-9]` 숫자 |
| `[^]` | 제외 | `[^0-9]` 숫자 아님 |
| `{n}` | 정확히 n번 | `\d{3}` |
| `{n,m}` | n~m번 | `\d{3,4}` |
| `\d` | 숫자 `[0-9]` | |
| `\w` | 영문·숫자·`_` | |
| `i` 플래그 | 대소문자 무시 | `/abc/i` |

자주 쓰는 틀:

```text
/^     시작
 패턴
$/     끝
```

→ **전체 문자열이 패턴과 일치**해야 할 때 `^` `$`를 붙입니다.

---

## 3. 예제에 나온 정규식 해설

```js
var regExpId = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
var regExpName = /^[가-힣]*$/;
var regExpPasswd = /^[0-9]*$/;
var regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
var regExpEmail =
  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
```

### 3.1 아이디 `regExpId`

```js
/^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/
```

- `^` … 문자열 **시작**
- `[a-z A-Z ㄱ-ㅎ ㅏ-ㅣ 가-힣]` … 영문 또는 한글(자모·완성형)
- (끝에 `$`가 없으면) **첫 글자만** 검사하는 형태에 가깝습니다.

수업용으로 **아이디 전체**를 영문·숫자만 허용하려면 아래처럼 쓰는 경우가 더 흔합니다.

```js
/^[a-zA-Z][a-zA-Z0-9]{3,11}$/   // 영문 시작, 4~12자
```

### 3.2 이름 `regExpName`

```js
/^[가-힣]*$/
```

- 한글만, 길이 0 이상 (`*`)
- `"홍길동"` OK · `"Hong"` NG

실무/수업에선 보통 1글자 이상:

```js
/^[가-힣]{2,5}$/
```

### 3.3 비밀번호 `regExpPasswd`

```js
/^[0-9]*$/
```

- **숫자만** (교재 단순 예제)
- 실제 서비스는 영문+숫자+특수문자 조합을 권장

```js
/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/  // 영문·숫자 포함 8자+
```

### 3.4 연락처 `regExpPhone`

```js
/^\d{3}-\d{3,4}-\d{4}$/
```

| 부분 | 의미 |
|------|------|
| `\d{3}` | 숫자 3자리 (010) |
| `-` | 하이픈 |
| `\d{3,4}` | 중간 3~4자리 |
| `\d{4}` | 끝 4자리 |

예: `010-123-4567`, `010-1234-5678`

폼이 `phone1` + `phone2` + `phone3`로 나뉘어 있으면,  
검사 전에 합칩니다.

```js
var phone = form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value;
if (!regExpPhone.test(phone)) { ... }
```

### 3.5 이메일 `regExpEmail`

```js
/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
```

대략:

```text
아이디부분 @ 도메인 . com 등
```

- `i` → 대소문자 무시
- 예: `hong@mail.com`

---

## 4. JS에서 검사하는 방법

```js
regExpName.test("홍길동");  // true / false

// 또는
"홍길동".match(regExpName);
```

폼 검사 골격:

```js
function checkMember() {
  var form = document.Member;

  if (!regExpId.test(form.id.value)) {
    alert("아이디를 확인해 주세요.");
    form.id.select();
    return;           // submit 막기
  }
  // ... 나머지 검사

  form.submit();      // 모두 통과 시 전송
}
```

> `type="button"` + `onclick="checkMember()"`  
> → 검증 후에만 `submit()` 호출 (검증 실패 시 전송 안 함)

---

## 5. 가장 많이 쓰는 정규식 치트시트

| 용도 | 정규식 | 설명 |
|------|--------|------|
| 숫자만 | `/^[0-9]+$/` | 1자 이상 숫자 |
| 영문만 | `/^[a-zA-Z]+$/` | |
| 한글만 | `/^[가-힣]+$/` | |
| 영문+숫자 | `/^[a-zA-Z0-9]+$/` | |
| 아이디(영문시작) | `/^[a-zA-Z][a-zA-Z0-9]{3,11}$/` | 4~12자 |
| 휴대폰 | `/^01[016789]-\d{3,4}-\d{4}$/` | 01x-xxx-xxxx |
| 휴대폰(하이픈없음) | `/^01[016789]\d{7,8}$/` | |
| 이메일(간단) | `/^[^\s@]+@[^\s@]+\.[^\s@]+$/` | 수업·실무 간단판 |
| 이메일(교재형) | 위 `regExpEmail` | |
| 우편번호 | `/^\d{5}$/` | 5자리 |
| URL(간단) | `/^https?:\/\/.+/` | http/https |

---

## 6. JSP 패턴 예제

### 6.1 입력 폼 + 검증 (`validation_member.jsp`)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 · 정규식 검증</title>
<script>
function checkMember() {
  var regExpId = /^[a-zA-Z][a-zA-Z0-9]{3,11}$/;
  var regExpName = /^[가-힣]{2,5}$/;
  var regExpPasswd = /^[0-9]{4,}$/;
  var regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
  var regExpEmail =
    /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

  var form = document.Member;

  if (!regExpId.test(form.id.value)) {
    alert("아이디: 영문으로 시작, 영문·숫자 4~12자");
    form.id.select();
    return;
  }
  if (!regExpPasswd.test(form.passwd.value)) {
    alert("비밀번호: 숫자 4자 이상");
    form.passwd.select();
    return;
  }
  if (!regExpName.test(form.name.value)) {
    alert("이름: 한글 2~5자");
    form.name.select();
    return;
  }

  var phone = form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value;
  if (!regExpPhone.test(phone)) {
    alert("연락처: 000-0000-0000 형식");
    form.phone2.select();
    return;
  }
  if (!regExpEmail.test(form.email.value)) {
    alert("이메일 형식을 확인해 주세요.");
    form.email.select();
    return;
  }

  form.submit();
}
</script>
</head>
<body>
  <h3>회원 가입</h3>
  <form action="validation_member_process.jsp" name="Member" method="post">
    <p>아이디 : <input type="text" name="id"></p>
    <p>비밀번호 : <input type="password" name="passwd"></p>
    <p>이름 : <input type="text" name="name"></p>
    <p>연락처 :
      <select name="phone1">
        <option value="010">010</option>
        <option value="011">011</option>
        <option value="016">016</option>
        <option value="017">017</option>
        <option value="019">019</option>
      </select>
      - <input type="text" maxlength="4" size="4" name="phone2">
      - <input type="text" maxlength="4" size="4" name="phone3">
    </p>
    <p>이메일 : <input type="text" name="email"></p>
    <p><input type="button" value="가입하기" onclick="checkMember()"></p>
  </form>
</body>
</html>
```

### 6.2 처리 페이지 (`validation_member_process.jsp`)

```jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>가입 결과</title>
</head>
<body>
<%
  request.setCharacterEncoding("UTF-8");
%>
  <p>아이디 : <%= request.getParameter("id") %></p>
  <p>비밀번호 : <%= request.getParameter("passwd") %></p>
  <p>이름 : <%= request.getParameter("name") %></p>
  <p>연락처 :
    <%= request.getParameter("phone1") %>-<%= request.getParameter("phone2") %>-<%= request.getParameter("phone3") %>
  </p>
  <p>이메일 : <%= request.getParameter("email") %></p>
</body>
</html>
```

---

## 7. 원본 코드와의 관계

제공하신 코드는 정규식 **변수만 선언**하고 `test()` 없이 `form.submit()`만 호출합니다.

수업에서는 반드시:

```js
if (!regExpXxx.test(...)) {
  alert("...");
  return;
}
form.submit();
```

순서로 **검사 → 실패 시 return → 성공 시 submit** 을 넣도록 강조하세요.

---

## 8. 클라이언트 검증의 한계

| | 클라이언트(JS) | 서버(JSP/Servlet) |
|--|----------------|-------------------|
| 속도 | 즉시 | 요청 후 |
| 우회 | JS 끄면 통과 가능 | 최종 방어 |
| 수업 | 정규식 연습에 적합 | 실무에서는 **둘 다** |

실무: 브라우저 검사 + 서버에서도 재검증.

---

## 9. 학생용 한 줄

```text
정규식 = 문자열 규칙
^ $ = 처음부터 끝까지
[] = 허용 문자
{n,m} = 반복 횟수
test() = 맞으면 true

폼: type=button → checkMember() → test() → submit()
```

---

## 10. 관련 자료

- `jsp/예제/16-폼검증/` — validation 예제 모음  
- `jsp/예제/04-폼처리/폼처리-문제답.md` — 파라미터 처리  
