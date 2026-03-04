# 📝 03-JSTL-EL.jsp 해설

## 📋 파일 개요

이 파일은 JSTL (JSP Standard Tag Library)과 EL (Expression Language)을 학습하기 위한 예제입니다. 스크립틀릿 대신 태그와 표현식을 사용하는 방법을 실습할 수 있습니다.

---

## 🎯 학습 목표

- EL 표현식 사용법
- JSTL Core 태그 사용법
- 조건문과 반복문을 태그로 구현
- 스크립틀릿 대신 태그 사용의 장점 이해

---

## 📖 코드 상세 설명

### 1. JSTL 선언

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
```

**설명:**
- **taglib 지시자**: 태그 라이브러리 사용 선언
- `prefix="c"`: Core 태그 라이브러리 (가장 많이 사용)
- `prefix="fmt"`: Formatting 태그 라이브러리
- `uri`: 태그 라이브러리의 고유 식별자

**주의사항:**
- JSTL 라이브러리(`jstl-1.2.jar`)가 필요함
- `WEB-INF/lib/` 폴더에 추가 필요

---

### 2. 데이터 설정

```jsp
<%
    // 테스트 데이터 설정
    request.setAttribute("name", "홍길동");
    request.setAttribute("age", 25);
    request.setAttribute("score", 85);
    
    // 리스트 설정
    java.util.List<String> list = new java.util.ArrayList<>();
    list.add("사과");
    list.add("바나나");
    list.add("오렌지");
    request.setAttribute("fruits", list);
    
    // Map 설정
    java.util.Map<String, Object> map = new java.util.HashMap<>();
    map.put("name", "김철수");
    map.put("age", 30);
    map.put("city", "서울");
    request.setAttribute("user", map);
%>
```

**설명:**
- EL은 `request`, `session`, `application`의 속성(attribute)에 접근
- `setAttribute()`로 저장한 데이터를 EL로 접근 가능
- 변수명을 키로 사용

---

### 3. EL 기본 사용

```jsp
<p>이름: ${name}</p>
<p>나이: ${age}</p>
<p>점수: ${score}</p>
```

**설명:**
- **EL 표현식**: `${표현식}` 형태
- 자동으로 scope를 검색 (page → request → session → application)
- null 안전 (NullPointerException 발생 안함)

**스크립틀릿 vs EL:**

| 스크립틀릿 | EL |
|-----------|-----|
| `<%= name %>` | `${name}` |
| `<%= request.getAttribute("name") %>` | `${name}` |
| 타입 캐스팅 필요 | 자동 변환 |

---

### 4. EL 연산

```jsp
<p>나이 + 10: ${age + 10}</p>
<p>점수 * 2: ${score * 2}</p>
<p>점수 >= 80: ${score >= 80}</p>
<p>점수 >= 90 ? 'A' : 'B': ${score >= 90 ? 'A' : 'B'}</p>
<p>이름이 비어있나? ${empty name ? '예' : '아니오'}</p>
```

**EL 연산자:**

| 연산자 | 설명 | 예시 |
|--------|------|------|
| `+`, `-`, `*`, `/`, `%` | 산술 연산 | `${age + 10}` |
| `==`, `!=`, `<`, `>`, `<=`, `>=` | 비교 연산 | `${age >= 18}` |
| `&&`, `\|\|`, `!` | 논리 연산 | `${age >= 18 && age < 65}` |
| `empty` | 비어있는지 확인 | `${empty list}` |
| `?:` | 삼항 연산자 | `${age >= 18 ? '성인' : '미성년'}` |

**empty 연산자:**
```jsp
${empty name}        <!-- name이 null이거나 빈 문자열이면 true -->
${!empty list}       <!-- list가 비어있지 않으면 true -->
```

---

### 5. JSTL 변수 설정

```jsp
<c:set var="message" value="안녕하세요!" />
<p>메시지: ${message}</p>

<c:set var="total" value="${age + score}" />
<p>나이 + 점수: ${total}</p>
```

**설명:**
- **`<c:set>`**: 변수 설정 태그
- `var`: 변수명
- `value`: 변수 값
- `scope`: 범위 지정 (기본값: page)

**scope 지정:**
```jsp
<c:set var="name" value="홍길동" scope="session" />
```

---

### 6. JSTL 조건문 - c:if

```jsp
<c:if test="${age >= 18}">
    <p>성인입니다.</p>
</c:if>
<c:if test="${age < 18}">
    <p>미성년자입니다.</p>
</c:if>
```

**설명:**
- **`<c:if>`**: 단순 조건문
- `test`: 조건식 (EL 표현식)
- 조건이 true이면 태그 내용 실행

**스크립틀릿 vs JSTL:**

| 스크립틀릿 | JSTL |
|-----------|------|
| `<% if (age >= 18) { %>` | `<c:if test="${age >= 18}">` |
| `<% } %>` | `</c:if>` |

---

### 7. JSTL 조건문 - c:choose

```jsp
<c:choose>
    <c:when test="${score >= 90}">
        <p>등급: A (우수)</p>
    </c:when>
    <c:when test="${score >= 80}">
        <p>등급: B (양호)</p>
    </c:when>
    <c:when test="${score >= 70}">
        <p>등급: C (보통)</p>
    </c:when>
    <c:otherwise>
        <p>등급: F (미흡)</p>
    </c:otherwise>
</c:choose>
```

**설명:**
- **`<c:choose>`**: 다중 조건문 (switch-case와 유사)
- **`<c:when>`**: 조건 분기
- **`<c:otherwise>`**: 기본값 (else와 유사)

**Java switch와 비교:**
```java
// Java
switch (score / 10) {
    case 9: case 10:
        grade = "A";
        break;
    case 8:
        grade = "B";
        break;
    default:
        grade = "F";
}
```

---

### 8. JSTL 반복문 - 숫자 반복

```jsp
<ul>
    <c:forEach var="i" begin="1" end="5" step="1">
        <li>항목 ${i}</li>
    </c:forEach>
</ul>
```

**설명:**
- **`<c:forEach>`**: 반복문 태그
- `var`: 반복 변수명
- `begin`: 시작 값
- `end`: 끝 값
- `step`: 증가 값 (기본값: 1)

**출력 결과:**
```html
<ul>
    <li>항목 1</li>
    <li>항목 2</li>
    <li>항목 3</li>
    <li>항목 4</li>
    <li>항목 5</li>
</ul>
```

---

### 9. JSTL 반복문 - 리스트 반복

```jsp
<ul>
    <c:forEach var="fruit" items="${fruits}">
        <li>${fruit}</li>
    </c:forEach>
</ul>
```

**설명:**
- `items`: 반복할 컬렉션 (리스트, 배열 등)
- `var`: 각 항목을 담을 변수명

**스크립틀릿 vs JSTL:**

| 스크립틀릿 | JSTL |
|-----------|------|
| `<% for (String fruit : fruits) { %>` | `<c:forEach var="fruit" items="${fruits}">` |
| `<%= fruit %>` | `${fruit}` |
| `<% } %>` | `</c:forEach>` |

---

### 10. JSTL 반복문 - varStatus 사용

```jsp
<table border="1">
    <tr>
        <th>인덱스</th>
        <th>순번</th>
        <th>첫번째</th>
        <th>마지막</th>
        <th>값</th>
    </tr>
    <c:forEach var="fruit" items="${fruits}" varStatus="status">
        <tr>
            <td>${status.index}</td>
            <td>${status.count}</td>
            <td>${status.first}</td>
            <td>${status.last}</td>
            <td>${fruit}</td>
        </tr>
    </c:forEach>
</table>
```

**varStatus 속성:**

| 속성 | 설명 | 예시 |
|------|------|------|
| `index` | 0부터 시작하는 인덱스 | 0, 1, 2, ... |
| `count` | 1부터 시작하는 순번 | 1, 2, 3, ... |
| `first` | 첫 번째 항목인지 | true/false |
| `last` | 마지막 항목인지 | true/false |

**활용 예시:**
```jsp
<c:forEach var="item" items="${list}" varStatus="status">
    <c:if test="${status.first}">
        <p>시작합니다!</p>
    </c:if>
    ${item}
    <c:if test="${status.last}">
        <p>끝났습니다!</p>
    </c:if>
</c:forEach>
```

---

### 11. JSTL 반복문 - Map 반복

```jsp
<ul>
    <c:forEach var="entry" items="${user}">
        <li>${entry.key}: ${entry.value}</li>
    </c:forEach>
</ul>
```

**설명:**
- Map을 반복하면 `Map.Entry` 객체가 반환됨
- `entry.key`: 키 값
- `entry.value`: 값

**출력 결과:**
```html
<ul>
    <li>name: 김철수</li>
    <li>age: 30</li>
    <li>city: 서울</li>
</ul>
```

---

### 12. 중첩 반복문과 조건문

```jsp
<p>짝수만 출력:</p>
<c:forEach var="num" items="${numbers}">
    <c:if test="${num % 2 == 0}">
        ${num}
    </c:if>
</c:forEach>
```

**설명:**
- JSTL 태그를 중첩하여 사용 가능
- 복잡한 로직도 태그로 표현 가능

---

### 13. URL 처리 - c:url

```jsp
<c:url value="/test.jsp" var="testUrl">
    <c:param name="id" value="123" />
    <c:param name="name" value="홍길동" />
</c:url>
<p>생성된 URL: <a href="${testUrl}">${testUrl}</a></p>
```

**설명:**
- **`<c:url>`**: URL 생성 및 인코딩
- **`<c:param>`**: 쿼리 파라미터 추가
- 자동으로 URL 인코딩 처리

**생성된 URL:**
```
/test.jsp?id=123&name=%ED%99%8D%EA%B8%B8%EB%8F%99
```

**장점:**
- 한글 자동 인코딩
- 컨텍스트 경로 자동 추가

---

### 14. 예외 처리 - c:catch

```jsp
<c:catch var="error">
    <%
        int result = 10 / 0;
    %>
</c:catch>
<c:if test="${error != null}">
    <p style="color: red;">에러 발생: ${error.message}</p>
</c:if>
```

**설명:**
- **`<c:catch>`**: 예외를 잡아서 처리
- `var`: 예외 객체를 저장할 변수명
- 예외가 발생해도 페이지가 중단되지 않음

**활용:**
- 안전한 코드 작성
- 에러 메시지 표시
- 로깅

---

### 15. 안전한 출력 - c:out

```jsp
<c:set var="htmlContent" value="<script>alert('XSS');</script>" />
<p>일반 출력 (위험): ${htmlContent}</p>
<p>c:out 사용 (안전): <c:out value="${htmlContent}" /></p>
<p>기본값 사용: <c:out value="${notExist}" default="값이 없습니다." /></p>
```

**설명:**
- **`<c:out>`**: 안전한 출력 (XSS 방지)
- HTML 태그를 이스케이프 처리
- `default`: 값이 없을 때 기본값

**XSS 공격 방지:**
```jsp
<!-- 위험 -->
${userInput}  <!-- 사용자 입력이 그대로 출력됨 -->

<!-- 안전 -->
<c:out value="${userInput}" />  <!-- HTML 태그가 이스케이프됨 -->
```

---

### 16. EL 내장 객체

```jsp
<p>파라미터: ${param.name != null ? param.name : '없음'}</p>
<p>세션: ${session.username != null ? session.username : '없음'}</p>
<p>요청 속성: ${request.name}</p>
<p>애플리케이션 속성: ${application.visitCount}</p>
```

**EL 내장 객체:**

| 객체 | 설명 | 예시 |
|------|------|------|
| `pageScope` | page scope 속성 | `${pageScope.name}` |
| `requestScope` | request scope 속성 | `${requestScope.name}` |
| `sessionScope` | session scope 속성 | `${sessionScope.name}` |
| `applicationScope` | application scope 속성 | `${applicationScope.name}` |
| `param` | 요청 파라미터 | `${param.id}` |
| `paramValues` | 요청 파라미터 배열 | `${paramValues.hobby[0]}` |
| `header` | 요청 헤더 | `${header['User-Agent']}` |
| `cookie` | 쿠키 | `${cookie.lastVisit.value}` |
| `initParam` | 초기화 파라미터 | `${initParam.encoding}` |
| `pageContext` | pageContext 객체 | `${pageContext.request.contextPath}` |

**파라미터 접근:**
```jsp
<!-- URL: test.jsp?id=123&name=홍길동 -->
${param.id}        <!-- 123 -->
${param.name}      <!-- 홍길동 -->
${paramValues.hobby[0]}  <!-- 첫 번째 hobby 값 -->
```

**쿠키 접근:**
```jsp
${cookie.lastVisit.value}  <!-- 쿠키 값 -->
${cookie.lastVisit.maxAge}  <!-- 쿠키 유효 시간 -->
```

---

## 🔍 스크립틀릿 vs JSTL+EL 비교

### 조건문

**스크립틀릿:**
```jsp
<%
    if (age >= 18) {
        out.println("<p>성인입니다.</p>");
    }
%>
```

**JSTL+EL:**
```jsp
<c:if test="${age >= 18}">
    <p>성인입니다.</p>
</c:if>
```

**장점:**
- HTML 구조가 명확함
- 디자이너도 이해하기 쉬움
- 유지보수 용이

---

### 반복문

**스크립틀릿:**
```jsp
<%
    for (String fruit : fruits) {
        out.println("<li>" + fruit + "</li>");
    }
%>
```

**JSTL+EL:**
```jsp
<c:forEach var="fruit" items="${fruits}">
    <li>${fruit}</li>
</c:forEach>
```

**장점:**
- HTML 태그가 명확하게 보임
- 코드 가독성 향상

---

## 💡 실전 활용 팁

### 팁 1: null 안전 처리

```jsp
<!-- 안전한 방법 -->
${name != null ? name : '이름 없음'}
${empty name ? '이름 없음' : name}
```

### 팁 2: 리스트가 비어있는지 확인

```jsp
<c:if test="${!empty list}">
    <c:forEach var="item" items="${list}">
        ${item}
    </c:forEach>
</c:if>
```

### 팁 3: 첫 번째/마지막 항목 처리

```jsp
<c:forEach var="item" items="${list}" varStatus="status">
    <c:if test="${status.first}">시작: </c:if>
    ${item}
    <c:if test="${!status.last}">, </c:if>
    <c:if test="${status.last}"> 끝</c:if>
</c:forEach>
```

---

## 🐛 자주 발생하는 오류

### 오류 1: JSTL 태그 인식 안됨

**원인:** JSTL 라이브러리 누락

**해결:**
1. `jstl-1.2.jar` 다운로드
2. `WEB-INF/lib/` 폴더에 추가
3. 프로젝트 재시작

### 오류 2: EL 표현식이 출력되지 않음

**원인:** EL이 비활성화됨

**해결:**
```jsp
<%@ page isELIgnored="false" %>
```

### 오류 3: 속성에 접근 불가

**원인:** `setAttribute()`로 저장하지 않음

**해결:**
```jsp
<%
    request.setAttribute("name", "홍길동");
%>
${name}  <!-- 이제 접근 가능 -->
```

---

## ✅ 체크리스트

다음 사항들을 확인하세요:

- [ ] EL 표현식 사용 가능
- [ ] JSTL 변수 설정 가능
- [ ] c:if 조건문 사용 가능
- [ ] c:choose 다중 조건문 사용 가능
- [ ] c:forEach 반복문 사용 가능
- [ ] varStatus 활용 가능
- [ ] c:url로 URL 생성 가능
- [ ] c:out으로 안전하게 출력 가능
- [ ] EL 내장 객체 사용 가능

---

**이 예제를 완료하면 JSTL과 EL을 마스터할 수 있습니다! 💪**
