# JSP 액션 태그 · 자바빈즈 (JavaBeans)

`useBean` → `setProperty` → `getProperty` 3개를 중심으로 이해하면 됩니다.

---

## 1. 자바빈즈 액션 태그 3가지

| 액션 태그 | 역할 |
|-----------|------|
| `<jsp:useBean>` | JavaBean 객체 생성/사용 |
| `<jsp:setProperty>` | Bean의 값 저장 (setter) |
| `<jsp:getProperty>` | Bean의 값 가져오기 (getter) |

---

## 2. JavaBean 만들기

예: `Member.java`

```java
package com.example;

public class Member {

    private String id;
    private String name;

    public Member() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

**필수 포인트**

- 필드: `private String id;`, `private String name;`
- 기본 생성자: `public Member() {}`
- getter / setter: `getId` · `setId` · `getName` · `setName`

> 프로퍼티 이름 `id` ↔ `getId()` / `setId()` 규칙이 맞아야 액션 태그가 동작합니다.

---

## 3. Bean 객체 만들기 — useBean

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>

<h2>JavaBean 실습</h2>

<jsp:useBean
    id="member"
    class="com.example.Member"
    scope="page" />
```

| 속성 | 의미 |
|------|------|
| `id="member"` | JSP에서 쓸 객체 이름 |
| `class="com.example.Member"` | Bean 클래스 |
| `scope="page"` | 이 페이지에서만 사용 |

---

## 4. 값 저장 — setProperty

```jsp
<jsp:setProperty
    name="member"
    property="id"
    value="admin" />

<jsp:setProperty
    name="member"
    property="name"
    value="홍길동" />
```

Java로 보면:

```java
member.setId("admin");
member.setName("홍길동");
```

---

## 5. 값 가져오기 — getProperty

```jsp
아이디 :
<jsp:getProperty name="member" property="id" />

<br>

이름 :
<jsp:getProperty name="member" property="name" />
```

결과:

```text
아이디 : admin
이름 : 홍길동
```

---

## 6. 전체 간단 예제

### 6.1 값 직접 넣기 (`member.jsp`)

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>

<h2>JavaBean 실습</h2>

<jsp:useBean
    id="member"
    class="com.example.Member"
    scope="page" />

<jsp:setProperty name="member" property="id" value="admin" />
<jsp:setProperty name="member" property="name" value="홍길동" />

<p>
  아이디 :
  <jsp:getProperty name="member" property="id" />
</p>

<p>
  이름 :
  <jsp:getProperty name="member" property="name" />
</p>
```

---

## 7. Java 코드와 비교

```jsp
<jsp:useBean id="member" class="com.example.Member" />
<jsp:setProperty name="member" property="id" value="admin" />
<jsp:getProperty name="member" property="id" />
```

대략 다음 Java와 같은 흐름입니다.

```java
Member member = new Member();   // useBean
member.setId("admin");          // setProperty
member.getId();                 // getProperty
```

```text
useBean      → 객체 생성
setProperty  → setter()
getProperty  → getter()
```

---

## 8. 실무에서 자주 쓰는 `property="*"`

### HTML 폼 (`form.jsp`)

```jsp
<form action="memberResult.jsp" method="post">
  아이디 : <input type="text" name="id">
  이름 : <input type="text" name="name">
  <button type="submit">전송</button>
</form>
```

사용자가 `id=hong`, `name=홍길동` 입력 시:

### 결과 페이지 (`memberResult.jsp`)

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="member" class="com.example.Member" />
<jsp:setProperty name="member" property="*" />

<p>아이디 : <jsp:getProperty name="member" property="id" /></p>
<p>이름 : <jsp:getProperty name="member" property="name" /></p>
```

폼의 `name`과 Bean 프로퍼티 이름이 같으면 **자동으로 setter 호출**:

```text
HTML form              JavaBean
──────────────────────────────
name="id"       →     setId()
name="name"     →     setName()
```

> `property="*"` = request 파라미터 이름과 Bean 프로퍼티 이름이 같으면 자동 연결

---

## 9. 왜 쓰나?

기존 방식:

```jsp
<%
  String id = request.getParameter("id");
  String name = request.getParameter("name");
%>
```

Bean 사용 시 흐름:

```text
HTML
 ↓
request parameter
 ↓
JavaBean
 ↓
JSP
```

MVC로 발전하면:

```text
브라우저
  ↓
Servlet / Controller
  ↓
JavaBean / DTO
  ↓
Service → DAO → DB
```

데이터를 **객체로 담아 관리**하는 연습이 됩니다.

---

## 학생용 한 줄 정리

| 태그 | 의미 |
|------|------|
| **useBean** | 객체 만들기 |
| **setProperty** | setter로 값 넣기 |
| **getProperty** | getter로 값 꺼내기 |
| **property="*"** | request 파라미터 ↔ Bean 프로퍼티 이름 자동 연결 |

---

## 실습 체크리스트

- [ ] `Member.java`에 private 필드 + 기본 생성자 + getter/setter
- [ ] `useBean`으로 객체 생성
- [ ] `setProperty` / `getProperty`로 값 넣고 출력
- [ ] 폼 + `property="*"`로 자동 매핑 확인
- [ ] POST일 때 `request.setCharacterEncoding("UTF-8")` 확인

---

## 관련 자료 · 실행 파일

| 경로 | 설명 |
|------|------|
| `jsp/예제/18-자바빈즈/src/com/example/Member.java` | JavaBean |
| `jsp/예제/18-자바빈즈/WebContent/member.jsp` | 값 직접 넣기 |
| `jsp/예제/18-자바빈즈/WebContent/form.jsp` | 입력 폼 |
| `jsp/예제/18-자바빈즈/WebContent/memberResult.jsp` | `property="*"` |
| `jsp/README.md` | JSP 전체 정리 |
