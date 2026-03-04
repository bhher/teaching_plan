<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
    // 숫자 리스트
    java.util.List<Integer> numbers = new java.util.ArrayList<>();
    for (int i = 1; i <= 10; i++) {
        numbers.add(i);
    }
    request.setAttribute("numbers", numbers);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSTL과 EL 예제</title>
</head>
<body>
    <h2>JSTL과 EL 예제</h2>
    
    <!-- 1. EL 기본 사용 -->
    <h3>1. EL (Expression Language) 기본</h3>
    <p>이름: ${name}</p>
    <p>나이: ${age}</p>
    <p>점수: ${score}</p>
    
    <!-- 2. EL 연산 -->
    <h3>2. EL 연산</h3>
    <p>나이 + 10: ${age + 10}</p>
    <p>점수 * 2: ${score * 2}</p>
    <p>점수 >= 80: ${score >= 80}</p>
    <p>점수 >= 90 ? 'A' : 'B': ${score >= 90 ? 'A' : 'B'}</p>
    <p>이름이 비어있나? ${empty name ? '예' : '아니오'}</p>
    
    <!-- 3. JSTL 변수 설정 -->
    <h3>3. JSTL 변수 설정</h3>
    <c:set var="message" value="안녕하세요!" />
    <p>메시지: ${message}</p>
    
    <c:set var="total" value="${age + score}" />
    <p>나이 + 점수: ${total}</p>
    
    <!-- 4. JSTL 조건문 -->
    <h3>4. JSTL 조건문</h3>
    
    <h4>c:if 사용</h4>
    <c:if test="${age >= 18}">
        <p>성인입니다.</p>
    </c:if>
    <c:if test="${age < 18}">
        <p>미성년자입니다.</p>
    </c:if>
    
    <h4>c:choose 사용</h4>
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
    
    <!-- 5. JSTL 반복문 -->
    <h3>5. JSTL 반복문</h3>
    
    <h4>숫자 반복</h4>
    <ul>
        <c:forEach var="i" begin="1" end="5" step="1">
            <li>항목 ${i}</li>
        </c:forEach>
    </ul>
    
    <h4>리스트 반복</h4>
    <ul>
        <c:forEach var="fruit" items="${fruits}">
            <li>${fruit}</li>
        </c:forEach>
    </ul>
    
    <h4>리스트 반복 (인덱스 정보 포함)</h4>
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
    
    <h4>Map 반복</h4>
    <ul>
        <c:forEach var="entry" items="${user}">
            <li>${entry.key}: ${entry.value}</li>
        </c:forEach>
    </ul>
    
    <!-- 6. 숫자 리스트 처리 -->
    <h3>6. 숫자 리스트 처리</h3>
    <p>숫자 목록:</p>
    <c:forEach var="num" items="${numbers}">
        ${num}
    </c:forEach>
    
    <p>짝수만 출력:</p>
    <c:forEach var="num" items="${numbers}">
        <c:if test="${num % 2 == 0}">
            ${num}
        </c:if>
    </c:forEach>
    
    <!-- 7. URL 처리 -->
    <h3>7. URL 처리</h3>
    <c:url value="/test.jsp" var="testUrl">
        <c:param name="id" value="123" />
        <c:param name="name" value="홍길동" />
    </c:url>
    <p>생성된 URL: <a href="${testUrl}">${testUrl}</a></p>
    
    <!-- 8. 예외 처리 -->
    <h3>8. 예외 처리</h3>
    <c:catch var="error">
        <%
            int result = 10 / 0;
        %>
    </c:catch>
    <c:if test="${error != null}">
        <p style="color: red;">에러 발생: ${error.message}</p>
    </c:if>
    
    <!-- 9. 출력 -->
    <h3>9. 출력 (c:out)</h3>
    <c:set var="htmlContent" value="<script>alert('XSS');</script>" />
    <p>일반 출력 (위험): ${htmlContent}</p>
    <p>c:out 사용 (안전): <c:out value="${htmlContent}" /></p>
    <p>기본값 사용: <c:out value="${notExist}" default="값이 없습니다." /></p>
    
    <!-- 10. 내장 객체 사용 -->
    <h3>10. EL 내장 객체</h3>
    <p>파라미터: ${param.name != null ? param.name : '없음'}</p>
    <p>세션: ${session.username != null ? session.username : '없음'}</p>
    <p>요청 속성: ${request.name}</p>
    <p>애플리케이션 속성: ${application.visitCount}</p>
</body>
</html>
