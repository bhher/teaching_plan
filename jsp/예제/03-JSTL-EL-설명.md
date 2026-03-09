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

---

## 🚀 JSTL-EL 3.0 버전 (고급)

### 📌 JSTL 3.0 개요

JSTL 3.0은 Jakarta EE 8 기반의 최신 버전으로, EL 3.0의 강력한 기능들을 지원합니다.

### 주요 변경사항

1. **네임스페이스 변경**
   - `javax.servlet.*` → `jakarta.servlet.*`
   - `javax.servlet.jsp.*` → `jakarta.servlet.jsp.*`

2. **EL 3.0 지원**
   - 람다 표현식
   - 스트림 API
   - 새로운 연산자
   - 함수형 프로그래밍 스타일

---

## 📋 EL 3.0 주요 기능

### 1. 람다 표현식 (Lambda Expressions)

**기본 문법:**
```jsp
${(x) -> x * 2}
```

**사용 예시:**
```jsp
<c:set var="double" value="${(x) -> x * 2}" />
${double(5)}  <!-- 결과: 10 -->
```

**복잡한 람다:**
```jsp
<!-- 두 개의 매개변수 -->
${(x, y) -> x + y}

<!-- 조건부 람다 -->
${(x) -> x > 0 ? x : -x}
```

---

### 2. 스트림 API

**리스트 필터링:**
```jsp
<c:set var="numbers" value="${[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}" />

<!-- 짝수만 필터링 -->
${numbers.stream().filter(x -> x % 2 == 0).toList()}
<!-- 결과: [2, 4, 6, 8, 10] -->
```

**리스트 변환 (map):**
```jsp
<!-- 각 요소를 2배로 -->
${numbers.stream().map(x -> x * 2).toList()}
<!-- 결과: [2, 4, 6, 8, 10, 12, 14, 16, 18, 20] -->
```

**리스트 집계:**
```jsp
<!-- 합계 -->
${numbers.stream().sum()}
<!-- 결과: 55 -->

<!-- 최대값 -->
${numbers.stream().max().get()}
<!-- 결과: 10 -->

<!-- 최소값 -->
${numbers.stream().min().get()}
<!-- 결과: 1 -->

<!-- 평균 -->
${numbers.stream().average().get()}
<!-- 결과: 5.5 -->
```

---

### 3. 문자열 스트림

**문자열 처리:**
```jsp
<c:set var="text" value="Hello World Java JSP" />

<!-- 단어로 분리 -->
${text.split(' ').stream().toList()}
<!-- 결과: [Hello, World, Java, JSP] -->

<!-- 대문자로 변환 -->
${text.split(' ').stream().map(s -> s.toUpperCase()).toList()}
<!-- 결과: [HELLO, WORLD, JAVA, JSP] -->

<!-- 길이가 4 이상인 단어만 -->
${text.split(' ').stream().filter(s -> s.length() >= 4).toList()}
<!-- 결과: [Hello, World, Java] -->
```

---

### 4. 컬렉션 스트림

**리스트 처리:**
```jsp
<%
    List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date");
    request.setAttribute("fruits", fruits);
%>

<!-- 대문자로 변환 -->
${fruits.stream().map(f -> f.toUpperCase()).toList()}
<!-- 결과: [APPLE, BANANA, CHERRY, DATE] -->

<!-- 'a'가 포함된 과일만 -->
${fruits.stream().filter(f -> f.contains("a")).toList()}
<!-- 결과: [apple, banana, date] -->

<!-- 길이 순으로 정렬 -->
${fruits.stream().sorted((a, b) -> a.length() - b.length()).toList()}
<!-- 결과: [date, apple, banana, cherry] -->
```

---

### 5. 조건부 필터링

**복잡한 조건:**
```jsp
<c:set var="ages" value="${[15, 20, 25, 30, 35, 40]}" />

<!-- 20세 이상 35세 이하 -->
${ages.stream().filter(age -> age >= 20 && age <= 35).toList()}
<!-- 결과: [20, 25, 30, 35] -->
```

---

### 6. 중첩 스트림

**2차원 리스트 처리:**
```jsp
<%
    List<List<Integer>> matrix = Arrays.asList(
        Arrays.asList(1, 2, 3),
        Arrays.asList(4, 5, 6),
        Arrays.asList(7, 8, 9)
    );
    request.setAttribute("matrix", matrix);
%>

<!-- 평탄화 (flatten) -->
${matrix.stream().flatMap(row -> row.stream()).toList()}
<!-- 결과: [1, 2, 3, 4, 5, 6, 7, 8, 9] -->

<!-- 각 행의 합계 -->
${matrix.stream().map(row -> row.stream().sum()).toList()}
<!-- 결과: [6, 15, 24] -->
```

---

### 7. 집계 함수

**통계 계산:**
```jsp
<c:set var="scores" value="${[85, 92, 78, 96, 88]}" />

<!-- 합계 -->
합계: ${scores.stream().sum()}

<!-- 평균 -->
평균: ${scores.stream().average().get()}

<!-- 최고점 -->
최고점: ${scores.stream().max().get()}

<!-- 최저점 -->
최저점: ${scores.stream().min().get()}

<!-- 개수 -->
개수: ${scores.stream().count()}
```

---

### 8. 문자열 템플릿

**문자열 보간:**
```jsp
<c:set var="name" value="홍길동" />
<c:set var="age" value="25" />

<!-- EL 3.0 문자열 템플릿 (일부 구현) -->
${"이름: " + name + ", 나이: " + age}
```

---

### 9. 함수 참조

**메서드 참조:**
```jsp
<c:set var="numbers" value="${['1', '2', '3', '4', '5']}" />

<!-- 문자열을 정수로 변환 -->
${numbers.stream().map(Integer::parseInt).toList()}
<!-- 또는 -->
${numbers.stream().map(s -> Integer.parseInt(s)).toList()}
```

---

### 10. 실전 예제: 사용자 목록 필터링

```jsp
<%
    List<Map<String, Object>> users = new ArrayList<>();
    Map<String, Object> user1 = new HashMap<>();
    user1.put("name", "홍길동");
    user1.put("age", 25);
    user1.put("city", "서울");
    users.add(user1);
    
    Map<String, Object> user2 = new HashMap<>();
    user2.put("name", "김철수");
    user2.put("age", 30);
    user2.put("city", "부산");
    users.add(user2);
    
    Map<String, Object> user3 = new HashMap<>();
    user3.put("name", "이영희");
    user3.put("age", 22);
    user3.put("city", "서울");
    users.add(user3);
    
    request.setAttribute("users", users);
%>

<!-- 서울 거주자만 필터링 -->
<c:forEach var="user" items="${users.stream().filter(u -> u.city == '서울').toList()}">
    <p>${user.name} (${user.age}세) - ${user.city}</p>
</c:forEach>

<!-- 25세 이상만 필터링 -->
<c:forEach var="user" items="${users.stream().filter(u -> u.age >= 25).toList()}">
    <p>${user.name} (${user.age}세)</p>
</c:forEach>

<!-- 나이순으로 정렬 -->
<c:forEach var="user" items="${users.stream().sorted((a, b) -> a.age - b.age).toList()}">
    <p>${user.name}: ${user.age}세</p>
</c:forEach>
```

---

## 🔄 JSTL 1.2 vs JSTL 3.0 비교

### 네임스페이스 변경

**JSTL 1.2:**
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

**JSTL 3.0:**
```jsp
<%@ taglib prefix="c" uri="https://jakarta.ee/xml/ns/jakartaee/jsp/jstl/core" %>
```

### 의존성 변경

**JSTL 1.2:**
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

**JSTL 3.0:**
```xml
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    <version>3.0.0</version>
</dependency>
```

---

## 💡 EL 3.0 활용 팁

### 팁 1: 복잡한 데이터 변환

```jsp
<!-- 사용자 목록에서 이름만 추출 -->
${users.stream().map(u -> u.name).toList()}

<!-- 나이의 평균 계산 -->
${users.stream().map(u -> u.age).average().get()}
```

### 팁 2: 중복 제거

```jsp
<c:set var="duplicates" value="${[1, 2, 2, 3, 3, 3, 4]}" />

<!-- 중복 제거 -->
${duplicates.stream().distinct().toList()}
<!-- 결과: [1, 2, 3, 4] -->
```

### 팁 3: 제한 (Limit)

```jsp
<!-- 처음 3개만 -->
${numbers.stream().limit(3).toList()}

<!-- 처음 5개 제외 -->
${numbers.stream().skip(5).toList()}
```

### 팁 4: 매칭 (Matching)

```jsp
<!-- 모든 요소가 조건을 만족하는지 -->
${numbers.stream().allMatch(x -> x > 0)}  <!-- 모두 양수인가? -->

<!-- 하나라도 조건을 만족하는지 -->
${numbers.stream().anyMatch(x -> x > 5)}  <!-- 5보다 큰 수가 있는가? -->

<!-- 조건을 만족하는 요소가 없는지 -->
${numbers.stream().noneMatch(x -> x < 0)}  <!-- 음수가 없는가? -->
```

---

## ⚠️ 주의사항

### 1. 호환성
- JSTL 3.0은 Jakarta EE 8 이상에서만 동작
- 기존 Java EE 8 프로젝트는 마이그레이션 필요
- Tomcat 9.0 이상 필요

### 2. 성능
- 스트림 연산은 메모리를 사용
- 큰 데이터셋에서는 주의 필요
- 필요시 인덱스 기반 접근 고려

### 3. 브라우저 호환성
- EL 3.0은 서버 측에서 처리
- 브라우저와 무관
- 모든 브라우저에서 동일하게 동작

---

## 📚 학습 순서

1. **기본 JSTL 1.2** (현재 파일)
   - 기본 태그 사용법
   - EL 기본 표현식

2. **EL 3.0 기초**
   - 람다 표현식
   - 스트림 기본

3. **EL 3.0 고급**
   - 복잡한 스트림 연산
   - 실전 활용

---

## ✅ EL 3.0 체크리스트

- [ ] 람다 표현식 사용 가능
- [ ] 스트림 API 사용 가능
- [ ] 필터링 (filter) 사용 가능
- [ ] 변환 (map) 사용 가능
- [ ] 집계 함수 (sum, average, max, min) 사용 가능
- [ ] 정렬 (sorted) 사용 가능
- [ ] 중복 제거 (distinct) 사용 가능
- [ ] 매칭 함수 (allMatch, anyMatch, noneMatch) 사용 가능

---

**JSTL-EL 3.0을 마스터하면 현대적인 JSP 개발이 가능합니다! 🚀**
