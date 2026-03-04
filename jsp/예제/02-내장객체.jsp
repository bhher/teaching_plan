<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP 내장 객체 예제</title>
</head>
<body>
    <h2>JSP 내장 객체 예제</h2>
    
    <!-- 1. request 객체 -->
    <h3>1. request 객체</h3>
    <p>요청 메서드: <%= request.getMethod() %></p>
    <p>요청 URI: <%= request.getRequestURI() %></p>
    <p>요청 URL: <%= request.getRequestURL() %></p>
    <p>클라이언트 IP: <%= request.getRemoteAddr() %></p>
    <p>서버 이름: <%= request.getServerName() %></p>
    <p>서버 포트: <%= request.getServerPort() %></p>
    <p>프로토콜: <%= request.getProtocol() %></p>
    
    <h4>파라미터 정보</h4>
    <%
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
    %>
        <p><%= paramName %>: <%= paramValue %></p>
    <%
        }
    %>
    
    <!-- 2. response 객체 -->
    <h3>2. response 객체</h3>
    <p>컨텐츠 타입: <%= response.getContentType() %></p>
    <p>인코딩: <%= response.getCharacterEncoding() %></p>
    
    <!-- 3. session 객체 -->
    <h3>3. session 객체</h3>
    <%
        String sessionId = session.getId();
        long createTime = session.getCreationTime();
        long lastAccess = session.getLastAccessedTime();
        int maxInactive = session.getMaxInactiveInterval();
    %>
    <p>세션 ID: <%= sessionId %></p>
    <p>세션 생성 시간: <%= new java.util.Date(createTime) %></p>
    <p>마지막 접근 시간: <%= new java.util.Date(lastAccess) %></p>
    <p>세션 유지 시간: <%= maxInactive %>초</p>
    
    <!-- 세션에 값 저장 -->
    <%
        session.setAttribute("username", "홍길동");
        session.setAttribute("loginTime", new java.util.Date());
    %>
    <p>세션에 저장된 사용자명: <%= session.getAttribute("username") %></p>
    
    <!-- 4. application 객체 -->
    <h3>4. application 객체</h3>
    <%
        String serverInfo = application.getServerInfo();
        String realPath = application.getRealPath("/");
        
        // 방문자 카운터
        Integer visitCount = (Integer) application.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 0;
        }
        visitCount++;
        application.setAttribute("visitCount", visitCount);
    %>
    <p>서버 정보: <%= serverInfo %></p>
    <p>실제 경로: <%= realPath %></p>
    <p>총 방문자 수: <%= visitCount %></p>
    
    <!-- 5. out 객체 -->
    <h3>5. out 객체</h3>
    <%
        out.print("out.print()로 출력한 내용<br>");
        out.println("out.println()로 출력한 내용");
        out.flush();
    %>
    
    <!-- 6. pageContext 객체 -->
    <h3>6. pageContext 객체</h3>
    <%
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        HttpSession sess = pageContext.getSession();
    %>
    <p>pageContext를 통해 가져온 요청 메서드: <%= req.getMethod() %></p>
    <p>pageContext를 통해 가져온 세션 ID: <%= sess.getId() %></p>
    
    <!-- 7. page 객체 -->
    <h3>7. page 객체</h3>
    <p>현재 페이지 클래스: <%= page.getClass().getName() %></p>
    
    <!-- 8. config 객체 -->
    <h3>8. config 객체</h3>
    <p>서블릿 이름: <%= config.getServletName() %></p>
    
    <!-- 9. 쿠키 사용 -->
    <h3>9. 쿠키 사용</h3>
    <%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
    %>
        <p>쿠키 이름: <%= cookie.getName() %>, 값: <%= cookie.getValue() %></p>
    <%
            }
        }
        
        // 새 쿠키 생성
        Cookie newCookie = new Cookie("lastVisit", new java.util.Date().toString());
        newCookie.setMaxAge(3600); // 1시간
        response.addCookie(newCookie);
    %>
    <p>쿠키가 설정되었습니다.</p>
</body>
</html>
